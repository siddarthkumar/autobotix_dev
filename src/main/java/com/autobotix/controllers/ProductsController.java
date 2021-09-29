package com.autobotix.controllers;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.autobotix.models.CrudOperations;
import com.autobotix.models.Product;
import com.autobotix.models.ProductDetails;
import com.autobotix.models.ResultDetails;
import com.autobotix.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

	@Resource(name="productService")
	ProductService productService;
	
      Logger logger= Logger.getLogger(ProductsController.class);

     @GetMapping(value = "p/{productCode}", produces = {"application/json"})	
	public Product getProduct(@PathVariable String productCode) {
		return  productService.getProductByCode(productCode);
	}

	@GetMapping(value = "/allProducts",produces = {"application/json"})
	public List<Product> getAllProducts() {
		return productService.getAllProducts();

	}

	@PostMapping(value = "/addProduct",produces = {"application/json"},consumes = {"application/json"})
	public ResultDetails saveProduct(@RequestBody Product product) {
		return  productService.evaluateResult(productService.saveProduct(product),CrudOperations.SAVE);
	}

	@PostMapping(value = "/updateProduct",produces = {"application/json"},consumes = {"application/json"})   
	public ResultDetails updateProduct(@RequestBody Product product) {
		return productService.evaluateResult(productService.updateProduct(product),CrudOperations.UPDATE);
	}

	
	@DeleteMapping(value =  "/deleteProduct/{productCode}",produces = {"application/json"})
	public ResultDetails deleteProduct(@PathVariable String productCode) {
		
		return productService.evaluateResult(productService.removeProduct(productCode),CrudOperations.DELETE);
	}
	
	@PostMapping(value = "/getInfo",produces = {"application/json"},consumes = {"application/json"})
	public List<ProductDetails> getShortInfo(@RequestBody List<String> productCode)
	{
		return productService.getShortInfo(productCode);
	}
	
	@GetMapping(value= "/getCodesList",produces = {"application/json"})
	public List<String> getProductCodesList()
	{
		return productService.getListOfCodes();
	}
	
	@GetMapping(value= "/getProductByCategory/{categoryCode}",produces = {"application/json"})
	public List<Product> getProductCodesList(@PathVariable String categoryCode)
	{
		return productService.getProductsForCategoryCode(categoryCode);
	}


}
