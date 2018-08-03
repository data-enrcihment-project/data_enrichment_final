package com.dcommerce.database;

/**
 * This is a utility class to change a ResultSet object to a POJO.
 * 
 * @author Mushfiqul Huda
 */

public class SimilarProducts {
	private String description;
	private String base_price;
	private String discount_price;
	private String shop_name;

	
	public SimilarProducts(String description, String base_price, String discount_price, String shop_name) {
		super();
		this.description = description;
		this.base_price = base_price;
		this.discount_price = discount_price;
		this.shop_name = shop_name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBase_price() {
		return base_price;
	}


	public void setBase_price(String base_price) {
		this.base_price = base_price;
	}


	public String getDiscount_price() {
		return discount_price;
	}


	public void setDiscount_price(String discount_price) {
		this.discount_price = discount_price;
	}


	public String getShop_name() {
		return shop_name;
	}


	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	

}
