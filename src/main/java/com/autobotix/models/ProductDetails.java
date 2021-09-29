package com.autobotix.models;

public class ProductDetails {
private String productCode;
private String productName;
private String productDescription;


public ProductDetails() {
	super();
	// TODO Auto-generated constructor stub
}


public ProductDetails(String productCode, String productName, String productDescription) {
	super();
	this.productCode = productCode;
	this.productName = productName;
	this.productDescription = productDescription;
}



public String getProductCode() {
	return productCode;
}
public void setProductCode(String productCode) {
	this.productCode = productCode;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getProductDescription() {
	return productDescription;
}
public void setProductDescription(String productDescription) {
	this.productDescription = productDescription;
}



}
