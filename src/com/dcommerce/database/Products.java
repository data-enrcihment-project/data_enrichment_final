package com.dcommerce.database;

/**
 * This is a utility class to change a ResultSet object to a POJO.
 * 
 * @author Mushfiqul Huda
 */

public class Products {
	
	private String description;
	private String item_no;
	
	public String getDescription() {
		return description;
	}

	public Products(String desc, String item_no) {
		this.description = desc;
		this.item_no = item_no;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getItem_no() {
		return item_no;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
}
