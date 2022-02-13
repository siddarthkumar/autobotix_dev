package com.autobotix.models;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
     @Id
	private String categoryCode;
	private String categoryName;
	private String  created;
	private String description;
	private String currency;
	@ElementCollection
	private Set<String> listOfProductCodes;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String categoryCode, String categoryName, String description, Set<String> listOfProductCodes) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.description = description;
		this.listOfProductCodes = listOfProductCodes;
	}

	public Category(String categoryCode, String categoryName, String description) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.description = description;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Set<String> getListOfProductCodes() {
		return listOfProductCodes;
	}

	public void setListOfProductCodes(Set<String> listOfProductCodes) {
		this.listOfProductCodes = listOfProductCodes;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Category [categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", description="
				+ description + ", currency=" + currency + ", listOfProductCodes=" + listOfProductCodes
				+ "]";
	}
	
}
