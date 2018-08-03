package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class GolfoutfitsSie extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public GolfoutfitsSie(String lineNo){
		this.lineNo = lineNo;
	}
	
	@Override
	public void run() {

		fetchData(lineNo);
	}

		
	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {
		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Golfbekleidung-fuer-Damen/?p=1");

			scrappedProductList.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golfbekleidung-fuer-Damen/?p=2"));

			scrappedProductList.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golfbekleidung-fuer-Damen/?p=3"));
			
			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfbekleidung/damenbekleidung/"));
			
			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfbekleidung/damenbekleidung/?p=2"));

			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Damenbekleidung/"));
			
			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Damenbekleidung/8/"));
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-GolfoutfitsSie!");
			e.printStackTrace();
		}
		
		return scrappedProductList;
	}
}
