package com.models.pkg;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ebay.services.finding.SearchItem;

import am.ik.aws.apa.handler.AwsHandlerResolver;
import am.ik.aws.apa.jaxws.AWSECommerceService;
import am.ik.aws.apa.jaxws.AWSECommerceServicePortType;
import am.ik.aws.apa.jaxws.Item;
import am.ik.aws.apa.jaxws.ItemLookup;
import am.ik.aws.apa.jaxws.ItemLookupRequest;
import am.ik.aws.apa.jaxws.ItemLookupResponse;
import am.ik.aws.apa.jaxws.ItemSearch;
import am.ik.aws.apa.jaxws.ItemSearchRequest;
import am.ik.aws.apa.jaxws.ItemSearchResponse;
import am.ik.aws.apa.jaxws.Items;




public class AmazonCall {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static Map<Integer, Object> CallAmazonApi(String psDescription,String globalID)
	{

		String awsAccessKeyID = "AKIAJGNN4WCXDEYYLG6A";
		String test = "md074c-21"; 

		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver("in+WiNhkEcTJY8NF1rozgORBiE4VdgUnrx7dSWhw"));

		AWSECommerceServicePortType port = null;
		System.out.println(globalID.equals("AMAZON-DE")+"a22");
		if(globalID.equals("AMAZON-DE"))
		{
			port = service.getAWSECommerceServicePortDE();			
		}
		else if(globalID.equals("AMAZON-US"))
		{
			port = service.getAWSECommerceServicePort();
		}
		
		ItemSearchRequest itemRequest = new ItemSearchRequest();

		// Fill in the request object:
		itemRequest.setSearchIndex("All");//Should be defined
		itemRequest.setKeywords(psDescription);//Should be defined
		//itemRequest.setTitle(psDescription);
		//SearchIndex=Apparel
		//itemRequest.setAvailability("Availability");//for available item
		itemRequest.getResponseGroup().add("Small");
		itemRequest.getResponseGroup().add("ItemAttributes");
		itemRequest.getResponseGroup().add("Offers");
		itemRequest.getResponseGroup().add("Images");
		//itemRequest.getResponseGroup().add("Reviews");//ItemLookup
		//itemRequest.getResponseGroup().add("Similarities");//ItemLookup Discover
		//itemRequest.getResponseGroup().add("RelatedItems");//ItemLookup Related items
		itemRequest.setItemPage(BigInteger.valueOf(1L));
		
		//SimilarityLookup for similar item

		ItemSearch ItemElement = new ItemSearch();
		ItemElement.setAWSAccessKeyId(awsAccessKeyID);
		ItemElement.setAssociateTag(test);
		ItemElement.getRequest().add(itemRequest);

		ItemSearchResponse response = port.itemSearch(ItemElement);
		

		//ItemAttribute.getFeatures
		////////
		//System.out.println(response);
		
		 List<Items> items = response.getItems(); 
		 
		
		 
		 int count = response.getItems().size();
		System.out.println(count);
		 
		Map<Integer, Object> row = null;
		 
		try {
			row = resultSetToList(items,count,psDescription);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
		 return row;
	}
	
	@SuppressWarnings({ "null" })
	public static String CallItemLookUp(String psDescription,String globalID,String asin)
	{
		String awsAccessKeyID = "AKIAJGNN4WCXDEYYLG6A";
		String test = "md074c-21"; 

		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver("in+WiNhkEcTJY8NF1rozgORBiE4VdgUnrx7dSWhw"));

		AWSECommerceServicePortType port = null;
		
		if(globalID.equals("AMAZON-DE"))
		{
			System.out.println("For DE");
			port = service.getAWSECommerceServicePortDE();			
		}
		else if(globalID.equals("AMAZON-US"))
		{
			System.out.println("FOR US");
			port = service.getAWSECommerceServicePort();
		}
		
