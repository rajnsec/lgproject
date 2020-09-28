package com.eval.coronakit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.coronakit.dao.ProductMasterRepository;
import com.eval.coronakit.entity.ProductMaster;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMasterRepository repository;
	
	@Override
	public ProductMaster addNewProduct(ProductMaster product) {
		// TODO Auto-generated method stub
		if (product != null) {
			if (repository.existsById(product.getId())) {
				System.out.println("Product Id is already in use");
			}
			repository.save(product);
		}
		return product;
	}

	@Override
	public List<ProductMaster> getAllProducts() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ProductMaster deleteProduct(int productId) {
		// TODO Auto-generated method stub
		repository.deleteById(productId);
		return null;
	}

	@Override
	public ProductMaster getProductById(int productId) {
		// TODO Auto-generated method stub
		return repository.findById(productId).orElse(null);

	}

}
