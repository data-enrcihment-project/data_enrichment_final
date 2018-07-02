package com.dcommerce.scrapper;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class takes the object reference of ArrayList from any implementation
 * class and prints out all the products, prices & score.
 * 
 * @author Mushfiqul Huda
 */
//TODO: delete before packaging
public class DisplayData {

	public static void displayData(ArrayList<Entry> entry) {
		Iterator<Entry> itr = entry.iterator();

		// traverse elements of ArrayList object
		while (itr.hasNext()) {
			Entry st = (Entry) itr.next();
			System.out.println(st.getTitle() + " " + st.getPrice1() + " " + st.getPrice2() + " " + st.getScore());
		}
	}
}
