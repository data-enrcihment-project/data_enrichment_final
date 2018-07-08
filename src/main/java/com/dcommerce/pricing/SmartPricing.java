package com.dcommerce.pricing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.dcommerce.scrapper.EbayCallService;
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
			System.out.println("Reading from csv");
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] product = line.split(cvsSplitBy);
				text1 = product[0];
				item_no = product[1];
				System.out.println("Similar products for: " + text1 + " with item_no: " + item_no ); // TODO : delete
				
				String psDescription = text1;
				String globalID = "EBAY-DE";
				entry = EbayCallService.GetEbayPrice(psDescription, globalID);
					
				for (int i = 0; i < entry.size(); i++) {
					Entry st = entry.get(i);
					text2 = st.getTitle();
					double result = JaccardIndex.getJaccardIndex(text1, text2);
					st.setScore(result); // Assignment of the similarity score
				}
				
				DatabaseQuery.insertData(entry,item_no);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void compareSimilarityShop2(ArrayList<Entry> entry, String File) {
		// retrieving data from existing csv file
		String line = "";
		String cvsSplitBy = ",";

		String text1;
		String text2;
		String item_no;
		try (BufferedReader br = new BufferedReader(new FileReader(File))) {
			System.out.println("Reading from csv");
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] product = line.split(cvsSplitBy);
				text1 = product[0];
				item_no = product[1];
				System.out.println("Similar products for: " + text1 + " with item_no: " + item_no ); // TODO : delete
				
				String psDescription = text1;
				String globalID = "EBAY-DE";
				entry = EbayCallService.GetEbayPrice(psDescription, globalID);
					
				for (int i = 0; i < entry.size(); i++) {
					Entry st = entry.get(i);
					text2 = st.getTitle();
					double result = JaccardIndex.getJaccardIndex(text1, text2);
					st.setScore(result); // Assignment of the similarity score
				}
				
				DatabaseQuery.insertDataShop2(entry,item_no);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}