package com.dcommerce.threads2;

import java.io.IOException;
import java.util.ArrayList;

import com.dcommerce.database.DatabaseQuery;
import com.dcommerce.pricing.SmartPricing;
import com.dcommerce.scrapper.Entry;

public class Zelte extends Thread {
	
	@Override
	public void run() {

		//	fetching all the products from the database and saving in a csv file
		String Statement = "SELECT shop_item2.description, shop_item2.item_no FROM shop_item2 INNER JOIN shop_category2 "
				+ "ON shop_item2.main_category_line_no = shop_category2.line_no "
				+ "WHERE shop_category2.parent_line_no = 30000 ";
		
		String File = "Zelte.csv";
		try {
			DatabaseQuery.returnResult(Statement, File);
		} catch (IOException e) {
			System.out.println("Error in fetching data from the database-Eisen!");
			e.printStackTrace();
		}

		
		//	web-scraping
		ArrayList<Entry> entry = new ArrayList<Entry>();
		
		//	similarity checking, sorting & taking first 5 items 
		SmartPricing.compareSimilarity(entry,File);
	}

}
