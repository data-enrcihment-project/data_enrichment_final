package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;

public class Halbsets extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Halbsets(String lineNo){
		this.lineNo = lineNo;
	}
	
	
	@Override
	public void run() {

		fetchData(lineNo);

	}
		
		
	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {
		try {
			scrappedProductList = GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaegersaetze/Halbsaetze/");
			
			//No products found in www.golflaedchen.de & www.best-preis-golf.de
			
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-Halbsets!");
			e.printStackTrace();
		}
		
		return scrappedProductList;
	}


}
