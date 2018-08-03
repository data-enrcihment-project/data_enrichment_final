package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class Accessories extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Accessories(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public void run() {
		
		fetchData(lineNo);
	}

	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {

		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Accessoires/?p=3");

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/zubehoer/?p=2"));

			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Zubehoer/Reisezubehoer/"));


		} catch (IOException e) {
			System.out.println("Error in webscrapping-Accessories!");
			e.printStackTrace();
		}

		return scrappedProductList;
	}

}