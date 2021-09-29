package com.autobotix.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.autobotix.models.Category;
import com.autobotix.models.Product;
import com.autobotix.models.ProductDetails;
import com.autobotix.models.ResultDetails;
import com.autobotix.repositories.CategoryRepository;
import com.autobotix.repositories.ProductRepository;

import jdk.internal.org.jline.utils.Log;
@Component("productService")
public class ProductServiceImpl implements ProductService {

	
	@Autowired ProductRepository repository;
	@Autowired CategoryRepository categoryRepo;

	@Override
	public boolean saveProduct(Product product) {
		try {
			repository.save(product);
			if(!CollectionUtils.isEmpty(product.getCategoryList()))
			for(String category :product.getCategoryList())
			{
				Category categ= categoryRepo.findById(category).get();
				if(!CollectionUtils.isEmpty(categ.getListOfProductCodes()))
				{
					categ.getListOfProductCodes().add(product.getProductCode());
				}
				else {
					Set<String> prodCode = new HashSet();
					prodCode.add(product.getProductCode());
					categ.setListOfProductCodes(prodCode);
				}
				categoryRepo.save(categ);
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Product getProductByCode(String code) {

		return repository.findById(code).orElse(new Product());
	}

	@Override
	public boolean removeProduct(String code) {

		try {
			 Set<String> categoryList= repository.findById(code).get().getCategoryList();
	            if(!CollectionUtils.isEmpty(categoryList))
	            {
	            	for(String productCode: categoryList)
	            	{
	            		Category category = categoryRepo.findById(productCode).get();
	            		if(!CollectionUtils.isEmpty(category.getListOfProductCodes()) && category.getListOfProductCodes().contains(code))
	            		{
	            		category.getListOfProductCodes().remove(code);
	            			categoryRepo.save(category);
	            		}
	            	}
	            }
			
			repository.deleteById(code);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean updateProduct(Product product) {

		try {
			Product update = getProductByCode(product.getProductCode());
			update.setProductCode(product.getProductCode());
			if (!StringUtils.isEmpty(product.getProductName())) {
				update.setProductName(product.getProductName());
			}
			if (!StringUtils.isEmpty(product.getProductDescription())) {
				update.setProductDescription(product.getProductDescription());
			}
			if (!StringUtils.isEmpty(product.getProductCost())) {
				update.setProductCost(product.getProductCost());
			}
			if (!CollectionUtils.isEmpty(product.getAdditionalParameters())) {
				Map<String, String> updateMap = update.getAdditionalParameters();

				for (Map.Entry<String, String> entry : product.getAdditionalParameters().entrySet()) {
					update.getAdditionalParameters().put(entry.getKey(), entry.getValue());
				}

			}
			if(!CollectionUtils.isEmpty(product.getCategoryList()) && !CollectionUtils.isEmpty(update.getCategoryList()))
			{
				          for(String code : product.getCategoryList())
				          {
				        	 Category category=categoryRepo.findById(code).get();
				        	 category.getListOfProductCodes().add(product.getProductCode());
				        	 categoryRepo.save(category);
				          }
	                  update.getCategoryList().addAll(product.getCategoryList());
			}
			else {
				update.setCategoryList(product.getCategoryList());
			}
			repository.save(update);
			return true;
		} catch (Exception e) {
			System.out.println("Error while updating" + e.getMessage());
			return false;
		}

	}

	@Override
	public List<ProductDetails> getShortInfo(List<String> codes) {
		List<ProductDetails> details= new ArrayList();
		
		for(String code:codes)
		{    Product p =getProductByCode(code);
		       ProductDetails productDetails = new ProductDetails(p.getProductCode(),p.getProductName(),p.getProductDescription());
			details.add(productDetails);
		}
                   return details;
	}

	@Override
	public List<String> getListOfCodes() {
		return new ArrayList<String>(repository.findAllProductCode());
	}

	@Override
	public List<Product> getAllProducts() {
		return repository.findAll();
	}
	
    public ResultDetails evaluateResult(boolean b,String operation)
    {
    	return b==true?new ResultDetails("SUCCESS",operation+" : is performed successfully"):new ResultDetails("FAILURE",operation+" could not be performed successfully");
    	
    }

	@Override
	public List<Product> getProductsForCategoryCode(String code) {
		try {
	          List<Product>  productList = new ArrayList(repository.findProductsByCategory(code));
	          return productList;
		}
		catch(Exception e)
		{
			Log.error("Error while fetching Products"+e.getMessage());
		}
		return Collections.emptyList();
	}

}
