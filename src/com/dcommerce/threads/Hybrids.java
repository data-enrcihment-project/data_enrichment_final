package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;

import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

//Done!
public class Hybrids extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Hybrids(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public void run() {

		fetchData(lineNo);

	}

	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {
		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Hybrid-Golfschlaeger/?p=1");

			scrappedProductList
					.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Hybrid-Golfschlaeger/?p=2"));

			scrappedProductList
					.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschlaeger/hybrid/?p=1"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Hybrids/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Hybrids/2/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Hybrids/3/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Hybrids/4/"));

		} catch (IOException e) {
			System.out.println("Error in webscrapping-Hybrids!");
			e.printStackTrace();
		}

		return scrappedProductList;
	}

}
