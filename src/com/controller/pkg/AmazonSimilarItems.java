package com.controller.pkg;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.models.pkg.AmazonCall;
/**
 * Servlet implementation class AmazonSimilarItems
 */
@WebServlet("/AmazonSimilarItems")
public class AmazonSimilarItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmazonSimilarItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String asinId = request.getParameter("item_Id");
		String typeId = request.getParameter("type_Id");
		String typefunction = request.getParameter("typefunction");
		
		ObjectMapper mapper = 
				new ObjectMapper();
		Map<Integer, Object> dictionary = new HashMap<Integer, Object>();
		
		String json = "";
		try {
			if(typefunction.equals("getSimilarItems"))
			{
				dictionary = AmazonCall.CallItemSimilarLookUp(asinId, "", "AMAZON-DE");
			}
			else if(typefunction.equals("getRelatedCategoryItems"))
			{
				System.out.println("Calling RelatedItems");
				dictionary = AmazonCall.CallItemLookUp(asinId,"RelatedItems");
			}
			
			json = mapper.writeValueAsString(dictionary);
			response.getWriter().write(json);
			
		}catch(Exception e)
		{			
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
