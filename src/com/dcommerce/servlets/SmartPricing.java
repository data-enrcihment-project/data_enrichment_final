package com.dcommerce.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcommerce.database.*;

@WebServlet("/SmartPricing")
public class SmartPricing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SmartPricing() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double cheapest = 0.0;
		double highest = 0.0;
		double average = 0.0;
		
		ArrayList<SimilarProducts> similarProList = new ArrayList<SimilarProducts>();
		String itemNo = request.getParameter("item_no");
		DatabaseQuery db = new DatabaseQuery();
		similarProList = db.showSimilarProducts(itemNo);
		
		List<Double> base = new ArrayList<Double>(); 
		
		for(SimilarProducts simProduct : similarProList) {
 			String similarBase = simProduct.getBase_price();
 			base.add(Double.parseDouble(similarBase));	
		}
		
	
		
		if (!base.isEmpty()) {
			Collections.sort(base);
			cheapest = base.get(0);
			highest = base.get(base.size() - 1);

			double sum = 0;
			for (int j = 0; j < base.size(); j++) {
				sum = sum + base.get(j);
			}
			average = 1.0d * sum / base.size();
		}
		
		request.setAttribute("list", similarProList);
		request.setAttribute("cheapest", cheapest);
		request.setAttribute("highest", highest);
		request.setAttribute("average", average);
		RequestDispatcher rd = request.getRequestDispatcher("/smart_pricing.jsp");
		rd.forward(request, response);
	}

}