		ItemLookupRequest lookupReq = new ItemLookupRequest();
		//lookupReq.setSearchIndex(psDescription);
		//lookupReq.getItemId().add(asin);
		lookupReq.setIdType("ASIN");
		lookupReq.getItemId().add(asin);
		//lookupReq.getResponseGroup().add("Small");
		//lookupReq.getResponseGroup().add("Reviews");//ItemLookup
		//lookupReq.getResponseGroup().add("Images");
		lookupReq.getResponseGroup().add("Offers");
		
		ItemLookup lookupitem = new ItemLookup();
		lookupitem.setAWSAccessKeyId(awsAccessKeyID);
		lookupitem.setAssociateTag(test);
		lookupitem.getRequest().add(lookupReq);
		
		ItemLookupResponse responseLook =port.itemLookup(lookupitem);
		 
		System.out.println(responseLook);
		int count = responseLook.getItems().size();
		List<Items> itemsLookUp = responseLook.getItems();
		
			
		Map<Integer, Object> row = null;
		
		//try {
			//row = resultSetToList(itemsLookUp,count,psDescription);
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		List<Object> obj = new ArrayList<Object>();
		///////////////
		
		for (Items itemList : itemsLookUp) {
			
			System.out.println(itemList.getItem().toString());
		    for (Item itemObj : itemList.getItem()) {
		        
		    
		        System.out.println(itemObj.getCustomerReviews());
		        
		        obj.add(itemObj);
		    }
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		    // convert user object to json string and return it 
		     try {
				json =  mapper.writeValueAsString(row);
				System.out.println(json);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return "";
	}
	
	public static List<Items> GetAmazonItemObject(String psDescription,String globalID)
	{
		String awsAccessKeyID = "AKIAJGNN4WCXDEYYLG6A";
		String test = "md074c-21"; 

		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver("in+WiNhkEcTJY8NF1rozgORBiE4VdgUnrx7dSWhw"));

		AWSECommerceServicePortType port = null;
		System.out.println(globalID.equals("AMAZON-DE"));
		if(globalID.equals("AMAZON-DE"))
		{
			port = service.getAWSECommerceServicePortDE();			
		}
		else if(globalID.equals("AMAZON-US"))
		{
			port = service.getAWSECommerceServicePort();
		}
		
		ItemSearchRequest itemRequest = new ItemSearchRequest();

		// Fill in the request object:
		itemRequest.setSearchIndex("All");//Should be defined
		itemRequest.setKeywords("MSI");//Should be defined
		//itemRequest.setTitle(psDescription);
		//SearchIndex=Apparel
		//itemRequest.setAvailability("Availability");//for available item
		itemRequest.getResponseGroup().add("Small");
		itemRequest.getResponseGroup().add("ItemAttributes");
		itemRequest.getResponseGroup().add("Offers");
		itemRequest.getResponseGroup().add("Images");
		itemRequest.getResponseGroup().add("Reviews");//ItemLookup
		itemRequest.getResponseGroup().add("Similarities");//ItemLookup Discover
		itemRequest.getResponseGroup().add("RelatedItems");//ItemLookup Related items
		itemRequest.setItemPage(BigInteger.valueOf(1L));
		
		//SimilarityLookup for similar item

		ItemSearch ItemElement = new ItemSearch();
		ItemElement.setAWSAccessKeyId(awsAccessKeyID);
		ItemElement.setAssociateTag(test);
		ItemElement.getRequest().add(itemRequest);

		ItemSearchResponse response = port.itemSearch(ItemElement);
		
/*
		ItemLookupRequest lookupReq = new ItemLookupRequest();
		lookupReq.setSearchIndex("MSI");
		//lookupReq.setCondition("New");
		lookupReq.getResponseGroup().add("ItemAttributes");
		lookupReq.getResponseGroup().add("Offers");
		
		
		ItemLookup lookupitem = new ItemLookup();
		lookupitem.setAWSAccessKeyId(awsAccessKeyID);
		lookupitem.setAssociateTag(test);
		lookupitem.getRequest().add(lookupReq);
		
		ItemLookupResponse response =port.itemLookup(lookupitem);
		
		*/
		
		
		
		
		System.out.println(response);
		
		 return response.getItems(); 
	}
	
