package com.models.pkg;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class FuzzyWuzzyMining {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static double GetItemDescrRatio(String dbItemDescr,String ebayItemDescr)
	{
		double getRatio = FuzzySearch.tokenSortPartialRatio(dbItemDescr, ebayItemDescr);
		
		return getRatio;
	}
	
	public static void CheckRationSaveItem()
	{
		//Check for ration and the return true to save or not		
	}

}
