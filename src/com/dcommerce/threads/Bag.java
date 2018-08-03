package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;

import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class Bag extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Bag(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public void run() {

		fetchData(lineNo);

	}

	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {

		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Golfbags/?p=2");

			scrappedProductList.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golf-Trolleys/?p=2"));

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/bags/?p=1"));

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/trolleys/"));

			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Bags/4/"));

			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Trolleys/4/"));

		} catch (IOException e) {
			System.out.println("Error in webscrapping-Bag!");
			e.printStackTrace();
		}
		return scrappedProductList;
	}

}
