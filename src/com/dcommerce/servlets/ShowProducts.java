package com.dcommerce.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcommerce.database.*;


@WebServlet(urlPatterns = "/ShowProducts")
public class ShowProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowProducts() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Products> prolist = new ArrayList<Products>();
		String lineNo = request.getParameter("line_no");
		DatabaseQuery db = new DatabaseQuery();
		prolist = db.showProductsOfSpecificCategory(lineNo);
		
		request.setAttribute("list", prolist);
		RequestDispatcher rd = request.getRequestDispatcher("/category_products.jsp");
		rd.forward(request, response);
	}
}
