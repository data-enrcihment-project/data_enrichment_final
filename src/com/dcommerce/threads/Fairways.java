package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;

import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;


public class Fairways extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Fairways(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public void run() {

		fetchData(lineNo);

	}

	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {
		try {
			scrappedProductList = Golflaedchen
					.getProducts("https://www.golflaedchen.de/Golfschlaeger-Fairwayholz/?p=1");

			scrappedProductList
					.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golfschlaeger-Fairwayholz/?p=2"));

			scrappedProductList.addAll(
					BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschlaeger/fairwayhoelzer/?p=1"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Fairways/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Fairways/2/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Fairways/3/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Fairways/4/"));

		} catch (IOException e) {
			System.out.println("Error in webscrapping-Fairways!");
			e.printStackTrace();
		}

		return scrappedProductList;
	}
}
