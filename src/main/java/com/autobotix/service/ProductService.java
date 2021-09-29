package com.autobotix.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobotix.models.Product;
import com.autobotix.models.ProductDetails;
import com.autobotix.models.ResultDetails;


public interface ProductService {

	public boolean saveProduct(Product product); 
	
	public Product getProductByCode(String code);
	
	public boolean removeProduct(String code);
	
	public boolean updateProduct(Product product);
	
	public List<ProductDetails> getShortInfo(List<String> codes);
	
	public List<String> getListOfCodes();
	
	public List<Product> getAllProducts();
	
	public List<Product> getProductsForCategoryCode(String code);
	
	public ResultDetails evaluateResult(boolean b ,String message);
}
