package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class Eisen extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Eisen(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public void run() {

		fetchData(lineNo);

	}

	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {
		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Herren-Eisen/?p=1");

			scrappedProductList.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Herren-Eisen/?p=2"));

			scrappedProductList
					.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschlaeger/eisensaetze/?p=1"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Einzeleisen/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Einzeleisen/2/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Einzeleisen/3/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Einzeleisen/4/"));

		} catch (IOException e) {
			System.out.println("Error in webscrapping-Eisen!");
			e.printStackTrace();
		}

		return scrappedProductList;
	}
}
