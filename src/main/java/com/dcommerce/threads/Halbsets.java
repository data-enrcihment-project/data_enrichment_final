package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;

import com.dcommerce.database.DatabaseQuery;
import com.dcommerce.pricing.SmartPricing;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;

public class Halbsets extends Thread {
	@Override
	public void run() {
		
		//	fetching all the products from the database and saving in a csv file
		String Statement = "SELECT shop_item.description, shop_item.item_no FROM shop_item INNER JOIN shop_item_has_category "
				+ "ON shop_item.item_no = shop_item_has_category.item_no "
				+ "WHERE shop_item_has_category.category_line_no = 28750 ";
		String File = "Halbsets.csv";
		try {
			DatabaseQuery.returnResult(Statement, File);
		} catch (IOException e) {
			System.out.println("Error in fetching data from the database-Eisen!");
			e.printStackTrace();
		}

		
		//	web-scraping
		ArrayList<Entry> entry = new ArrayList<Entry>();
		try {
			entry = GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaegersaetze/Halbsaetze/");
			
			//No products found in www.golflaedchen.de & www.best-preis-golf.de
			
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-Eisen!");
			e.printStackTrace();
		}
		
		//	similarity checking, sorting & taking first 10 items 
		SmartPricing.compareSimilarity(entry,File);
	}


}
