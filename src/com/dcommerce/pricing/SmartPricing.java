package com.dcommerce.pricing;


import java.util.ArrayList;
import com.dcommerce.scrapper.Entry;
import com.dcommerce.similarity.JaccardIndex;


public class SmartPricing {

	public static ArrayList<Entry> compareSimilarity(String text1, ArrayList<Entry> entry) {

		for (int i = 0; i < entry.size(); i++) {
			
			Entry st = entry.get(i);
			
			String text2 =  st.getTitle();
			
			double result = JaccardIndex.getJaccardIndex(text1, text2);
			
			st.setScore(result); // Assignment of the similarity score
		}
		
		return entry;
	}
}