package com.models.pkg;

import java.io.FileWriter;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	public static List<Items> GetAmazonItemObject(String psDescription,String globalID)
	{
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
	
	public static Item GetAmazonItemPrice(String psDescription,String globalID) {
		
		Double previousRatio=0.0;
		List<Items> itemLists = GetAmazonItemObject(psDescription,globalID);
		
		Item selectedItem = null;
		
		 for (Items itemList : itemLists) {
				
			    for (Item itemObj : itemList.getItem()) { 
			        
			        Double  ratio= FuzzyWuzzyMining.GetItemDescrRatio(psDescription,itemObj.getItemAttributes().getTitle().toString());
			        if(previousRatio==0.0)
					 { 
						 previousRatio = ratio; 
					 }
			        else if(previousRatio < ratio)
					 {
						 previousRatio = ratio;
						 selectedItem = itemObj;//itemObj.getOfferSummary().getLowestNewPrice().getFormattedPrice() 
					 }
			        
			    }
			}
		return selectedItem;
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
		        
		        
		        Double  ratio= FuzzyWuzzyMining.GetItemDescrRatio(psDescr,itemObj.getItemAttributes().getTitle().toString());
		        if(ratio >= 60)
	            {	
		        	System.out.println("INNNNN");
		        	filteredListCount++;
	            	row.put(filteredListCount, itemObj);
	            	details.put(filteredListCount,itemObj);
	            }
		        
		    }
		}
		
	    System.out.println(itemCount);
	    
	        try {
	        	details.put(0,itemCount);
	        	row.put(0,itemCount);
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
