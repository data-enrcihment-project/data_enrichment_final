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
import am.ik.aws.apa.jaxws.SimilarityLookup;
import am.ik.aws.apa.jaxws.SimilarityLookupRequest;
import am.ik.aws.apa.jaxws.SimilarityLookupResponse;




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
		itemRequest.getResponseGroup().add("Reviews");//ItemLookup
		itemRequest.getResponseGroup().add("EditorialReview");//ItemLookup
		//itemRequest.getResponseGroup().add("Similarities");//ItemLookup Discover
		//itemRequest.getResponseGroup().add("RelatedItems");//ItemLookup Related items
		itemRequest.setItemPage(BigInteger.valueOf(1L));
		
		//SimilarityLookup for similar item

		ItemSearch ItemElement = new ItemSearch();
		ItemElement.setAWSAccessKeyId(awsAccessKeyID);
		ItemElement.setAssociateTag(test);
		ItemElement.getRequest().add(itemRequest);

		ItemSearchResponse response = port.itemSearch(ItemElement);
		
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
	
	
	public static Map<Integer, Object> CallItemLookUp(String asin,String type)
	{
		///for related or similar items
		String awsAccessKeyID = "AKIAJGNN4WCXDEYYLG6A";
		String test = "md074c-21"; 

		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver("in+WiNhkEcTJY8NF1rozgORBiE4VdgUnrx7dSWhw"));

		AWSECommerceServicePortType port = null;		
		port = service.getAWSECommerceServicePortDE();
		
		//for discover similarities
		ItemLookupRequest lookupReq = new ItemLookupRequest();
		
		if(type.equals("DiscoverSimilarItems"))//discover similar items 
		{ 
			System.out.println("Similarity");
			lookupReq.getResponseGroup().add("Similarities"); 
			lookupReq.setSearchIndex("All");
			lookupReq.setIdType("UPC");//UPC for discover similar items
			lookupReq.getItemId().add(asin);
			
		}				
		else if(type.equals("RelatedItems"))//for relateItems also this one
		{
			System.out.println("Called Related Items");
			lookupReq.getResponseGroup().add("RelatedItem,Small");
			lookupReq.setIdType("ASIN");
			lookupReq.getItemId().add(asin);
		}
		
		/*lookupReq.getResponseGroup().add("ItemAttributes");
		lookupReq.getResponseGroup().add("Offers");
		lookupReq.getResponseGroup().add("Images");
		lookupReq.getResponseGroup().add("Reviews");*/
		
		ItemLookup lookupitem = new ItemLookup();
		lookupitem.setAWSAccessKeyId(awsAccessKeyID);
		lookupitem.setAssociateTag(test);
		
		lookupitem.getRequest().add(lookupReq);
		
		ItemLookupResponse responseLook =port.itemLookup(lookupitem);
		
		System.out.println(responseLook.getItems().size());		
		
		System.out.println(responseLook.getItems());
		//int count = responseLook.getItems().size();
		Map<Integer, Object> similarRow = new HashMap<Integer, Object>();
		
		Integer rowCount = 0;
		for(Items itemsLookUp: responseLook.getItems())
		{
			System.out.println("GOT SMT RELATED");
			rowCount++;
			similarRow.put(rowCount, itemsLookUp);
		}
		
		similarRow.put(0,rowCount);
		     
		return similarRow;
	}
	
	public static Map<Integer, Object> CallItemSimilarLookUp(String asin,String type,String globalID)//String psDescription,String globalID,String asin
	{
		///for related or similar items
		String awsAccessKeyID = "AKIAJGNN4WCXDEYYLG6A";
		String test = "md074c-21"; 

		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver("in+WiNhkEcTJY8NF1rozgORBiE4VdgUnrx7dSWhw"));

		AWSECommerceServicePortType port = null;
		
		if(globalID.equals("AMAZON-DE"))
		{
			port = service.getAWSECommerceServicePortDE();			
		}
		else if(globalID.equals("AMAZON-US"))
		{
			port = service.getAWSECommerceServicePort();
		}
		
		//for similarities
		SimilarityLookupRequest simlarReq = new SimilarityLookupRequest();
		//simlarReq. setIdType("ASIN");
		simlarReq.getResponseGroup().add("Small");
		simlarReq.getResponseGroup().add("ItemAttributes");
		simlarReq.getResponseGroup().add("Offers");
		simlarReq.getResponseGroup().add("Images");
		simlarReq.getResponseGroup().add("Reviews");
		
		simlarReq.getItemId().add(asin);
		
		SimilarityLookup lookupitem = new SimilarityLookup();
		lookupitem.setAWSAccessKeyId(awsAccessKeyID);
		lookupitem.setAssociateTag(test);
		lookupitem.getRequest().add(simlarReq);
		
		SimilarityLookupResponse responseLook =port.similarityLookup(lookupitem);
		System.out.println("Called SimilarityLookUP");
		System.out.println(responseLook.getItems());
		
		
		//int count = responseLook.getItems().size();
		Map<Integer, Object> similarityRow = new HashMap<Integer, Object>();
		
		Integer rowCount = 0;
		for(Items itemsLookUp: responseLook.getItems())
		{
			rowCount++;
			System.out.println("GOT SMT");
			similarityRow.put(rowCount, itemsLookUp);
		}
		
		similarityRow.put(0,rowCount);
		     
		return similarityRow;
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
