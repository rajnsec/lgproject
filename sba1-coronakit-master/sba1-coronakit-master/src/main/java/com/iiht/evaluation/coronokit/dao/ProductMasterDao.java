package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronakit.exception.CoronaException;
import com.iiht.evaluation.coronokit.model.ProductMaster;




public class ProductMasterDao {
	
	public static final String INS_ITEM_QRY = "INSERT INTO item(pid,pname,pcost,pdesc) VALUES(?,?,?,?)";
	public static final String UPD_ITEM_QRY = "UPDATE item set pname=?,pcost=?,pdesc=? WHERE pid=?";
	public static final String DEL_ITEM_QRY = "DELETE FROM item WHERE pid=?";
	public static final String GET_ALL_ITEM_QRY = "SELECT pid,pname,pcost,pdesc FROM item";
	public static final String GET_ITEM_BY_ID_QRY = "SELECT pid,pname,pcost,pdesc FROM item WHERE pid=?";

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	
	public List<ProductMaster> getAll() throws CoronaException   {
		List<ProductMaster> products = new ArrayList<>();
		
		try {
		
			this.connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_ALL_ITEM_QRY);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				ProductMaster product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setCost(rs.getString(3));
				product.setProductDescription(rs.getString(4));
				
				products.add(product);
			}
			
			if(products.isEmpty()) {
				products=null;
			}
			
			this.disconnect();
		
		}catch(SQLException e) {
			throw new CoronaException("List All Product Failed. Check your network connection.");
			}

			
		return products;
	} 
	public boolean deleteProduct(int productId) throws CoronaException {
		boolean isDeleted = false;
		try 
		{
			this.connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(DEL_ITEM_QRY);
			

			pst.setInt(1, productId);

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;
			this.disconnect();
		} catch (SQLException exp) {
			throw new CoronaException("Delete Product Failed. Product Id may not be valid one.");
		}
		return isDeleted;
	}
	
	public ProductMaster getById(int productId) throws CoronaException {
		ProductMaster product = null;

		try 
		{
			this.connect();
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_ITEM_BY_ID_QRY);

			pst.setInt(1, productId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setCost(rs.getString(3));
				product.setProductDescription(rs.getString(4));
				this.disconnect();
			}

		} catch (SQLException exp) {
			throw new CoronaException("List Product Failed. Product Id may not be valid one.");
		}

		return product;
	}
	
	public ProductMaster saveProduct(ProductMaster product) throws CoronaException {
		if (product != null) {
			try  {
				this.connect();
				PreparedStatement pst = jdbcConnection.prepareStatement(UPD_ITEM_QRY);
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getCost());
				pst.setString(3, product.getProductDescription());
				pst.setInt(4, product.getId());
				

				pst.executeUpdate();
				
				this.disconnect();
			} catch (SQLException exp) {
				throw new CoronaException("Update Product Failed. All fields are mandatory and can not be left blank.");
			}
		}
	return  product;
	}
	
	public ProductMaster addProduct(ProductMaster product) throws CoronaException {
		if (product != null) {
			try  {
				this.connect();
				PreparedStatement pst = jdbcConnection.prepareStatement(INS_ITEM_QRY);
				pst.setInt(1, product.getId());
				pst.setString(2, product.getProductName());
				pst.setString(3, product.getCost());
				pst.setString(4, product.getProductDescription());


				pst.executeUpdate();
				
				this.disconnect();
			} catch (SQLException exp) {
				throw new CoronaException("Add Product Failed. All fields are mandatory and can not be left blank. Product ID should be unique.");
			}
		}
	return  product;
	}
	// add DAO methods as per requirements
}