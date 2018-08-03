package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class GolfschuheHerren extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public GolfschuheHerren(String lineNo){
		
		this.lineNo = lineNo;
	}
	
	
	@Override
	public void run() {

		fetchData(lineNo);

	}
		

		
	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {
		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Herren-Golfschuhe/?p=1");

			scrappedProductList.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Herren-Golfschuhe/?p=2"));

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschuhe/herren-golfschuhe/?p=1"));

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschuhe/herren-golfschuhe/?p=2"));
			
			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Herrenbekleidung/Schuhe/"));
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-GolfschuheHerren!");
			e.printStackTrace();
		}
		
		return scrappedProductList;
	}

}
