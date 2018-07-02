package com.dcommerce.similarity;

import org.apache.commons.text.similarity.JaccardSimilarity;

/**
 * This class implements the Jaccard Index by using Apache Commons library.
 * 
 * @author mushfiqul.onee
 */

public class JaccardIndex {
	
	public static double getJaccardIndex(String s1, String s2) {
		
		JaccardSimilarity jaccard = new JaccardSimilarity();
		double res =  jaccard.apply(s1, s2);
		return res;
	}
}
