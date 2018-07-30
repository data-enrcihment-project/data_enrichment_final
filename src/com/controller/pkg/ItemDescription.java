package com.controller.pkg;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.ebay.services.finding.SearchItem;
import com.ebay.services.finding.SearchResult;
import com.google.gson.Gson;
import com.models.pkg.AmazonCall;
import com.models.pkg.DbMethods;
import com.models.pkg.EbayCallService;

import am.ik.aws.apa.jaxws.Item;

/**
 * Servlet implementation class ItemDescription
 */
@WebServlet("/ItemDescription")
public class ItemDescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDescription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String descr = request.getParameter("psDescr");
		System.out.println(request.getParameter("psDescr")); 
		
		
		Map<Integer, Object> dictionary = EbayCallService.GetEbayDataWithDescr(descr,"EBAY-DE");
		
	
		boolean isEmpty = false;
		
		
		for (Map.Entry<Integer, Object> entry : dictionary.entrySet()) {
			System.out.println(isEmpty+ " 222Final");
			String val = entry.getValue().toString();
			if(Integer.parseInt(val) > 0)
			{
				System.out.println(isEmpty+ " 2332Final");
				
				break;
			}else {
				isEmpty = true ;//false;
			}
		}
		
		if(isEmpty)
		{
			System.out.println("2weFinal");
			dictionary= EbayCallService.GetEbayDataWithDescr(descr,"EBAY-US");
		}
		ObjectMapper mapper = 
				new ObjectMapper();
		String json = "";
		try {
		    // convert user object to json string and return it 
		     json =  mapper.writeValueAsString(dictionary);
		}catch(Exception e)
		{			
			throw e;
		}
        request.setAttribute("dictionary", json);
		
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String jsonData = request.getParameter("psJsonString");
		String dataId = request.getParameter("dataID");
		String dataDescr = request.getParameter("dataDescr");
		String dataCode = request.getParameter("dataCode");
		String categoryName = request.getParameter("categoryName");
		String images_Json = request.getParameter("images_URL");
		
	////Inserting data
		String sqlSave = "INSERT INTO enrichment_module (Enrich_ID, Item_no,Item_title,Item_Image,Item_Description,Item_Price,Item_URL,Item_Reviews,Type_ID,JSON_Text,CategoryName,Images_URL) " +
		        "VALUES (?, ?, ?,?, ?, ?,?, ?, ?, ?, ?, ?)";
		
		System.out.println(dataId);
		
		ObjectMapper mapper = new ObjectMapper();
		SearchItem obj = null;
		//try {
		    // convert user object to json string and return it 
		//Get Ebay data Object
			obj =  (SearchItem) mapper.readValue(jsonData,SearchItem.class);
			
			String sqlCount = "Select E_ID from enrichment_module where Item_no='"+ obj.getItemId().toString()+"'";//Enrich_ID='"+ dataId.toString() +"' AND
			System.out.println(sqlCount); 
			
			//Get Previous same Asin No count if available
			ResultSet rsetCount = DbMethods.QueryStatement(sqlCount);
					
			//Update record if necessary
			String sqlUpdateQuery =  ("UPDATE enrichment_module SET Item_title = ?, Item_Image = ?, Item_Description = ?, Item_Price = ?, Item_URL = ?, Item_Reviews = ?, JSON_Text = ?,CategoryName = ?,Images_URL = ? WHERE Enrich_ID ='"+dataId +"' AND Item_no='"+obj.getItemId()+"'");
			
			//Calling Save method over here
			//ArrayList<String> paramsArray = new ArrayList<String>();
			//Insert parameter Array
			Map<String, String> paramsArray  = new HashMap<String, String>();
			
			paramsArray.put("1", dataId);
			paramsArray.put("2", obj.getItemId());
		    paramsArray.put("3", obj.getTitle());
			paramsArray.put("4", obj.getGalleryURL());
			//Description from httpget
			paramsArray.put("5", dataDescr);
			paramsArray.put("6", Double.toString(obj.getSellingStatus().getCurrentPrice().getValue()));
			paramsArray.put("7", obj.getViewItemURL().toString());
			
			String sellerRatingReviews = mapper.writeValueAsString(obj.getSellerInfo());
			
			paramsArray.put("8", sellerRatingReviews);
			paramsArray.put("9", "1");
			paramsArray.put("10",jsonData);
			//////
			paramsArray.put("11", categoryName);//category name
			paramsArray.put("12",images_Json);//images json
			
			
			//Update Array
			Map<String, String> paramsArrayUpdate  = new HashMap<String, String>();
		
			paramsArrayUpdate.put("1", obj.getTitle());
			paramsArrayUpdate.put("2", obj.getGalleryURL());
			
			paramsArrayUpdate.put("3", dataDescr);
			paramsArrayUpdate.put("4", Double.toString(obj.getSellingStatus().getCurrentPrice().getValue()));
			paramsArrayUpdate.put("5", obj.getViewItemURL());
			
			String sellerRatingReviewsUpdate = mapper.writeValueAsString(obj.getSellerInfo());
			
			paramsArrayUpdate.put("6", sellerRatingReviewsUpdate);
			
			paramsArrayUpdate.put("7",jsonData);
			paramsArrayUpdate.put("8", categoryName);//category name
			paramsArrayUpdate.put("9",images_Json);//images json
			
			System.out.println(DbMethods.GetRecordCount(rsetCount));
			
			try {
				
				if(DbMethods.GetRecordCount(rsetCount)==0)
				{
					//Insert value into Perform module Table
					Map<String, String> paramsArrayPerformedModule  = new HashMap<String, String>();
					
					System.out.println("Insert Query");
					String sqlSavePerformed = "INSERT INTO performed_jobs_module (Item_no,Enrich_ID,Type_ID,Time_Stamp)" +
					        "  VALUES (?,?,?,?)";		
					
					paramsArrayPerformedModule.put("1", obj.getItemId());
					paramsArrayPerformedModule.put("2", dataId.toString());
					paramsArrayPerformedModule.put("3", "1");
					paramsArrayPerformedModule.put("4", DbMethods.GetdateTime());
					
					DbMethods.SaveUpdateQueryStatement(sqlSave,paramsArray);
					
					DbMethods.SaveUpdateQueryStatement(sqlSavePerformed,paramsArrayPerformedModule);
				}
				else {
					//update
					System.out.println("Update Query");
					//Update value into Perform module Table
					String sqlSavePerformedUpdate = "UPDATE performed_jobs_module SET Time_Stamp=? where Item_no='"+obj.getItemId().toString()+ "'  AND Enrich_ID='"+dataId.toString()+"'";
					
					DbMethods.SaveUpdateQueryStatement(sqlUpdateQuery,paramsArrayUpdate);
					
					Map<String, String> paramsArrayPerformedModuleUpdate  = new HashMap<String, String>();
					paramsArrayPerformedModuleUpdate.put("1", DbMethods.GetdateTime());
					DbMethods.SaveUpdateQueryStatement(sqlSavePerformedUpdate,paramsArrayPerformedModuleUpdate);
				}
				System.out.println("Done");
				response.getWriter().write("Item NO. "+obj.getItemId()+ " - having title -"+obj.getTitle());
				
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
		
	}


