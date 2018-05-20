package com.models.pkg;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class DbMethods {
	
	CallableStatement statement = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Connection DBConnection()
	{	
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/enrich_data";
			String username = "root";
			String password = "";
			
			Connection conn = DriverManager.getConnection(url,username,password);
			
			///calling stored procedure
			
		    statement = conn.prepareCall("{call sp_test(?)}");
		    String msg = "";
			statement.setString(1, "0410");
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
			
				
				msg = rs.getInt("id")+" , "+rs.getString("company")+" , "+rs.getString("code")+" , "+rs.getString("description");
				System.out.println(msg);
			}
//			
//			float getRatio = FuzzySearch.ratio("mynameisamir", "amir is amazing name and brilliant");
			
			////End stored procedure call
			
			
//			System.out.println("connected");
//			System.out.println(getRatio);
			return conn;
		}
		catch(Exception e)
		{
			
			System.out.println(e);
		}
		return null;
	}
	
	public ResultSet QueryStatement(String query)
	{
		ResultSet rs;
		try {
			Statement st = DBConnection().createStatement();
			rs = st.executeQuery(query);
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

}
