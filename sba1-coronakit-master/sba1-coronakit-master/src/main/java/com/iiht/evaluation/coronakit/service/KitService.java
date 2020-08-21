package com.iiht.evaluation.coronakit.service;

import java.util.List;

import com.iiht.evaluation.coronakit.exception.CoronaException;
import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;

public interface KitService {
	CoronaKit getCustomerDetailById(int customerId) throws CoronaException;
	
	CoronaKit addCustomer(CoronaKit customer) throws CoronaException;
	
	KitDetail addItemToKit(KitDetail item) throws CoronaException;
	
	boolean deleteItemFromKit(KitDetail item) throws CoronaException;
	
	List<KitDetail> getKitDetails(KitDetail item)  throws CoronaException;
	
	CoronaKit updateCustomerAddress(CoronaKit customer) throws CoronaException;
	
	int getOrderTotal(KitDetail item) throws CoronaException; 

}
