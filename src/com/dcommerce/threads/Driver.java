package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class Driver extends CategorisedProducts  implements Runnable {

	private String lineNo;
	
	public Driver(String lineNo) {
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
					.getProducts("https://www.golflaedchen.de/Golfschlaeger-Golf-Driver/?p=1");

			scrappedProductList
					.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golfschlaeger-Golf-Driver/?p=2"));

			scrappedProductList
					.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschlaeger/driver/?p=1"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Driver/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Driver/2/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Driver/3/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Driver/4/"));

		} catch (IOException e) {
			System.out.println("Error in webscrapping-Driver!");
			e.printStackTrace();
		}

		return scrappedProductList;
	}
}
