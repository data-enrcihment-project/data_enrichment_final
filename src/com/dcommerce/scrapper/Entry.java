package com.dcommerce.scrapper;

import java.util.Comparator;

/**
 * This is a custom utility class which stores multiple data types. 
 * 
 * @author Mushfiqul Huda
 */

public class Entry {
	private String productTitle;
	private double basePrice;
	private double discountPrice;
	private double similarityScore;
	private String shopName;

	Entry(String title, double price1, double price2, double score, String shop) {
		this.productTitle = title;
		this.basePrice = price1;
		this.discountPrice = price2;
		this.similarityScore = score;
		this.shopName = shop;
	}
	
	public String getTitle() {
		return productTitle;
	}
	
	public double getBasePrice() {
		return basePrice;
	}
	
	public double getDiscountPrice() {
		return discountPrice;
	}
	
	public double getScore() {
		return similarityScore;
	}
	
	public String getShopName() {
		return shopName;
	}
	
	public void setScore(double score) {
		this.similarityScore = score;
	}

	//	compares an ArrayList based on the Text Similarity score 
	public static Comparator<Entry>	ScoreDiff = new Comparator<Entry>() {

		public int compare(Entry s1, Entry s2) {

		   double product1 = s1.getScore();
		   double product2 = s2.getScore();
		   double res = product2 - product1;
		   if(res > 0) return 1;
	       if(res < 0) return -1;
	       return 0;
		   
	       // For ascending order : res = product1-product2;

		   //For descending order : res = product2 - product1;
	   }};

}