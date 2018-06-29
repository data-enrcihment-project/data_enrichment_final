package com.controller.pkg;

import java.io.IOException;
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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.ebay.services.finding.SearchItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.models.pkg.DbMethods;
import com.models.pkg.EbayCallService;
import com.mysql.cj.result.Row;
import com.oracle.jrockit.jfr.RequestableEvent;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;

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
		
		ObjectMapper mapper = new ObjectMapper();
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
		//this.getServletContext().getRequestDispatcher("ItemDescription.jsp").include(request, response);
		//request.getRequestDispatcher("ItemDescription.jsp").forward(request, response);
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
		
		ResultSet rs = DbMethods.QueryStatement("Select id,company,description,language_code,shop_code from shop_item");
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
