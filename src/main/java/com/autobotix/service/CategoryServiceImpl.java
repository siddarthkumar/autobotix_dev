package com.autobotix.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.autobotix.models.Category;
import com.autobotix.models.CategoryDetails;
import com.autobotix.models.Product;
import com.autobotix.models.ResultDetails;
import com.autobotix.repositories.CategoryRepository;
import com.autobotix.repositories.ProductRepository;


@Component
public class CategoryServiceImpl implements CategoryService{
Logger Log = Logger.getLogger(CategoryServiceImpl.class);
	
    
@Autowired CategoryRepository repository;
@Autowired ProductRepository pRepository;

	
	@Override
	public boolean saveCategory(Category category) {
        
		try {
	            if(StringUtils.isEmpty(repository.findById(category.getCategoryCode()).get().getCategoryCode()))
	            {
	            	if(!CollectionUtils.isEmpty(category.getListOfProductCodes()))
	            	{
	            		for(String code :category.getListOfProductCodes())
	            		{
	            			    Product product = pRepository.findById(code).orElse(null);
	            			    if(Objects.nonNull(product))
	            			    {
	            			    	if(!CollectionUtils.isEmpty(product.getCategoryList()) && !product.getCategoryList().contains(category.getCategoryCode()))
	            			    	{
	            			    		product.getCategoryList().add(category.getCategoryCode());
	            			    		pRepository.save(product);
	            			    	}
	            			    	else {
	            			    		Set<String> catList =new HashSet();
	            			    		catList.add(category.getCategoryCode());
	            			    		product.setCategoryList(catList);
	            			    		pRepository.save(product);
	            			    	}
	            			    }
	            		}
	            	}
	            	repository.save(category);
	            	return true;
	            }
	            else {
	            	Log.error("Category already Exists");
	            	return false;
	            	
	            }
		}catch(Exception e)
		{
			Log.error("Error while saving Category :"+e.getMessage());
			return false;
		}
		
	}

	@Override
	public Category getCategoryByCode(String code) {
		return repository.findById(code).orElse(new Category());
	}

	@Override
	public boolean removeCategory(String code) {
	            try {
	            Set<String> productList= repository.findById(code).get().getListOfProductCodes();
	            if(!CollectionUtils.isEmpty(productList))
	            {
	            	for(String productCode: productList)
	            	{
	            		Product product = pRepository.findById(productCode).get();
	            		if(!CollectionUtils.isEmpty(product.getCategoryList()) && product.getCategoryList().contains(code))
	            		{
	            			product.getCategoryList().remove(code);
	            			pRepository.save(product);
	            		}
	            	}
	            }
	            repository.deleteById(code);
	            return true;
	            }
	            catch(Exception e)
	            {
	            	Log.error("Error while deleting"+e.getMessage());
	            	return false;
	            }
	}

	@Override
	public boolean updateCategory(Category Category) {
		try {
			Category update = getCategoryByCode(Category.getCategoryCode());
			update.setCategoryCode(Category.getCategoryCode());
			if (!StringUtils.isEmpty(Category.getCategoryName())) {
				update.setCategoryName(Category.getCategoryName());
			}
			if (!StringUtils.isEmpty(Category.getDescription())) {
				update.setDescription(Category.getDescription());
			}
			if (!StringUtils.isEmpty(Category.getCurrency())) {
				update.setCurrency(Category.getCurrency());
			}
			if (!CollectionUtils.isEmpty(Category.getListOfProductCodes()) && !CollectionUtils.isEmpty(update.getListOfProductCodes())) {
                         update.getListOfProductCodes().addAll(Category.getListOfProductCodes());
                         if(!CollectionUtils.isEmpty(Category.getListOfProductCodes()))
     	            	{
     	            		for(String code :Category.getListOfProductCodes())
     	            		{
     	            			    Product product = pRepository.findById(code).orElse(null);
     	            			    if(Objects.nonNull(product))
     	            			    {
     	            			    	if(!CollectionUtils.isEmpty(product.getCategoryList()) && !product.getCategoryList().contains(Category.getCategoryCode()))
     	            			    	{
     	            			    		product.getCategoryList().add(Category.getCategoryCode());
     	            			    		pRepository.save(product);
     	            			    	}
     	            			    	else {
     	            			    		Set<String> catList =new HashSet();
     	            			    		catList.add(Category.getCategoryCode());
     	            			    		product.setCategoryList(catList);
     	            			    		pRepository.save(product);
     	            			    	}
     	            			    }
     	            		}
     	            	}
			}else {
				update.setListOfProductCodes(Category.getListOfProductCodes());
			}
			repository.save(update);
			return true;
		} catch (Exception e) {
			System.out.println("Error while updating" + e.getMessage());
			return false;
		}
	}

	@Override
	public List<CategoryDetails> getShortInfo(List<String> codes) {
		List<CategoryDetails> details= new ArrayList();         
		for(String code:codes)
		          {
		        	  Category categ= repository.findById(code).get();
		        	  CategoryDetails detail = new CategoryDetails(categ.getCategoryCode(),categ.getCategoryName(),categ.getDescription());
		        	  details.add(detail);
		          }
		return details;
	}

	@Override
	public List<String> getListOfCodes() {
		return new ArrayList<String>(repository.findAllCategoryCode());
	}

	@Override
	public List<Category> getAllCategorys() {
		return repository.findAll();
	}

	@Override
	public ResultDetails evaluateResult(boolean b, String operation) {
		return b==true?new ResultDetails("SUCCESS",operation+" : is performed successfully"):new ResultDetails("FAILURE",operation+" could not be performed successfully");
	}

	@Override
	public List<Product> getContainedProduct(String code) {
		try {
	          List<Product>  productList = new ArrayList(pRepository.findProductsByCategory(code));
	          return productList;
		}
		catch(Exception e)
		{
			Log.error("Error while fetching Products"+e.getMessage());
		}
		return Collections.emptyList();
	}

	@Override
	public Set<String> getContainedProductCodelist(String code) {
		Category category = repository.findById(code).orElse(new Category());
		return category.getListOfProductCodes();
	}

}
