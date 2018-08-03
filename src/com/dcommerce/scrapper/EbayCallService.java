package com.dcommerce.scrapper;

import java.util.ArrayList;
import java.util.List;
import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsAdvancedRequest;
import com.ebay.services.finding.FindItemsAdvancedResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.SearchItem;

public class EbayCallService {

	public static List<SearchItem> GetEbayItemObject(String psDescription, String globalID) {

		ClientConfig config = new ClientConfig();
		config.setApplicationId("AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231");
		config.setGlobalId(globalID);
		// create a service client
		FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);
		// create request object
		FindItemsAdvancedRequest request = new FindItemsAdvancedRequest();
		request.setKeywords(psDescription);

		FindItemsAdvancedResponse res = serviceClient.findItemsAdvanced(request);

		return res.getSearchResult().getItem();
	}

//	public static ArrayList<Entry> GetEbayPriceFuzzy(String psDescription, String globalID) {
//
//		List<SearchItem> itemList = GetEbayItemObject(psDescription, globalID);
//		ArrayList<Entry> entry = new ArrayList<Entry>();
//		
//		Double previousRatio = 0.0;
//		for (SearchItem item : itemList) {
//			
//			Double ratio = FuzzyWuzzyMining.GetItemDescrRatio(psDescription, item.getTitle().toString());
//			if (previousRatio > 60) {
//				previousRatio = ratio;
//				double score = 0.0;
//				String title = item.getTitle().toString();
//				double price1 = item.getSellingStatus().getCurrentPrice().getValue();
//				double price2 = item.getSellingStatus().getCurrentPrice().getValue();
//				String shop = "Ebay";
//				Entry s1 = new Entry(title, price1, price2, score, shop);
//				entry.add(s1);
//			}
//		}
//
//		return entry;
//	}

	// This method searches products with title from eBay and fetches them
	public static ArrayList<Entry> GetEbayPrice(String psDescription, String globalID) {

		List<SearchItem> itemList = GetEbayItemObject(psDescription, globalID);
		ArrayList<Entry> entry = new ArrayList<Entry>();

		for (SearchItem item : itemList) {
			double score = 0.0;
			String title = item.getTitle().toString();
			double price1 = item.getSellingStatus().getCurrentPrice().getValue();
			double price2 = item.getSellingStatus().getCurrentPrice().getValue();
			String shop = "Ebay";
			Entry s1 = new Entry(title, price1, price2, score, shop);
			entry.add(s1);
		}

		return entry;
	}
}
