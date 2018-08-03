package com.models.pkg;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.image.RasterOp;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public static List<SearchItem> GetEbayItemObject(String psDescription,String globalID) throws IOException
	{
		 ClientConfig config = new ClientConfig();         
		 config.setApplicationId(DbMethods.GetConfigProperty("ebayApplicationID"));
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
		 config.setApplicationId(DbMethods.GetConfigProperty("ebayApplicationID"));
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
	
	private static String GetItemDescription(String itemId)
	{
		
		String URL = "http://open.api.ebay.com/shopping?callname=GetSingleItem&appid=AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231&version=517&responseencoding=JSON&itemid="+ itemId +"&IncludeSelector=Details,Description,Variations,Compatibility";
		String json = "";
		try {
			json = DbMethods.sendGetCallForEnrichment(URL);
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public static List<SearchItem> GetEbayItemObjectFast() throws IOException
	{
		 ClientConfig config = new ClientConfig();         
		 config.setApplicationId(DbMethods.GetConfigProperty("ebayApplicationID"));
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
		 config.setApplicationId(DbMethods.GetConfigProperty("ebayApplicationID"));
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
	    int filteredListCount=0;	    
	    
	    //for snding to client
	    Map<Integer, Object> row  = new HashMap<Integer, Object>();
	    
	    Map<Integer, Object> details = new HashMap<Integer, Object>();
    	
	        for (SearchItem item : searchItems) {
	        
				 String descr = GetItemDescription(item.getItemId().toString());
				
				 item.setSubtitle(descr);
	    
	        	Double  ratio= FuzzyWuzzyMining.GetItemDescrRatio(psDescr,item.getTitle().toString());        	
	        	
	        	if(ratio >= 69)
	            {
	        		filteredListCount++;
	            	details.put(filteredListCount,item);
	            	row.put(filteredListCount, item);
	            }
	        	System.out.println(item.getSubtitle());
	        }
	        	        
	        System.out.println(filteredListCount);
	        try {
	        	
	        	row.put(0,filteredListCount);
	        	WriteSearchItemToXML(details, psDescr +filteredListCount+".xml");
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
	    }
	
	
	@SuppressWarnings("unused")
	public static Map<String, Double> GetEbayItemPrice(String psDescription,String globalID) throws IOException {
		
		Double previousRatio=0.0;
		List<SearchItem> itemList = GetEbayItemObject(psDescription,globalID);
		
		 Map<String, Double> pricingRow  = new HashMap<String, Double>();
		
		
		Double selectedItem = null;
		Integer rowCount = 0;
		for(SearchItem item : itemList) {           
			
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
