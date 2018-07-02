package com.dcommerce.pricing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.similarity.JaccardIndex;
import com.dcommerce.database.*;

public class SmartPricing {

	public static void compareSimilarity(ArrayList<Entry> entry, String File) {
		// retrieving data from existing csv file
		String line = "";
		String cvsSplitBy = ",";

		String text1;
		String text2;
		String item_no;
		try (BufferedReader br = new BufferedReader(new FileReader(File))) {

			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] product = line.split(cvsSplitBy);
				// read the item_no also and save it
				item_no = product[1];
				text1 = product[0];
				System.out.println("Similar products for: " + text1); // TODO : delete
				// I can store the name and the id here
				//But how to pass???
				
				for (int i = 0; i < entry.size(); i++) {
					Entry st = entry.get(i);
					text2 = st.getTitle();
					double result = JaccardIndex.getJaccardIndex(text1, text2);
					st.setScore(result); // Assignment of the similarity score
				}
				//SmartPricing.suggestPrice(entry);
				DatabaseQuery.insertData(entry,item_no);//Here we can pass the item_no
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
//	public static void suggestPrice(ArrayList<Entry> entry) {
//		// sorting the product list according to their similarity score
//		Collections.sort(entry, Entry.ScoreDiff);
//
//		// taking only first 10 similar items
//		//TODO: change the var i into a meaningful name like max
//		for (int i = 0; i < 10; i++) {
//			Entry str = entry.get(i);
//			//System.out.println(str.getTitle() + " " + str.getScore() + " " + str.getPrice1() + " " + str.getPrice2());
//			// here the data will be inserted 
//			String title = str.getTitle();
//			double base_price = str.getPrice1();
//			double discount_price = str.getPrice2();
//			String Statement = "INSERT INTO pricing_module (description, base_price, discount_price) VALUES ('"+title+"', '"+base_price+"', '"+discount_price+"')"; 
//			
//			//DatabaseQuery.insertData(Statement);
//		}
//	}
}
