package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;
//Done!
public class Wedges extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Wedges(String lineNo){
		
		this.lineNo = lineNo;
	}
	

	@Override
	public void run() {

		fetchData(lineNo);

	}
		
		
	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {

		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Golfschlaeger-Golf-Wedges/");

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschlaeger/wedges/"));
			
			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Wedges/"));
						
			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Wedges/2/"));
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-Wedges!");
			e.printStackTrace();
		}
		
		return scrappedProductList;
	}

}
