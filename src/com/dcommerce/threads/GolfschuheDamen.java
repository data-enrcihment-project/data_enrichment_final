package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class GolfschuheDamen extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public GolfschuheDamen(String lineNo){
		this.lineNo = lineNo;
	}
	

	@Override
	public void run() {

		fetchData(lineNo);

	}
		
		
	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {
		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Damen-Golfschuhe/?p=1");

			scrappedProductList.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Damen-Golfschuhe/?p=2"));

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschuhe/damen-golfschuhe/?p=1"));
			
			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschuhe/damen-golfschuhe/?p=2"));

			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Damenbekleidung/Schuhe/2/"));
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-GolfschuheDamen!");
			e.printStackTrace();
		}
		
		return scrappedProductList;
	}

}
