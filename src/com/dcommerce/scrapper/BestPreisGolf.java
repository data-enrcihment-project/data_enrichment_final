package com.dcommerce.scrapper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/*
 * This class fetches the products from "www.best-preis-golf.de". The
 * products are listed inside <div class="product--info">. The loop iterates
 * through all the div tags and grabs only the titles and the prices.
 */
public class BestPreisGolf {

	public static ArrayList<Entry> getProducts(String URL) throws IOException {

		final Document document = Jsoup.connect(URL).get();

		ArrayList<Entry> entry = new ArrayList<Entry>(); // ArrayList of our custom class type

		for (Element row : document.select("div.product--info")) {
			String title = row.select("a.product--title").text();
			String value1 = row.select("span.price--pseudo").text();
			String value2 = row.select("span.price--default").text();

			/* Data cleaning process using regular expressions. */
			title = DataCleaning.cleanTitle(title);
			value1 = DataCleaning.cleanPrice(value1);
			value2 = DataCleaning.cleanPrice(value2);

			// Converting price from string to double
			double price1 = DataCleaning.stringToDouble(value1);
			double price2 = DataCleaning.stringToDouble(value2);

			// This value will be changed during the text similarity checking
			double score = 0.0;
			
			String shop = "Best Preis Golf";

			Entry s1 = new Entry(title, price1, price2, score,shop);
			entry.add(s1);
		}

		return entry;
	}
}
