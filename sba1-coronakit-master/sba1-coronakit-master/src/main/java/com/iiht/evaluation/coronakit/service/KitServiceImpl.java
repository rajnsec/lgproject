package com.iiht.evaluation.coronakit.service;

import java.util.List;

import com.iiht.evaluation.coronakit.exception.CoronaException;
import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;

public class KitServiceImpl implements KitService {
	
	KitDao kitDao=null;
	
	public KitServiceImpl(KitDao kitDao)
	{
		this.kitDao=kitDao;
	}

	@Override
	public CoronaKit getCustomerDetailById(int customerId) throws CoronaException {
		// TODO Auto-generated method stub
		try
		{
		return kitDao.getByCustomerId(customerId);
		}catch(CoronaException e) { throw e;}
	}

	@Override
	public CoronaKit addCustomer(CoronaKit customer) throws CoronaException {
		// TODO Auto-generated method stub
		try
		{
		return  kitDao.insertCustomer(customer);
		}catch(CoronaException e) { throw e;}
	}

	@Override
	public KitDetail addItemToKit(KitDetail item) throws CoronaException {
		// TODO Auto-generated method stub
		try
		{
		item=kitDao.getQtyFromKit(item);
		if(item.getQuantity()>=1)
		{
			item.setQuantity(item.getQuantity()+1);
			return kitDao.updateQty(item);
		}
		else
		{
			item.setQuantity(1);
			return kitDao.addItemToKit(item);
		}
		}catch(CoronaException e) { throw e;}
		
			}

	@Override
	public boolean deleteItemFromKit(KitDetail item) throws CoronaException {
		// TODO Auto-generated method stub
		try
		{
		item=kitDao.getQtyFromKit(item);
		if(item.getQuantity()==-1)
		{
			return true;
		}
		
		else if(item.getQuantity()==1)
		{
			
			return kitDao.deleteItemFromKit(item);
			
		}
		else
		{
			item.setQuantity(item.getQuantity()-1);
			kitDao.updateQty(item);
			return true; 
		}
		}catch(CoronaException e) { throw e;}
		
	}

	@Override
	public List<KitDetail> getKitDetails(KitDetail item) throws CoronaException {
		// TODO Auto-generated method stub
		try
		{
		return kitDao.getKitDetails(item);
		}catch(CoronaException e) { throw e;}
	}

	@Override
	public CoronaKit updateCustomerAddress(CoronaKit customer) throws CoronaException {
		// TODO Auto-generated method stub
		try {
		return kitDao.updateCustomerAddress(customer);
		}catch(CoronaException e) { throw e;}
	}

	@Override
	public int getOrderTotal(KitDetail item) throws CoronaException {
		// TODO Auto-generated method stub
		try
		{
		return kitDao.getOrderTotal(item);
		}catch(CoronaException e) { throw e;}
	}

}
