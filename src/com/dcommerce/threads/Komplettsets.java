package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class Komplettsets extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Komplettsets(String lineNo){
		this.lineNo = lineNo;
	}
	
	
	@Override
	public void run() {

		fetchData(lineNo);

	}
		

		
	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {
		
		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Golfschlaeger-Golfset/");

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschlaeger/komplettsets/"));

			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaegersaetze/Komplettsaetze/"));
			
			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaegersaetze/Komplettsaetze/2/"));
			
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-Komplettsets!");
			e.printStackTrace();
		}
		
		return scrappedProductList;
	}


}
