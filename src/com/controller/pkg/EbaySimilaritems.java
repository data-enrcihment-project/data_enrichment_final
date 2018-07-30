package com.controller.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.pkg.DbMethods;

/**
 * Servlet implementation class EbaySimilaritems
 */
@WebServlet("/EbaySimilaritems")
public class EbaySimilaritems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EbaySimilaritems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String itemId = request.getParameter("item_Id");
		String typefunction = request.getParameter("typefunction");
		//Usage in future related category Items
		String URL = "";
		String applicationID = DbMethods.GetConfigProperty("ebayApplicationID");
		
		if(typefunction.equals("getSimilarItems"))
		{
			System.out.println("Started similar");
			
			URL = "http://svcs.ebay.com/MerchandisingService?OPERATION-NAME=getSimilarItems&SERVICE-NAME=MerchandisingService&SERVICE-VERSION=1.1.0&CONSUMER-ID="+applicationID+"&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&itemId="+itemId;
			
		}//  
		else if(typefunction.equals("getRelatedCategoryItems")) {
			System.out.println("Started Category");
		
			URL ="http://svcs.ebay.com/MerchandisingService?" + 
					"OPERATION-NAME=getRelatedCategoryItems&" + 
					"SERVICE-NAME=MerchandisingService&" + 
					"SERVICE-VERSION=1.1.0&" + 
					"CONSUMER-ID=" + applicationID +
					"RESPONSE-DATA-FORMAT=JSON&" + 
					"REST-PAYLOAD&" + 
					"maxResults=3&" + 
					"itemId="+itemId;
		}

		String json = "";
		try {
			json = DbMethods.sendGetCallForEnrichment(URL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
