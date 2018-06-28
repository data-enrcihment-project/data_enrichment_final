package com.models.pkg;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class DbMethods {
	
	CallableStatement statement = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public enum DataEnrichment{
		
		Ebay("1"),
		Amazon("2");
		
		private String name;

		DataEnrichment(String name)
		{
	        this.name = name;
	    }
	}
	
	public static Connection DBConnection()
	{	
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/enrichment";
			String username = "root";
			String password = "";
			
			Connection conn = DriverManager.getConnection(url,username,password);
			
			///calling stored procedure
			
//		    statement = conn.prepareCall("{call sp_test(?)}");
//		    String msg = "";
//			statement.setString(1, "0410");
//			ResultSet rs = statement.executeQuery();
//			
//			while(rs.next())
//			{
//			
//				
//				msg = rs.getInt("id")+" , "+rs.getString("company")+" , "+rs.getString("code")+" , "+rs.getString("description");
//				System.out.println(msg);
//			}
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
	
	public static ResultSet QueryStatement(String query)
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
	
	public static String SaveUpdateQueryStatement(String sqlStatement,Map<String, String> param)
	{
		try {
			PreparedStatement preparedStatement = DBConnection().prepareStatement(sqlStatement);
			
			int count = 0;
			
			for (Map.Entry<String,String> entry : param.entrySet()) 
			{
				count++;
					System.out.println("Key = " + entry.getKey() +
				                 ", Value = " + entry.getValue());
					
					switch(entry.getKey())
					{
					case "Int":
						preparedStatement.setInt(count, Integer.parseInt(entry.getValue()));
						break;
						
					case "String":
						preparedStatement.setString(count, entry.getValue());
						break;
						
					case "Double":
						preparedStatement.setDouble(count, Double.parseDouble(entry.getValue()));
						break;					
					}
			}
			
			preparedStatement.executeUpdate(); 
			System.out.println("Done");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	///Return rows from ResultSet
	public static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
	    while (rs.next()){
	        Map<String, Object> row = new HashMap<String, Object>(columns);
	        for(int i = 1; i <= columns; ++i){
	            row.put(md.getColumnName(i), rs.getObject(i));
	        }
	        rows.add(row);
	    }
	    return rows;
	}

}
