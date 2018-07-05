package com.controller.pkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.jinstagram.utils.MapUtils;
import org.omg.CORBA.portable.InputStream;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.dcommerce.app.App;
import com.ebay.services.finding.SearchItem;
import com.ebay.soap.eBLBaseComponents.EBayAPIInterfaceService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.models.pkg.AmazonCall;
import com.models.pkg.DbMethods;
import com.models.pkg.EbayCallService;
import com.models.pkg.EbayFeedback;
import com.mysql.cj.result.Row;
import com.oracle.jrockit.jfr.RequestableEvent;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;

import sun.net.www.URLConnection;

/**
 * Servlet implementation class dataEnrichment_db
 */
@WebServlet("/dataEnrichment_db")
public class dataEnrichment_db extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dataEnrichment_db() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
		//this.getServletContext().getRequestDispatcher("ItemDescription.jsp").include(request, response);
		//request.getRequestDispatcher("ItemDescription.jsp").forward(request, response);
    }
    
   
    
	@SuppressWarnings("unused")
	private String sendGet(String URL) throws Exception {

		String url = URL;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			response.append(inputLine);
		}
		in.close();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			System.out.println(response.toString());
		    // convert user object to json string and return it 
		     json =  response.toString();
		}catch(Exception e)
		{
			
			throw e;
		}
		
		return json;
		//print result
		//

	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//DbMethods method = new DbMethods();
//		Map<String, Double> dictionary = EbayCallService.GetEbayData();
//		
//		Gson gson = new Gson();
//		String json = gson.toJson(dictionary);
		
		//response.getWriter().write(json);
		
		//App.CallPricing();
		//String json = EbayFeedback.GetEbayFeedback();
		
		/*String json = AmazonCall.CallItemLookUp("RelatedItems,Small","B018XHNJCM");
		System.out.println(json);
		//Discover Similar Items
		String json1 = AmazonCall.CallItemLookUp("Similarities","B018XHNJCM");
		System.out.println(json1);
		String json3 = AmazonCall.CallItemLookUp("RelatedItems,Small","B008S7BV4G");
		System.out.println(json3);
				//Discover Similar Items
		String json4 = AmazonCall.CallItemLookUp("Similarities","B008S7BV4G");
		System.out.println(json4);
		
		String json5 = AmazonCall.CallItemLookUp("RelatedItems,Small","B01B8052EY");
		System.out.println(json5);
		//Discover Similar Items
		String json6 = AmazonCall.CallItemLookUp("Similarities,Small","B01B8052EY");
		System.out.println(json6);*/
		
		/*String json = AmazonCall.CallItemSimilarLookUp("RelatedItems,Small","B018XHNJCM,B008S7BV4G,B01B8052EY");
		System.out.println(json);
		
		String json1 = AmazonCall.CallItemSimilarLookUp("RelatedItems,Small","B008S7BV4G");
		System.out.println(json1);
		
		String json2 = AmazonCall.CallItemSimilarLookUp("RelatedItems,Small","B01B8052EY");
		System.out.println(json2);*/
		
		//Grid Implementation
		// sanbox AmirMans-DataEnri-SBX-f2ccbdebc-dcb0ffe1
	/*	String endpoint = "https://api.sandbox.ebay.com/wsapi";
		// Define the query string parameters.
		String queryString = "?callname=GetItem"
				+ "&siteid=0"
		                                        + "&appid=AmirMans-DataEnri-SBX-f2ccbdebc-dcb0ffe1"
		                                        + "&version=349"//563
		                                        +  "&itemid=263271955718"
		                                        + "&Routing=new";
		String requestURL  = endpoint + queryString;*/
	/*	String URL = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByKeywords"+
			   "&SERVICE-VERSION=1.0.0" +
			   "&SECURITY-APPNAME=AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231" +
			   "&RESPONSE-DATA-FORMAT=XML" +
			   "&REST-PAYLOAD" +
			   "&keywords=harry potter phoenix";*/
		
		//Usage in future
		/*String URl = "http://svcs.ebay.com/MerchandisingService?\r\n" + 
				"   OPERATION-NAME=getRelatedCategoryItems&\r\n" + 
				"   SERVICE-NAME=MerchandisingService&\r\n" + 
				"   SERVICE-VERSION=1.1.0&\r\n" + 
				"   CONSUMER-ID=YourAppID&\r\n" + 
				"   RESPONSE-DATA-FORMAT=XML&\r\n" + 
				"   REST-PAYLOAD&\r\n" + 
				"   maxResults=3&\r\n" + 
				"   itemId=170192529715";*/
		
		/*String URL = "http://svcs.sandbox.ebay.com/services/marketplacecatalog/ProductService/v1?OPERATION-NAME=GetProductDetails" + 
				"&SERVICE-VERSION=1.3.0" + 
				"&SECURITY-APPNAME=AmirMans-DataEnri-SBX-f2ccbdebc-dcb0ffe1" + 
				"&GLOBAL-ID=EBAY-MOTOR" + 
				"&RESPONSE-DATA-FORMAT=XML" + 
				//"   &productSearch.invocationId=1234567890" + 
				//"   &productSearch.categoryId=170583" + 
				"&productSearch.keywords=182594333027";*///NOt working 
		//For Shopping ,working with description
		//String URL = "http://open.api.ebay.com/shopping?callname=GetSingleItem&appid=AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231&version=517&responseencoding=JSON&itemid=182594333027&IncludeSelector=Details,Description,Variations,Compatibility";
		
		
		//String URL = "http://open.api.ebay.com/shopping?version=655&appid=AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231&callname=FindProducts&itemid=182594333027";
		
		/*String URL = "http://open.api.ebay.com/shopping?callname=FindProducts" + 
				"&responseencoding=XML" + 
				"&appid=AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231" + 
				"&siteid=3" + 
				"&version=525" + 
				"&ProductID.type=itemid" + 
				"&ProductID.Value=182594333027" + 
				"&MaxEntries=5";*/
		
		/*String URL ="http://svcs.sandbox.ebay.com/services/marketplacecatalog/ProductService/v1?OPERATION-NAME=findProducts" + 
				"&SERVICE-VERSION=1.3.0" + 
				"&SECURITY-APPNAME=AmirMans-DataEnri-SBX-f2ccbdebc-dcb0ffe1" + 
				"&GLOBAL-ID=EBAY-MOTOR" + 
				"&RESPONSE-DATA-FORMAT=XML" + 
				"&productSearch.invocationId=1234567890"+
				"&productSearch.keywords=182594333027&IncludeSelector=Details"; */
				
		//keywords
		//String URL = "http://svcs.ebay.com/MerchandisingService?OPERATION-NAME=getSimilarItems&SERVICE-NAME=MerchandisingService&SERVICE-VERSION=1.1.0&CONSUMER-ID=AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231&RESPONSE-DATA-FORMAT=XML&REST-PAYLOAD&itemId=182594333027";
		//String URL = "http://open.api.ebay.com/trading?callname=GetUserProfile&appid=AmirMans-DataEnri-PRD-32cc7bc3a-9d32c231&version=563&itemid=182594333027&IncludeSelector=Details,Feedback";
		
			
		/*String json = "";
		try {
			json = sendGet(URL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(json);*/
		
        //EbayFeedback.GetEbayFeedback();
		//EbayCallService.GetEbayItemObjectFast();
		
		// Define the endpoint (e.g., the Sandbox Gateway URI)
		
		
		ResultSet rs = DbMethods.QueryStatement("Select id,item_no,company,description,language_code,shop_code from shop_item");
		List<Map<String, Object>> rows = null;
		try {
			rows = DbMethods.resultSetToList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(rows);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
