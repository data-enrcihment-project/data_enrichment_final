package com.controller.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.ebay.services.finding.SearchItem;
import com.ebay.services.finding.SearchResult;
import com.models.pkg.AmazonCall;

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
		String descr = request.getParameter("psJsonString");
		System.out.println(descr); 
		
		
		ObjectMapper mapper = new ObjectMapper();
		SearchItem obj = null;
		try {
		    // convert user object to json string and return it 
			obj =  (SearchItem) mapper.readValue(descr,SearchItem.class);
			
			//Calling Save method over here
			
			//calling database update method to save these details
		}catch(Exception e)
		{			
			throw e;
		}
	}

}
