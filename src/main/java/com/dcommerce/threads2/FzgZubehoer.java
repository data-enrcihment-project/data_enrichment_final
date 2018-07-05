package com.dcommerce.threads2;

import java.io.IOException;
import java.util.ArrayList;

import com.dcommerce.database.DatabaseQuery;
import com.dcommerce.pricing.SmartPricing;
import com.dcommerce.scrapper.Entry;

public class FzgZubehoer extends Thread{
	
	@Override
	public void run() {
		
		//	fetching all the products from the database and saving in a csv file
		String Statement = "SELECT shop_item.description, shop_item.item_no FROM shop_item INNER JOIN shop_category "
				+ "ON shop_item.main_category_line_no = shop_category.line_no "
				+ "WHERE shop_category.parent_line_no = 1192500 ";
		
		String File = "Fzg.csv";
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
