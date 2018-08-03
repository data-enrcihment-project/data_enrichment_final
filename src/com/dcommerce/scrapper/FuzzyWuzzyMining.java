package com.dcommerce.scrapper;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class FuzzyWuzzyMining {
	
	public static double GetItemDescrRatio(String dbItemDescr,String ebayItemDescr)
	{
		double getRatio = FuzzySearch.tokenSortPartialRatio(dbItemDescr, ebayItemDescr);
		
		return getRatio;
	}
}