	public static Map<String, Double> GetAmazonItemPrice(String psDescription,String globalID) {
		
		Double previousRatio=0.0;
		List<Items> itemLists = GetAmazonItemObject(psDescription,globalID);
		 Map<String, Double> pricingRow  = new HashMap<String, Double>();
		Item selectedItem = null;
		Integer rowCount = 0;
		
		 for (Items itemList : itemLists) {
				
			    for (Item itemObj : itemList.getItem()) { 
			        
			        Double  ratio= FuzzyWuzzyMining.GetItemDescrRatio(psDescription,itemObj.getItemAttributes().getTitle().toString());
			        if(previousRatio>60)
					 { 
						 previousRatio = ratio; 
						 pricingRow.put(Integer.toString(rowCount++), Double.parseDouble(itemObj.getOfferSummary().getLowestNewPrice().getFormattedPrice()));
					 }
			        /*if(previousRatio==0.0)
					 { 
						 previousRatio = ratio; 
					 }
			        else if(previousRatio < ratio)
					 {
						 previousRatio = ratio;
						 selectedItem = itemObj;//itemObj.getOfferSummary().getLowestNewPrice().getFormattedPrice() 
					 }*/			        
			    }
			}
		return pricingRow;
	}
	
	@SuppressWarnings("null")
	public static Map<Integer, Object> resultSetToList(List<Items> searchItems,int count, String psDescr) throws SQLException {
	    
	    ////Important Arrays
	    
	    //for snding to client
	    Map<Integer, Object> row  = new HashMap<Integer, Object>();
	    
	    //List<SearchItem> details = searchItems;
	    Map<Integer, Object> details = new HashMap<Integer, Object>();
    	
	    BigInteger itemCount = null;
	    ///////
	    int filteredListCount=0;
	    /////////
	    for (Items itemList : searchItems) {
			
	    	itemCount = itemList.getTotalResults();
			System.out.println(itemList.getItem().toString());
		    for (Item itemObj : itemList.getItem()) {
		        /*System.out.println(itemObj.getItemAttributes().getBrand());
		        System.out.println(itemObj.getItemAttributes().getIssuesPerYear());
		        
		        System.out.println(itemObj.getItemAttributes().getColor());
		        System.out.println(itemObj.getItemAttributes().getItemPartNumber());
		        System.out.println(itemObj.getItemAttributes().getModel().toString());
		        System.out.println(itemObj.getItemAttributes().getIssuesPerYear());
		        System.out.println(itemObj.getItemAttributes().getTitle());
		        */
		    	 System.out.println(itemObj.getASIN());
		    	 System.out.println(itemObj.getCustomerReviews());
		    	 System.out.println("ItemLookUpStarted");
		    	
		    	//CallItemLookUp(psDescr,"AMAZON-DE",itemObj.getASIN());
		    	System.out.println("ItemLookUpEnded");
		    	
		        Double  ratio= FuzzyWuzzyMining.GetItemDescrRatio(psDescr,itemObj.getItemAttributes().getTitle().toString());
		        if(ratio >= 65)
	            {	
		        	filteredListCount++;
	            	row.put(filteredListCount, itemObj);
	            	details.put(filteredListCount,itemObj);
	            }
		        
		    }
		}
		
	    System.out.println(itemCount);
	    
	        try {
	        	details.put(0,filteredListCount);
	        	row.put(0,filteredListCount);
	        	WriteSearchItemToXML(details, psDescr +filteredListCount+".xml");//WriteSearchItemToXML(details, psDescr +count1+".xml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    return row;
	}
	
	public static void WriteSearchItemToXML(Map<Integer, Object> item, String filename) throws Exception{
		 
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
		    // convert user object to json string and return it 
		     json =  mapper.writeValueAsString(item);
		}catch(Exception e)
		{
			
			throw e;
		}
		 
		 
		 FileWriter writer = null;
		 writer = new FileWriter("gen1.json");
		 writer.write(json);
		 writer.close();
		 System.out.println(json);
	    }
	
}
