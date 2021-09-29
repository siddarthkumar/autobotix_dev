package com.autobotix.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobotix.models.Category;
import com.autobotix.models.Product;
import com.autobotix.models.ResultDetails;
import com.autobotix.service.CategoryService;

@RestController
@RequestMapping(value = "/Categories")
public class CatgoryController {

	@Autowired
	CategoryService service;
	
@GetMapping(value = "/allCategories",produces = {"application/json"})
public List<Category> getAllCategories()
{
    return service.getAllCategorys();
}

@GetMapping(value = "/getCategoryByCode/{categoryCode}",produces = {"application/json"})
public Category getCategoryByCode(@PathVariable String categoryCode)
{
    return service.getCategoryByCode(categoryCode);
}

@PostMapping(value = "/updateCategory",produces = {"application/json"},consumes = {"application/json"})
public ResultDetails updateCategory(@RequestBody Category category)
{
    return service.evaluateResult(service.updateCategory(category),"UPDATE");
}

@PostMapping(value = "/addCategory",produces = {"application/json"},consumes = {"application/json"})
public ResultDetails saveCategory(@RequestBody Category category)
{
    return service.evaluateResult(service.saveCategory(category),"SAVE");
}


@DeleteMapping(value = "/removeCategory/{categoryCode}",produces = {"application/json"})
public ResultDetails removeCategory(@PathVariable String categoryCode)
{
    return service.evaluateResult(service.removeCategory(categoryCode),"DELETE");
}

@GetMapping(value = "/getCategoryCodeList",produces = {"application/json"})
public List<String> getCategoryCodeList()
{
    return service.getListOfCodes();
}

@GetMapping(value = "/getContainedProducts/{categoryCode}",produces = {"application/json"})
public List<Product> getContainedInProducts(@PathVariable String categoryCode)
{
    return service.getContainedProduct(categoryCode);
}

@GetMapping(value = "/getListOfContainedProductCodes/{categoryCode}",produces = {"application/json"})
public Set<String> getContainedInProductCodes(@PathVariable String code)
{
    return service.getContainedProductCodelist(code);
}

}
