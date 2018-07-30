package com.controller.pkg;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.models.pkg.DbMethods;

/**
 * Servlet implementation class Performed_Jobs
 */
@WebServlet("/Performed_Jobs")
public class Performed_Jobs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Performed_Jobs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Get Performed Enriched Jobs
		//String psduration = request.getParameter("duration");
		String typeID = request.getParameter("type_Id");
		
		String sqlQuery = "SELECT enrichment.E_ID,enrichment.Enrich_ID,enrichment.Item_no,enrichment.Item_title, enrichment.Item_Price, enrichment.Item_Description, enrichment.Type_ID, enrichment.Item_Reviews,enrichment.Item_URL,performed_job.Time_Stamp FROM enrichment_module enrichment INNER JOIN performed_jobs_module performed_job ON enrichment.Type_ID = performed_job.Type_ID ";
			
		System.out.println(typeID);
		if(typeID==null || typeID == "null")
		{
			//sqlQuery +=" Where enrichment.Type_ID = 1 OR enrichment.Type_ID =2";
			
		}else {
			sqlQuery +=" Where enrichment.Type_ID ="+typeID;
		}
		sqlQuery +=" GROUP BY enrichment.Item_no";
	
		System.out.println(sqlQuery);
		
		ResultSet rs = DbMethods.QueryStatement(sqlQuery);
	
		List<Map<String, Object>> rows = null;
		try {
			rows = DbMethods.resultSetToList(rs);
			System.out.println(rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(rows);
		
		System.out.println(json);
		response.getWriter().write(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String itemID = request.getParameter("E_ID");
		
		System.out.println(itemID);
		String sqlQuery = "DELETE from enrichment_module WHERE E_ID = "+itemID;
		
		System.out.println(sqlQuery);
	
		DbMethods.QueryStatementDelete(sqlQuery);
	}
}
