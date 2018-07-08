package com.controller.pkg;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		System.out.println("Called");
		//System.out.println(psduration);
		
		///query change a liitle for review grid to fetch with type ID 1 and 2
		
		/*String sqlQuery = "SELECT  enrichment.Item_ID,enrichment.Item_title, enrichment.Item_Price, enrichment.Description, enrichment.Type_ID, enrichment.Item_Reviews,enrichment.Item_URL,performed_job.Time_Stamp   " + 
				"  FROM enrichment_project enrichment   " + 
				" INNER JOIN performed_jobs_project performed_job  " + 
				"  ON enrichment.Type_ID = performed_job.Type_ID AND enrichment.Enrich_ID = performed_job.Enrich_ID";
		*/
		String sqlQuery = "SELECT  enrichment.Item_no,enrichment.Item_title, enrichment.Item_Price, enrichment.Item_Description, enrichment.Type_ID, enrichment.Item_Reviews,enrichment.Item_URL,performed_job.Time_Stamp   " + 
				"  FROM enrichment_module enrichment   " + 
				" INNER JOIN performed_jobs_module performed_job  " + 
				"  ON enrichment.Type_ID = performed_job.Type_ID "+
				" Where enrichment.Type_ID ="+typeID;
		
		
		
		/*if(!duration.isEmpty())
		{
			switch (duration) {
			case "All":
				sqlQuery+= " WHERE Time_Stamp <"+duration;
				break;

			default:
				break;
			}
			
		}*/
		
		ResultSet rs = DbMethods.QueryStatement(sqlQuery);
		
		
		
		List<Map<String, Object>> rows = null;
		try {
			rows = DbMethods.resultSetToList(rs);
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

}
