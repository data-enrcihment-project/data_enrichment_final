package com.dcommerce.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import javax.sql.DataSource;
import com.dcommerce.scrapper.Entry;

/**
 * This class takes a connection from the connection pool and performs 
 * database queries
 * 
 * @author Mushfiqul Huda
 */
public class DatabaseQuery {

	/**This method fetches all the products of different categories and writes the data in csv files.
	 * 
	 * @param Statement
	 *            datbase query to fetch the data
	 * @param File
	 *            csv file name where the result will be saved
	 */
	public static void returnResult(String Statement, String File) throws IOException {

		ResultSet rsObj = null;
		Connection connObj = null;
		PreparedStatement pstmtObj = null;
		ConnectionPool jdbcObj = new ConnectionPool();

		try {
			DataSource dataSource = jdbcObj.setUpPool();
			jdbcObj.printDbStatus(); // TODO: delete

			connObj = dataSource.getConnection();
			jdbcObj.printDbStatus(); // TODO: delete

			// performing database query
			pstmtObj = connObj.prepareStatement(Statement);
			rsObj = pstmtObj.executeQuery();

			// writing the results in corresponding csv file
			BufferedWriter bw = new BufferedWriter(new FileWriter(File));

			while (rsObj.next()) {
				StringBuffer Nb = new StringBuffer();
				Nb.append(rsObj.getString("description") + "," + rsObj.getString("item_no") + "\n");
				bw.write(Nb.toString());
			}
			System.out.println(new File(File).getAbsolutePath());
			// closing BufferWriter object
			bw.close();

		} catch (Exception sqlException) {
			sqlException.printStackTrace();
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
			}
		}
	}
	
	/**This method inserts enriched data in the database.
	 * 
	 * @param entry
	 *            Arraylist of similar items
	 */
	public static void insertData(ArrayList<Entry> entry, String item_no) throws IOException {

		Connection connObj = null;
		PreparedStatement pstmtObj = null;
		ConnectionPool jdbcObj = new ConnectionPool();

		try {
			DataSource dataSource = jdbcObj.setUpPool();

			connObj = dataSource.getConnection();

			// sorting the product list according to their similarity score
			Collections.sort(entry, Entry.ScoreDiff);
			
			//taking the first 5 similar items.
			int max = 5;
			if(entry.size()<5) {
				max = entry.size();
			}
			for (int i = 0; i < max ; i++) {
				Entry str = entry.get(i);
				//System.out.println(str.getTitle() + " " + str.getScore() + " " + str.getPrice1() + " " + str.getPrice2());
				String title = str.getTitle();
				double base_price = str.getPrice1();
				double discount_price = str.getPrice2();
				//add the column in the database and change the statement
				String Statement = "INSERT INTO pricing_module (item_no, description, base_price, discount_price) VALUES ('"+item_no+"','"+title+"', '"+base_price+"', '"+discount_price+"')"; 
				pstmtObj = connObj.prepareStatement(Statement);
				pstmtObj.executeUpdate();
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
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
			}
		}
	}
}
