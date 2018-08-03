package com.dcommerce.threads;

import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.BestPreisGolf;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.scrapper.GolfUndGuenstig;
import com.dcommerce.scrapper.Golflaedchen;

public class Messtechnik extends CategorisedProducts implements Runnable {

	private String lineNo;
	
	public Messtechnik(String lineNo){
		this.lineNo = lineNo;
	}
	
	
	@Override
	public void run() {

		fetchData(lineNo);

	}

		
	@Override
	public ArrayList<Entry> webScrapping(ArrayList<Entry> scrappedProductList) {

		try {
			scrappedProductList = Golflaedchen.getProducts("https://www.golflaedchen.de/Distanzmesser/");

			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Zubehoer/GPS-Entfernungsm/"));
			
			scrappedProductList.addAll(GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Zubehoer/GPS-Entfernungsm/2/"));
			
			
		} catch (IOException e) {
			System.out.println("Error in webscrapping-Messtechnik!");
			e.printStackTrace();
		}
		
		return scrappedProductList;
	}


}
