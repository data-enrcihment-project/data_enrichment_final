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
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		//request.getRequestDispatcher("ItemDescription.jsp").forward(request, response);
    }
        
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//DbMethods method = new DbMethods();
		//App.CallPricing();
		
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
