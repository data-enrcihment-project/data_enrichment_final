package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.Golflaedchen;


public class Schuhpflege extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Schuhpflege(String lineNo){
		
		this.lineNo = lineNo;
	}
	
	
	@Override
	public void run() {

		fetchData(lineNo);

	}
		
	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {

		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Schuhpflege/");

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschuhe/schuhzubehoer/"));
			
			//nothing has been found in www.golfundguenstig.de
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-Schuhpflege!");
			e.printStackTrace();
		}
		
		return scrappedProductList;
	}


}
