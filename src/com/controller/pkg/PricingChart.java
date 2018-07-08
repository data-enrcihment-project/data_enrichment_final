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
 * Servlet implementation class PricingChart
 */
@WebServlet("/PricingChart")
public class PricingChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PricingChart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		System.out.println("Strted");
		
		String selectTypeNo = request.getParameter("selectTypeNo");
		
		String sqlQuery = "SELECT item_no,description from pricing_module GROUP BY item_no";
		if(!(selectTypeNo.equals("")))
		{
			System.out.println(selectTypeNo);
			sqlQuery = "Select item_no,description,base_price,discount_price from pricing_module where item_no='"+selectTypeNo+"'";
		}
		
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
