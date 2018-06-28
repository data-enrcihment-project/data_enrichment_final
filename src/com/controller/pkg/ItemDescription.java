package com.controller.pkg;

import java.io.IOException;
import java.sql.PreparedStatement;
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

import com.ebay.services.finding.SearchItem;
import com.ebay.services.finding.SearchResult;
import com.models.pkg.AmazonCall;
import com.models.pkg.DbMethods;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		System.out.println(jsonData); 
		
		String sql = "INSERT INTO enriched_data (E_Id,Enrich_ID, Item_no,Item_title,Item_Image,Item_Description,Item_Price,Item_URL,Item_Reviews,Type_ID,JSON_Text)" +
		        "VALUES (?,?, ?, ?,?, ?, ?,?, ?, ?, ?)";
		
		
		ObjectMapper mapper = new ObjectMapper();
		SearchItem obj = null;
		//try {
		    // convert user object to json string and return it 
			obj =  (SearchItem) mapper.readValue(jsonData,SearchItem.class);
			
			//Calling Save method over here
			//ArrayList<String> paramsArray = new ArrayList<String>();
			
			//Map<String, String> paramsArray  = new HashMap<String, String>();
			//paramsArray.put("Int1", obj.getItemId());
			//paramsArray.put("Int2", dataId);
			///paramsArray.put("String1", obj.getTitle());
			//paramsArray.put("String2", obj.getGalleryURL());
			//paramsArray.put("String3", "");
			//paramsArray.put("Double1", Double.toString(obj.getSellingStatus().getCurrentPrice().getValue()));
			//paramsArray.put("String4", obj.getViewItemURL());
			//paramsArray.put("String5", "");
			//paramsArray.put("Int3", "1");
			//paramsArray.put("String6","");//jsonData
			
			
		
			try {
				PreparedStatement preparedStatement = DbMethods.DBConnection().prepareStatement(sql);
				
				preparedStatement.setInt(1, Integer.parseInt((obj.getItemId())));
				preparedStatement.setString(2, (obj.getItemId()));
				preparedStatement.setInt(3, Integer.parseInt(dataId));
				preparedStatement.setString(4, obj.getTitle());
				preparedStatement.setString(5, obj.getGalleryURL());
				preparedStatement.setString(6, "");
				preparedStatement.setDouble(7, Double.parseDouble(obj.getItemId()));
				preparedStatement.setString(8, obj.getViewItemURL());
				preparedStatement.setString(9, "");
				preparedStatement.setInt(10, 1);
				preparedStatement.setString(11, "");
				 
				preparedStatement.executeUpdate(); 
				System.out.println("Done");
				
			}catch(Exception e)
			{
				
				try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			 
			
			System.out.println(DbMethods.DataEnrichment.Ebay.toString());
			//DbMethods.SaveUpdateQueryStatement(sql,paramsArray);
			//calling database update method to save these details
		//}catch(Exception e)
		//{			
		//	throw e;
		}
		
		
		
		
		
		
		
	}


