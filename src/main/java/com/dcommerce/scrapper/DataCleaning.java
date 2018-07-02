package com.dcommerce.scrapper;

public class DataCleaning {
	
	public static String cleanTitle(String title) {
		title = title.replaceAll("\\bArtikel-Nr\\b.:\\s(\\d)*", "");
		return title;
	}
	
	public static String cleanPrice(String value) {
		value = value.replaceAll("[^0-9,.]", "");
		value = value.replace(".", "");
		value = value.replace(',', '.');
		return value;
	}
	
	public static double stringToDouble(String valString) {
		double valDouble;
		if (valString != null && !valString.isEmpty()) {
			valDouble = Double.parseDouble(valString);
		} else {
			valDouble = 0.0;
		}
		return valDouble;
	}

}
