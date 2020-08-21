package com.iiht.evaluation.coronakit.service;

import java.util.List;

import com.iiht.evaluation.coronakit.exception.CoronaException;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductMasterServiceImpl implements ProductMasterService {
	ProductMasterDao productMasterDao=null;
	
	public ProductMasterServiceImpl(ProductMasterDao productMasterDao)
	{
		this.productMasterDao=productMasterDao;
	}
 
	@Override
	public List<ProductMaster> getAll() throws CoronaException {
		// TODO Auto-generated method stub	
				try {
			return productMasterDao.getAll();
				}catch(CoronaException e) { throw e;}
		
		}

	@Override
	public boolean deleteProduct(int productid) throws CoronaException {
		// TODO Auto-generated method stub
		try {
		return productMasterDao.deleteProduct(productid);
	}catch(CoronaException e) { throw e;}
	}

	@Override
	public ProductMaster getProductById(int productid) throws CoronaException {
		// TODO Auto-generated method stub
		try {
		return productMasterDao.getById(productid);
	}catch(CoronaException e) { throw e;}
	}

	@Override
	public ProductMaster updateProduct(ProductMaster product) throws CoronaException {
		try{// TODO Auto-generated method stub
		return productMasterDao.saveProduct(product);
	}catch(CoronaException e) { throw e;}
	}

	@Override
	public ProductMaster addProduct(ProductMaster product) throws CoronaException {
		// TODO Auto-generated method stub
		try {
		return  productMasterDao.addProduct(product);
		}catch(CoronaException e) { throw e;}
	}
	}


