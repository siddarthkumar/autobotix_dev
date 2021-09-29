package com.autobotix.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;


@Entity
public class Product {
    
	@Id
	private String productCode;
	private String productName;
	private String imageUrl;
	@ElementCollection
	private Set<String> categoryList=new HashSet<>();
	private String productDescription;
	private double productCost;
	private CurrencyEnum currency;
	private boolean availability;
	  @ElementCollection(fetch = FetchType.EAGER)
	    @MapKeyColumn(name="name")
	    @Column(name="additionalParameters")
	    @CollectionTable(name="additional_Product_Attributes", joinColumns= {@JoinColumn(name="productCode")})
	private Map<String,String> additionalParameters = new HashMap(); 
	  @ElementCollection(fetch = FetchType.EAGER)
	  @MapKeyColumn(name="warehouse")
	    @Column(name="stocks")
	    @CollectionTable(name="stock", joinColumns= {@JoinColumn(name="productCode")})
	private Map<String, String> stock=new HashMap();
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productCode, String productName, String productDescription, double productCost) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productCost = productCost;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Map<String, String> getAdditionalParameters() {
		return additionalParameters;
	}

	public void setAdditionalParameters(Map<String, String> additionalParameters) {
		this.additionalParameters = additionalParameters;
	}

	public Map<String, String> getStock() {
		return stock;
	}

	public void setStock(Map<String, String> stock) {
		this.stock = stock;
	}

	public Set<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<String> categoryList) {
		this.categoryList = categoryList;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", imageUrl=" + imageUrl
				+ ", categoryList=" + categoryList + ", productDescription=" + productDescription + ", productCost="
				+ productCost + ", currency=" + currency + ", availability=" + availability + ", additionalParameters="
				+ additionalParameters + ", stock=" + stock + "]";
	}

	
	
	
	
	
}
