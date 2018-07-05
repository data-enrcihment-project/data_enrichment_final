package com.dcommerce.scrapper;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class FuzzyWuzzyMining {
	
	public static double GetItemDescrRatio(String dbItemDescr,String ebayItemDescr)
	{
		double getRatio = FuzzySearch.tokenSortPartialRatio(dbItemDescr, ebayItemDescr);
		
		//System.out.println(dbItemDescr +"-----"+ebayItemDescr); 
		//System.out.println(getRatio); 
		
		return getRatio;
	}
	
	public static void CheckRationSaveItem()
	{
		//Check for ration and the return true to save or not
		
	}

}
