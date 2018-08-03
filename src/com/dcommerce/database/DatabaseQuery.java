package com.dcommerce.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.dcommerce.scrapper.Entry;

/**
 * This class takes a connection from the connection pool and performs database
 * queries
 * 
 * @author Mushfiqul Huda
 */
public class DatabaseQuery {
	
	/**
	 * This method updates enriched data in the database.
	 * 
	 * @param entry 
	 * 			Arraylist of similar items
	 */
	public synchronized void insertData(ArrayList<Entry> entry, String item_no) throws IOException {
		Connection connObj = null;
		PreparedStatement pstmtObj = null;
		ConnectionPool jdbcObj = new ConnectionPool();

		try {
			DataSource dataSource = jdbcObj.setUpPool();
			connObj = dataSource.getConnection();

			int max = 5;
			if (entry.size() < 5) {
				max = entry.size();
			}
			for (int i = 0; i < max; i++) {
				Entry str = entry.get(i);
				
				String title = str.getTitle();
				double base_price = str.getBasePrice();
				double discount_price = str.getDiscountPrice();
				String shop_name = str.getShopName();
				
				String Statement = "INSERT INTO pricing_module(item_no, description, base_price, discount_price, shop_name) VALUES ('"
						+ item_no + "','" + title + "', '" + base_price + "', '" + discount_price + "', '" + shop_name + "')";
				
				pstmtObj = connObj.prepareStatement(Statement);
				pstmtObj.executeUpdate();
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			System.out.println("Problem : Inserting Similar Products : Table -> pricing_module ");
		} finally {
			try {
				// closing PreparedStatement object
				if (pstmtObj != null) {
					pstmtObj.close();
				}
				// closing Connection object
				if (connObj != null) {
					connObj.close();
				}
			} catch (Exception sqlException) {
				sqlException.printStackTrace();
				System.out.println("Problem : Closing Database Resources ");
			}
		}
	}

	
	/**
	 * This method fetches all the products of a specific category from the
	 * database. Populates the result into an ArrayList and returns it because
	 * returning a ResultSet object is a very bad practice.
	 * 
	 * @param lineNo 
	 * 			Category line no.
	 */

	public ArrayList<Products> showProductsOfSpecificCategory(String lineNo) {

		ResultSet rsObj = null;
		Connection connObj = null;
		PreparedStatement pstmtObj = null;
		ConnectionPool jdbcObj = new ConnectionPool();

		ArrayList<Products> prolist = new ArrayList<Products>();

		try {
			DataSource dataSource = jdbcObj.setUpPool();
			connObj = dataSource.getConnection();
			String Statement = "SELECT shop_item.description, shop_item.item_no FROM shop_item INNER JOIN shop_item_has_category "
					+ "ON shop_item.item_no = shop_item_has_category.item_no "
					+ "WHERE shop_item_has_category.category_line_no = " + lineNo;
			pstmtObj = connObj.prepareStatement(Statement);
			rsObj = pstmtObj.executeQuery();

			while (rsObj.next()) {

				String desc = rsObj.getString("description");
				String item_no = rsObj.getString("item_no");
				Products products = new Products(desc, item_no);
				prolist.add(products);

			}

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			System.out.println(
					"Problem : Fetching Products From Of Some Category : Table -> shop_item & shop_item_has_category ");
		} finally {
			try {
				// closing ResultSet object
				if (rsObj != null) {
					rsObj.close();
				}
				// closing PreparedStatement object
				if (pstmtObj != null) {
					pstmtObj.close();
				}
				// closing Connection object
				if (connObj != null) {
					connObj.close();
				}
			} catch (Exception sqlException) {
				sqlException.printStackTrace();
				System.out.println("Problem : Closing Database Resources ");
			}
		}
		return prolist;
	}

	/**
	 * This method fetches all the enriched or similar products. Populates the
	 * result into an ArrayList and returns it because returning a ResultSet object
	 * is a very bad practice.
	 * 
	 * @param itemNo
	 * 
	 */

	public ArrayList<SimilarProducts> showSimilarProducts(String itemNo) throws IOException {

		ResultSet rsObj = null;
		Connection connObj = null;
		PreparedStatement pstmtObj = null;
		ConnectionPool jdbcObj = new ConnectionPool();

		ArrayList<SimilarProducts> similarProList = new ArrayList<SimilarProducts>();

		try {
			DataSource dataSource = jdbcObj.setUpPool();
			connObj = dataSource.getConnection();
			String Statement = "SELECT description, base_price, discount_price, shop_name "
					+ "FROM pricing_module WHERE item_no = '" + itemNo + "'";

			pstmtObj = connObj.prepareStatement(Statement);
			rsObj = pstmtObj.executeQuery();

			while (rsObj.next()) {

				String desc = rsObj.getString("description");
				String base_price = rsObj.getString("base_price");
				String discount_price = rsObj.getString("discount_price");
				String shop_name = rsObj.getString("shop_name");

				SimilarProducts similarProducts = new SimilarProducts(desc, base_price, discount_price, shop_name);
				similarProList.add(similarProducts);

			}

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			System.out.println("Problem : Fetching Similar Products : Table -> pricing_module ");
		} finally {
			try {
				// closing ResultSet object
				if (rsObj != null) {
					rsObj.close();
				}
				// closing PreparedStatement object
				if (pstmtObj != null) {
					pstmtObj.close();
				}
				// closing Connection object
				if (connObj != null) {
					connObj.close();
				}
			} catch (Exception sqlException) {
				sqlException.printStackTrace();
				System.out.println("Problem : Closing Database Resources ");
			}
		}
		return similarProList;
	}
}
