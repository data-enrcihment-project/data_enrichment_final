package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.dcommerce.database.DatabaseQuery;
import com.dcommerce.database.Products;
import com.dcommerce.pricing.SmartPricing;
import com.dcommerce.scrapper.EbayCallService;
import com.dcommerce.scrapper.Entry;

public abstract class CategorisedProducts {

	// every concrete class will implement its own webScrapping class
	abstract public ArrayList<Entry> webScrapping(ArrayList<Entry> entry);

	public void fetchData(String lineNo) {

		ArrayList<Entry> scrappedProductList = new ArrayList<Entry>();
		scrappedProductList = webScrapping(scrappedProductList);

		ArrayList<Products> existingProductList = new ArrayList<Products>();
		DatabaseQuery db = new DatabaseQuery();
		existingProductList = db.showProductsOfSpecificCategory(lineNo);

		enrichment(existingProductList, scrappedProductList);
	}

	public void enrichment(ArrayList<Products> existingProductList, ArrayList<Entry> scrappedProductList) {

		for (int i = 0; i < existingProductList.size(); i++) {

			Products pt = existingProductList.get(i);

			String text1 = pt.getDescription();
			String item_no = pt.getItem_no();

			String psDescription = text1;
			String globalID = "EBAY-DE";

			ArrayList<Entry> ebayProductList = new ArrayList<Entry>();
			ebayProductList = EbayCallService.GetEbayPrice(psDescription, globalID);

			ArrayList<Entry> combined = new ArrayList<Entry>();
			combined = scrappedProductList;
			combined.addAll(ebayProductList);

			combined = SmartPricing.compareSimilarity(text1, combined);

			Collections.sort(combined, Entry.ScoreDiff);

			try {
				DatabaseQuery data = new DatabaseQuery();
				data.insertData(combined, item_no);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Problem with insertData() method");
			}

		}
	}
}
