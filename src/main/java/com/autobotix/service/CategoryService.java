package com.autobotix.service;

import java.util.List;
import java.util.Set;

import com.autobotix.models.Category;
import com.autobotix.models.CategoryDetails;
import com.autobotix.models.Product;
import com.autobotix.models.ResultDetails;

public interface CategoryService {
	
public boolean saveCategory(Category Category); 
	
	public Category getCategoryByCode(String code);
	
	public boolean removeCategory(String code);
	
	public boolean updateCategory(Category Category);
	
	public List<CategoryDetails> getShortInfo(List<String> codes);
	
	public List<String> getListOfCodes();
	
	public List<Category> getAllCategorys();
	
	public List<Product> getContainedProduct(String code);
	
	public Set<String> getContainedProductCodelist(String code);
	
	public ResultDetails evaluateResult(boolean b ,String message);
	

}
