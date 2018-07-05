package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;

import com.dcommerce.database.DatabaseQuery;
import com.dcommerce.pricing.SmartPricing;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.Golflaedchen;

public class Handschuhe extends Thread {
	@Override
	public void run() {
		
		//	fetching all the products from the database and saving in a csv file
		String Statement = "SELECT shop_item.description, shop_item.item_no FROM shop_item INNER JOIN shop_item_has_category "
				+ "ON shop_item.item_no = shop_item_has_category.item_no "
				+ "WHERE shop_item_has_category.category_line_no = 42500 ";
		String File = "Handschuhe.csv";
		try {
			DatabaseQuery.returnResult(Statement, File);
		} catch (IOException e) {
			System.out.println("Error in fetching data from the database-Eisen!");
			e.printStackTrace();
		}

		
		//	web-scraping
		ArrayList<Entry> entry = new ArrayList<Entry>();
		try {
			entry = Golflaedchen.getProducts("https://www.golflaedchen.de/Golfhandschuhe/?p=1");

			entry.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golfhandschuhe/?p=2"));

			entry.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfhandschuhe/?p=1"));
			
			entry.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfhandschuhe/?p=2"));
			
			entry.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfhandschuhe/?p=3"));
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-Eisen!");
			e.printStackTrace();
		}
		
		//	similarity checking, sorting & taking first 10 items 
		SmartPricing.compareSimilarity(entry,File);
	}


}
