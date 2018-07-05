package com.dcommerce.threads;

import com.dcommerce.database.*;
import com.dcommerce.pricing.SmartPricing;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

import java.io.IOException;
import java.util.ArrayList;
//Done!
public class Hybrids extends Thread {

	@Override
	public void run() {
		
		//	fetching all the products from the database and saving in a csv file
		String Statement = "SELECT shop_item.description, shop_item.item_no FROM shop_item INNER JOIN shop_item_has_category "
				+ "ON shop_item.item_no = shop_item_has_category.item_no "
				+ "WHERE shop_item_has_category.category_line_no = 24687 ";
		String File = "Hybrids.csv";
		try {
			DatabaseQuery.returnResult(Statement, File);
		} catch (IOException e) {
			System.out.println("Error in fetching data from the database-Eisen!");
			e.printStackTrace();
		}

		
		//	web-scraping
		ArrayList<Entry> entry = new ArrayList<Entry>();
		try {
			entry = Golflaedchen.getProducts("https://www.golflaedchen.de/Hybrid-Golfschlaeger/?p=1");

			entry.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Hybrid-Golfschlaeger/?p=2"));

			entry.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschlaeger/hybrid/?p=1"));

			entry.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Hybrids/"));
			
			entry.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Hybrids/2/"));
			
			entry.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Hybrids/3/"));
			
			entry.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Hybrids/4/"));
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-Eisen!");
			e.printStackTrace();
		}
		
		//	similarity checking, sorting & taking first 10 items 
		SmartPricing.compareSimilarity(entry,File);
	}

}
