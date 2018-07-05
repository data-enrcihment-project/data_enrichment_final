package com.dcommerce.scrapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public static Map<String, Double> GetEbayData() {
		try {
			// initialize service end-point configuration
			ClientConfig config = new ClientConfig();
			config.setApplicationId("AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231");
			config.setGlobalId("EBAY-DE");
			// create a service client
			FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);
			// create request object
			FindItemsAdvancedRequest request = new FindItemsAdvancedRequest();
			request.setKeywords("MSI GP72");

			FindItemsAdvancedResponse res = serviceClient.findItemsAdvanced(request);

			Map<String, Double> dictionary = new HashMap<String, Double>();

			List<SearchItem> items = res.getSearchResult().getItem();

			for (SearchItem item : items) {
				System.out.println(item.getItemId());
				System.out.println(item.getSellingStatus().getCurrentPrice().getCurrencyId().toString());
				item.getSellingStatus().getCurrentPrice().getValue();

				dictionary.put(item.getTitle().toString(), item.getSellingStatus().getCurrentPrice().getValue());

			}

			return dictionary;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public static Map<Integer, Object> GetEbayDataWithDescr(String psDescription, String globalID) {
		try {
			// initialize service end-point configuration
			ClientConfig config = new ClientConfig();
			config.setApplicationId("AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231");
			config.setGlobalId(globalID);
			// create a service client
			FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);
			// create request object
			FindItemsAdvancedRequest request = new FindItemsAdvancedRequest();
			request.setKeywords(psDescription);

			FindItemsAdvancedResponse res = serviceClient.findItemsAdvanced(request);

			List<SearchItem> items = res.getSearchResult().getItem();
			int count = res.getSearchResult().getCount();

			Map<Integer, Object> row = resultSetToList(items, count, psDescription);

			return row;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public static Map<Integer, Object> resultSetToList(List<SearchItem> searchItems, int count, String psDescr)
			throws SQLException {

		ClientConfig config = new ClientConfig();

		config.setApplicationId("AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231");

		new ArrayList<Map<String, Object>>();

		new ArrayList<Map<Double, Object>>();

		int filteredListCount = 0;

		Map<Integer, Object> row = new HashMap<Integer, Object>();

		// List<SearchItem> details = searchItems;
		Map<Integer, Object> details = new HashMap<Integer, Object>();

		for (SearchItem item : searchItems) {
			{
				filteredListCount++;
				details.put(filteredListCount, item);
				row.put(filteredListCount, item);
			}
		}

		System.out.println(filteredListCount);
		
		try {
		
			row.put(0, filteredListCount); // filtered
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return row;
	}

	@SuppressWarnings("unused")
	public static Map<String, Double> GetEbayItemPrice(String psDescription, String globalID) {

		Double previousRatio = 0.0;
		List<SearchItem> itemList = GetEbayItemObject(psDescription, globalID);

		Map<String, Double> pricingRow = new HashMap<String, Double>();

		Double selectedItem = null;
		Integer rowCount = 0;
		for (SearchItem item : itemList) {

			System.out.println(item.getSellingStatus().getCurrentPrice().getValue());
			System.out.println(item.getTitle().toString());

			Double ratio = FuzzyWuzzyMining.GetItemDescrRatio(psDescription, item.getTitle().toString());
			if (previousRatio > 60) {
				previousRatio = ratio;
				pricingRow.put(Integer.toString(rowCount++), item.getSellingStatus().getCurrentPrice().getValue());
			}

		}
		return pricingRow;
	}
	
	public static ArrayList<Entry> GetEbayPriceFuzzy(String psDescription, String globalID) {

		List<SearchItem> itemList = GetEbayItemObject(psDescription, globalID);
		ArrayList<Entry> entry = new ArrayList<Entry>();
		
		Double previousRatio = 0.0;
		for (SearchItem item : itemList) {
			
			Double ratio = FuzzyWuzzyMining.GetItemDescrRatio(psDescription, item.getTitle().toString());
			if (previousRatio > 60) {
				previousRatio = ratio;
				double score = 0.0;
				String title = item.getTitle().toString();
				double price1 = item.getSellingStatus().getCurrentPrice().getValue();
				double price2 = item.getSellingStatus().getCurrentPrice().getValue();
				Entry s1 = new Entry(title, price1, price2, score);
				entry.add(s1);
			}
		}

		return entry;
	}

	//This method searches products with title from eBay and fetches them
	public static ArrayList<Entry> GetEbayPrice(String psDescription, String globalID) {

		List<SearchItem> itemList = GetEbayItemObject(psDescription, globalID);
		ArrayList<Entry> entry = new ArrayList<Entry>();
	
		for (SearchItem item : itemList) {
				double score = 0.0;
				String title = item.getTitle().toString();
				double price1 = item.getSellingStatus().getCurrentPrice().getValue();
				double price2 = item.getSellingStatus().getCurrentPrice().getValue();
				Entry s1 = new Entry(title, price1, price2, score);
				entry.add(s1);
		}

		return entry;
	}
}
