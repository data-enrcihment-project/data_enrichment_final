package com.dcommerce.scrapper;

import java.io.IOException;
import java.util.ArrayList;

public class ScrapperWedges {

	public static void main(String[] args) throws IOException {
		ArrayList<Entry> entry = new ArrayList<Entry>();
//		entry = Golflaedchen.getProducts("https://www.golflaedchen.de/Herren-Eisen/?p=1");
//		entry.addAll(BestPreisGolf.getProducts("https://www.best-preis-golf.de/golfschlaeger/fairwayhoelzer/?p=1"));
		entry = GolfUndGuenstig.getProducts("https://www.golfundguenstig.de/Golfschlaeger/Einzeleisen/");
		DisplayData.displayData(entry);
	}
}
