package com.autobotix.models;

public class CategoryDetails {
      private String code;
      private String Name;
      private String Description;
      
      
	public CategoryDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CategoryDetails(String code, String name, String description) {
		super();
		this.code = code;
		Name = name;
		Description = description;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
      
      
}
