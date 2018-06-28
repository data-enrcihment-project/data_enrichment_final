package com.controller.pkg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.ebay.services.finding.SearchItem;
import com.models.pkg.AmazonCall;
import com.models.pkg.DbMethods;
import com.models.pkg.EbayCallService;

import am.ik.aws.apa.jaxws.Item;

/**
 * Servlet implementation class AmazonItemDescription
 */
@WebServlet("/AmazonItemDescription")
public class AmazonItemDescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmazonItemDescription() {
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

		/////////Amazon Calling Start
		
		
		Map<Integer, Object> dictionary = AmazonCall.CallAmazonApi(descr,"AMAZON-DE");
		
		boolean isEmpty = dictionary.isEmpty();
		
		if(isEmpty)
		{			
			dictionary= AmazonCall.CallAmazonApi(descr,"AMAZON-US");
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
		
		
		/////////Calling End
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String descr = request.getParameter("psJsonString");
		String dataId = request.getParameter("dataID");
		String dataDescr = request.getParameter("dataDescr");
		String dataCode = request.getParameter("dataCode");
		
		System.out.println(descr); 
		
		String sql = "INSERT INTO enriched_data (course_code, course_desc, course_chair)" +
		        "VALUES (?, ?, ?)";
		
		
		ObjectMapper mapper = new ObjectMapper();
		Item obj = null;
		try {
		    // convert user object to json string and return it 
			obj =  (Item) mapper.readValue(descr,Item.class);
			
			//Calling Save method over here

			ArrayList<String> paramsArray = new ArrayList<String>();
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			paramsArray.add("");
			
			//DbMethods.SaveUpdateQueryStatement("",paramsArray);
			//calling database update method to save these details
		}catch(Exception e)
		{			
			throw e;
		}
		//doGet(request, response);
	}

}
