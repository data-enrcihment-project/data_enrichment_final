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
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		String sql = "INSERT INTO enrichment_project (Enrich_ID, Item_ID,Item_title,Item_Image,Item_Description,Item_Price,Item_URL,Item_Reviews,Type_ID,JSON_Text)" +
		        "VALUES (?, ?, ?,?, ?, ?,?, ?, ?, ?)";
		
		
		ObjectMapper mapper = new ObjectMapper();
		SearchItem obj = null;
		//try {
		    // convert user object to json string and return it 
			obj =  (SearchItem) mapper.readValue(jsonData,SearchItem.class);
			
			//Calling Save method over here
			//ArrayList<String> paramsArray = new ArrayList<String>();
			
			Map<String, String> paramsArray  = new HashMap<String, String>();
			
			paramsArray.put("1", dataId);
			paramsArray.put("2", obj.getItemId());
		    paramsArray.put("3", obj.getTitle());
			paramsArray.put("4", obj.getGalleryURL());
			paramsArray.put("5", "");
			paramsArray.put("6", Double.toString(obj.getSellingStatus().getCurrentPrice().getValue()));
			paramsArray.put("7", obj.getViewItemURL());
			paramsArray.put("8", "");
			paramsArray.put("9", "1");
			paramsArray.put("10",jsonData);
			
			try {
				
				DbMethods.SaveUpdateQueryStatement(sql,paramsArray);
				
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
			 
		}
		
	}


