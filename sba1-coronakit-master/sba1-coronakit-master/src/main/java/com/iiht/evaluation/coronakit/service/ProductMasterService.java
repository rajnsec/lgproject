package com.iiht.evaluation.coronakit.service;

import java.util.List;

import com.iiht.evaluation.coronakit.exception.CoronaException;
import com.iiht.evaluation.coronokit.model.ProductMaster;



public interface ProductMasterService {
	
	List<ProductMaster> getAll() throws CoronaException;

	boolean deleteProduct(int productid) throws CoronaException;
	
	ProductMaster getProductById(int productid) throws CoronaException;
	ProductMaster updateProduct(ProductMaster product) throws CoronaException;
	ProductMaster addProduct(ProductMaster product) throws CoronaException;
}
