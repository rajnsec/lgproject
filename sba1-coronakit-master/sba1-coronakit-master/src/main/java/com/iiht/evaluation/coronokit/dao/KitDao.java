package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronakit.exception.CoronaException;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;



public class KitDao {
	
	public static final String INS_CUST_QRY = "INSERT INTO customer(cid,cname,cemail,cphone) VALUES(?,?,?,?)";
	public static final String GET_CUST_BY_ID_QRY = "SELECT cid,cname,cemail,cphone,caddress FROM customer WHERE cid=?";
	public static final String UPD_CUST_ADDR_QRY = "UPDATE customer set caddress=? WHERE cid=?";
	
	public static final String INS_KIT_QRY = "INSERT INTO kit(cid,pid,qty) VALUES(?,?,?)";
	public static final String DEL_KIT_QRY = "DELETE FROM kit WHERE cid=? and pid=?";
	public static final String GET_PRODID_KIT_QRY = "SELECT cid,pid,qty FROM kit WHERE cid=? and pid=?";
	public static final String UPD_KIT_QTY_QRY = "UPDATE kit set qty=? WHERE cid=? and pid=?";
	
	public static final String GET_KIT_DETAILS_BY_ID_QRY=
			"SELECT kit.pid,pname,pdesc,pcost,kit.qty, qty*pcost as TotalCost from item INNER JOIN kit on item.pid=kit.pid where cid=?";
	
	public static final String GET_ORDER_TOTAL_BY_ID_QRY="SELECT sum(TotalCost) as OrderTotal from (SELECT qty*pcost as TotalCost from item INNER JOIN kit on item.pid=kit.pid where cid=?) as derived";

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public CoronaKit getByCustomerId(int customerId) throws CoronaException {
		CoronaKit customer = null;

		try 
		{
			this.connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_CUST_BY_ID_QRY);

			pst.setInt(1, customerId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				customer = new CoronaKit();
				customer.setId(rs.getInt(1));
				customer.setPersonName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setContactNumber(rs.getString(4));
				customer.setDeliveryAddress(rs.getString(5));
				this.disconnect();
			}

		} catch (SQLException exp) {
			throw new CoronaException("List Customer Details Failed. Check network connection.");
		}

		return customer;
	}
	public CoronaKit insertCustomer(CoronaKit customer) throws CoronaException {
		if (customer != null) {
			try  {
				this.connect();
				PreparedStatement pst = jdbcConnection.prepareStatement(INS_CUST_QRY);
				pst.setInt(1, customer.getId());
				pst.setString(2, customer.getPersonName());
				pst.setString(3, customer.getEmail());
				pst.setString(4, customer.getContactNumber());


				pst.executeUpdate();
				
				this.disconnect();
			} catch (SQLException exp) {
				throw new CoronaException("Add Customer Failed. Customer ID should be unique.");
			}
		}
	return  customer;
	}
	
	public KitDetail addItemToKit(KitDetail item) throws CoronaException {
		if (item != null) {
			try  {
				this.connect();
				PreparedStatement pst = jdbcConnection.prepareStatement(INS_KIT_QRY);
				pst.setInt(1, item.getId());
				pst.setInt(2, item.getProductId());
				pst.setInt(3, item.getQuantity());
				

				pst.executeUpdate();
				
				this.disconnect();
			} catch (SQLException exp) {
				throw new CoronaException("Add Item Failed. Check network connection");
			}
		}
	return  item;
	}

	public boolean deleteItemFromKit(KitDetail item) throws CoronaException {
		boolean isDeleted = false;
		try 
		{
			this.connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(DEL_KIT_QRY);
			
			pst.setInt(1, item.getId());
			pst.setInt(2, item.getProductId());

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;
			this.disconnect();
		} catch (SQLException exp) {
			throw new CoronaException("Delete Product Failed. Check your network connection.");
		}
		return isDeleted;
	}
	
	public KitDetail getQtyFromKit(KitDetail item) throws CoronaException {
		
		try 
		{
			this.connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_PRODID_KIT_QRY);

			pst.setInt(1, item.getId());
			pst.setInt(2, item.getProductId());
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				item.setQuantity(rs.getInt(3));
				this.disconnect();
			}

		} catch (SQLException exp) {
			throw new CoronaException("Get Item Qty Failed. check your network connection.");
		}

		return item;
	}
	
	public KitDetail updateQty(KitDetail item) throws CoronaException {
		if (item != null) {
			try  {
				this.connect();
				PreparedStatement pst = jdbcConnection.prepareStatement(UPD_KIT_QTY_QRY);
				pst.setInt(1, item.getQuantity());
				pst.setInt(2, item.getId());
				pst.setInt(3, item.getProductId());
							

				pst.executeUpdate();
				
				this.disconnect();
			} catch (SQLException exp) {
				throw new CoronaException("Update Qty Item Failed. check your network connection.");
			}
		}
	return  item;
	}
	
public List<KitDetail> getKitDetails(KitDetail existingItem) throws CoronaException {
	List<KitDetail> items=new ArrayList<>();
		try 
		{
			this.connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_KIT_DETAILS_BY_ID_QRY);
			
			pst.setInt(1, existingItem.getId());
			existingItem=null;
						
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				KitDetail item=new KitDetail();
				item.setProductId(rs.getInt(1));
				item.setProductName(rs.getString(2));
				item.setProductDescription(rs.getString(3));
				item.setCost(rs.getString(4));
				item.setQuantity(rs.getInt(5));
				item.setAmount(rs.getInt(6));	
				items.add(item);
				
			}
			if(items.isEmpty()) {
				items=null;
			}
			this.disconnect();
		} catch (SQLException exp) {
			throw new CoronaException("Fetch Kit Details Failed. check your network connection.");
		}
			
		return items;
	}


public int getOrderTotal(KitDetail item) throws CoronaException {
	
	try 
	{
		this.connect();
		PreparedStatement pst = jdbcConnection.prepareStatement(GET_ORDER_TOTAL_BY_ID_QRY);
		
		pst.setInt(1, item.getId());
		
					
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {			
			item.setOrderTotal(rs.getInt(1));	
			}
		this.disconnect();
	} catch (SQLException exp) {
		throw new CoronaException("Get Order Total Failed. check your network connection.");
	}

	return item.getOrderTotal();
}

public CoronaKit updateCustomerAddress(CoronaKit customer) throws CoronaException {
	if (customer != null) {
		try  {
			this.connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(UPD_CUST_ADDR_QRY);
			pst.setString(1, customer.getDeliveryAddress());
			pst.setInt(2, customer.getId());
								

			pst.executeUpdate();
			
			this.disconnect();
		} catch (SQLException exp) {
			throw new CoronaException("Save Delivery Address  Failed. Please provide valid address or check your network connection.");
		}
	}
return  customer;
}
	// add DAO methods as per requirements
}