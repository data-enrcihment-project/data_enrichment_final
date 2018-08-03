package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.Golflaedchen;

public class Handschuhe extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Handschuhe(String lineNo){
		this.lineNo = lineNo;
	}
	
	@Override
	public void run() {

		fetchData(lineNo);
		
	}
		

		
	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {
		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Golfhandschuhe/?p=1");

			scrappedProductList.addAll(Golflaedchen.getProducts("https://www.golflaedchen.de/Golfhandschuhe/?p=2"));

			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfhandschuhe/?p=1"));
			
			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfhandschuhe/?p=2"));
			
			scrappedProductList.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfhandschuhe/?p=3"));
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-Handschuhe!");
			e.printStackTrace();
		}
		
		return scrappedProductList;
	}


}
