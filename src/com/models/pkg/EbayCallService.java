package com.models.pkg;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.image.RasterOp;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;

import org.codehaus.jackson.map.ObjectMapper;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.CategoryHistogram;
import com.ebay.services.finding.FindItemsAdvancedRequest;
import com.ebay.services.finding.FindItemsAdvancedResponse;
import com.ebay.services.finding.FindItemsByCategoryRequest;
import com.ebay.services.finding.FindItemsByKeywordsRequest;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.FindItemsByProductRequest;
import com.ebay.services.finding.FindItemsByProductResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.OutputSelectorType;
import com.ebay.services.finding.PaginationInput;
import com.ebay.services.finding.ProductId;
import com.ebay.services.finding.SearchItem;
import com.ebay.services.finding.SearchResult;

import com.ebay.sdk.*;
import com.ebay.sdk.call.GetFeedbackCall;
import com.ebay.sdk.call.GetItemCall;
import com.ebay.soap.eBLBaseComponents.*;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class EbayCallService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static List<SearchItem> GetEbayItemObject(String psDescription,String globalID)
	{
		 ClientConfig config = new ClientConfig();         
		 config.setApplicationId("AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231");
		 config.setGlobalId(globalID);
		//create a service client       
		 FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);    
		 //create request object  
		 FindItemsAdvancedRequest request =new FindItemsAdvancedRequest();
		 request.setKeywords(psDescription);
		 		 
		 FindItemsAdvancedResponse res= serviceClient.findItemsAdvanced(request);
		
		 Double db =0.0;
		 
		
		 return res.getSearchResult().getItem();
	}
	
	public static Map<String, Double> GetEbayData()
	{ 
		try {
		// initialize service end-point configuration 
		 ClientConfig config = new ClientConfig();         
		 config.setApplicationId("AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231");
		 config.setGlobalId("EBAY-DE");
		//create a service client       
		 FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);    
		 //create request object  
		 
		 
		 FindItemsAdvancedRequest request =new FindItemsAdvancedRequest();
		 request.setKeywords("MSI GP72");
		 
		 
		 
		 FindItemsAdvancedResponse res= serviceClient.findItemsAdvanced(request);
		
		 Double db =0.0;
		 
		 Map<String, Double> dictionary = new HashMap<String, Double>();
		 
		 List<SearchItem> items = res.getSearchResult().getItem(); 
		 
		 
		 for(SearchItem item : items) {           
			 System.out.println(item.getItemId()); 
			 System.out.println(item.getSellingStatus().getCurrentPrice().getCurrencyId().toString());
			 //System.out.println(item.getSellingStatus().getCurrentPrice().getValue());
			 db=item.getSellingStatus().getCurrentPrice().getValue();
			 
			 dictionary.put(item.getTitle().toString(), item.getSellingStatus().getCurrentPrice().getValue());
			 
		 }   
		 return dictionary;
		}catch(Exception e)
		{
			
			e.printStackTrace(); 
		}
		return null;
	}
	
	public static List<SearchItem> GetEbayItemObjectFast()
	{
		 ClientConfig config = new ClientConfig();         
		 config.setApplicationId("AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231");
		 config.setGlobalId("EBAY-US");
		//create a service client       
		 FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);    
		 //create request object  
		 FindItemsByKeywordsRequest request =new FindItemsByKeywordsRequest();
		 request.setKeywords("Harry Potter Order of pheonix");
		 
		 
		 request.getOutputSelector().add(OutputSelectorType.SELLER_INFO);
		 FindItemsByKeywordsResponse res= serviceClient.findItemsByKeywords(request);
		
		 Double db =0.0;
		 
		 List<Object> list =null;
		
		 for(SearchItem item :res.getSearchResult().getItem() )
		 {
			 
			 ObjectMapper mapper = new ObjectMapper();
				String json = "";
				try {
				    // convert user object to json string and return it 
				     json =  mapper.writeValueAsString(item);
				     System.out.println(json);
				}catch(Exception e)
				{
					
					try {
						throw e;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		 }
		 
		 
		 return res.getSearchResult().getItem();
	}
	
	
	public static Map<Integer, Object> GetEbayDataWithDescr(String psDescription,String globalID)
	{ 
		try {
		// initialize service end-point configuration 
		 ClientConfig config = new ClientConfig();         
		 config.setApplicationId("AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231");
		 config.setGlobalId(globalID);
		//create a service client       
		 FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);    
		 //create request object  
		 FindItemsAdvancedRequest request =new FindItemsAdvancedRequest();
		 request.setKeywords(psDescription);		 
		 request.getOutputSelector().add(OutputSelectorType.SELLER_INFO);		 
		 request.getOutputSelector().add(OutputSelectorType.STORE_INFO);
		 
		 FindItemsAdvancedResponse res= serviceClient.findItemsAdvanced(request);
		
		 Double db =0.0;
		 
		 List<SearchItem> items = res.getSearchResult().getItem(); 
		 int count = res.getSearchResult().getCount();
		 
		 Map<Integer, Object> row=resultSetToList(items,count,psDescription);
		 
		 //Feedback
		 
		 
		 ////
		 //FindItemsByProductRequest prodReq = new FindItemsByProductRequest();
		 
		 
		 return row;
		}catch(Exception e)
		{
			
			e.printStackTrace(); 
		}
		return null;
	}
	
	
	@SuppressWarnings("null")
	public static Map<Integer, Object> resultSetToList(List<SearchItem> searchItems,int count, String psDescr) throws SQLException {
	    
		////Feedback Object
		
		ClientConfig config = new ClientConfig();         
		 config.setApplicationId("AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231");
		 
		//create a service client       
		 
		
		//GetFeedbackRequestType req = new GetFeedbackRequestType();
		 
		///
	    List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
	    
	    List<Map<Double, Object>> ratioRow = new ArrayList<Map<Double, Object>>();
	    
	    int filteredListCount=0;
	    
	    List<HashMap<Integer, Object>> rowsadded=null;//adding to list
	    
	    //for snding to client
	    Map<Integer, Object> row  = new HashMap<Integer, Object>();
	    
	    //List<SearchItem> details = searchItems;
	    Map<Integer, Object> details = new HashMap<Integer, Object>();
    	
	        for (SearchItem item : searchItems) {
	        	
	        	//itemID
	        	//req.setItemID(item.getItemId());

	        	
	        	//for categories of this item
	        	//CategoryHistogram ch = new CategoryHistogram();
	        	//ch.set
	           //String a = item.getSellerInfo().getFeedbackRatingStar();
	        	//new object for every item
	        	//Ebay Feedback from ebay sdk but in another .java file Trading api. GetFeedback
	        	//Send Description of item to fuzzy mining and if it is more than 70 percent ratio then add to array list
	        	Double  ratio= FuzzyWuzzyMining.GetItemDescrRatio(psDescr,item.getTitle().toString());
	        	///or may be can which one i highest and select that and save those details to DB
	        	/*
	        	
	        	try {
	        		System.out.println("Calling Feedback");
					EbayFeedback.getFeedback(item.getItemId());
				} catch (ApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SdkException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	        	
	        	if(ratio >= 69)
	            {
	        		filteredListCount++;
	            	details.put(filteredListCount,item);
	            	row.put(filteredListCount, item);
	            }
	            //Writing to xml
	           //if more than 70 percent we will send data to client and show them in list
	        }
	        //TODO: setting this List item to get highest item and the object
	        //Object getObject = Collection.max(ratioRow);
	        
	        System.out.println(filteredListCount);
	        try {
	        	//details.put(0,count);
	        	row.put(0,filteredListCount);//filtered
	        	WriteSearchItemToXML(details, psDescr +filteredListCount+".xml");//WriteSearchItemToXML(details, psDescr +count1+".xml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    return row;
	}

	public static void WriteSearchItemToXML(Map<Integer, Object> item, String filename) throws Exception{
	   
		 
		 //String strJson =  new GsonBuilder().setPrettyPrinting().create().toJson(item,SearchItem.class);
		 
		
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
//		 XMLEncoder encoder =
//	           new XMLEncoder(
//	              new BufferedOutputStream(
//	                new FileOutputStream(filename)));
//	        
//	        encoder.writeObject(item);
//	        encoder.close();
//	        System.out.println(item.getSellingStatus().getCurrentPrice());
	    }
	
	
	@SuppressWarnings("unused")
	public static Map<String, Double> GetEbayItemPrice(String psDescription,String globalID) {
		
		Double previousRatio=0.0;
		List<SearchItem> itemList = GetEbayItemObject(psDescription,globalID);
		
		 Map<String, Double> pricingRow  = new HashMap<String, Double>();
		
		
		Double selectedItem = null;
		Integer rowCount = 0;
		for(SearchItem item : itemList) {           
			 System.out.println(item.getItemId()); 
			 System.out.println(item.getSellingStatus().getCurrentPrice().getCurrencyId().toString());
			 //System.out.println(item.getSellingStatus().getCurrentPrice().getValue());
			 item.getSellingStatus().getCurrentPrice().getValue();
			 
			 Double  ratio= FuzzyWuzzyMining.GetItemDescrRatio(psDescription,item.getTitle().toString());
			 if(previousRatio>60)
			 { 
				 previousRatio = ratio; 
				 pricingRow.put( Integer.toString(rowCount++), item.getSellingStatus().getCurrentPrice().getValue());
			 }
			 
		 } 
		return pricingRow;
	}

}
