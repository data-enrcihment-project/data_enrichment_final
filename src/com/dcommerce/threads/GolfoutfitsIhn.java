package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class GolfoutfitsIhn extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public GolfoutfitsIhn(String lineNo) {
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
					.getProducts("https://www.golflaedchen.de/Golfbekleidung-fuer-Herren/?p=1");

			scrappedProductList
					.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golfbekleidung-fuer-Herren/?p=2"));

			scrappedProductList
					.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golfbekleidung-fuer-Herren/?p=3"));

			scrappedProductList
					.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golfbekleidung-fuer-Herren/?p=4"));

			scrappedProductList.addAll(
					BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfbekleidung/herrenbekleidung/"));

			scrappedProductList.addAll(
					BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfbekleidung/herrenbekleidung/?p=2"));

			scrappedProductList.addAll(
					BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfbekleidung/herrenbekleidung/?p=3"));

			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Herrenbekleidung/"));

			scrappedProductList
					.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Herrenbekleidung/6/"));

		} catch (IOException e) {
			System.out.println("Error in webscrapping-GolfoutfitsIhn!");
			e.printStackTrace();
		}

		return scrappedProductList;
	}

}
