package com.dcommerce.scrapper;

import java.util.Comparator;

/**
 * This is a custom class which can store multiple data types. The constructor
 * will store product title as string, prices and score as double. The the value
 * of score will be changed later in the string similarity finding phase. It
 * will be also used to sort the products according to their similarities.
 * 
 * @author Mushfiqul Huda
 */

public class Entry {
	private String title;
	private double price1;
	private double price2;
	private double score;

	Entry(String title, double price1, double price2, double score) {
		this.title = title;
		this.price1 = price1;
		this.price2 = price2;
		this.score = score;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getPrice1() {
		return price1;
	}
	
	public double getPrice2() {
		return price2;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
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
		   
	       // For ascending order
		  // res = product1-product2;

		   //For descending order
	       //return product2 - product1;
	   }};

}