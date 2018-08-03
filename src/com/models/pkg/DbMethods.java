package com.models.pkg;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class DbMethods {
	
	CallableStatement statement = null;
	
	
	public static void main(String[] args) {

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
			
			Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			
			String url = GetConfigProperty("dbURl"); 
			String username = GetConfigProperty("dbuser");
			String password = GetConfigProperty("dbpassword");
			
			
			System.out.println(url +"----"+username);
			conn = DriverManager.getConnection(url,username,password);
			
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
			
			//PreparedStatement st = DBConnection().prepareStatement(query);
			Statement st = DBConnection().createStatement();
			rs = st.executeQuery(query);
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Integer QueryStatementDelete(String query)
	{
		Integer rs;
		try {
			
			//PreparedStatement st = DBConnection().prepareStatement(query);
			Statement st = DBConnection().createStatement();
			rs = st.executeUpdate(query);
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
			
			
			for (Map.Entry<String,String> entry : param.entrySet()) 
			{
				preparedStatement.setString(Integer.parseInt(entry.getKey()), entry.getValue());
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
	
	public static Integer GetRecordCount(ResultSet rs)
	{
		Integer count= 0;
		try {
				
			rs.last();
				
			count = rs.getRow();
			 
		}catch(Exception e)
		{
			
		}
		return count;
	}
	
	@SuppressWarnings("unused")
	public static String sendGetCallForEnrichment(String URL) throws Exception {// return get url response

		String url = URL;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			response.append(inputLine);
		}
		in.close();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			System.out.println(response.toString());
		    // convert user object to json string and return it 
		     json =  response.toString();
		}catch(Exception e)
		{
			
			throw e;
		}
		
		return json;
		//print result
		//

	}
	
	public static String GetdateTime()
	{
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		return currentTime;
	}
	
	public static InputStream GetInputStream()
	{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();		
		InputStream resourceStream = loader.getResourceAsStream("config.properties");
		return resourceStream;
	}
	
	
	public static String GetConfigProperty(String propertName) throws IOException
	{
		Properties props = new Properties();
	    try {	    
	    		
	    	props.load(GetInputStream());
	    	
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    } finally {
	       
	    }
	    // get the property value and return it
	    return props.getProperty(propertName);
	}
	
}
