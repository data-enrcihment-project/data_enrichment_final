package com.dcommerce.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;


/**
 * This class creates a connection pool with Database. The classes inside the
 * com.dcommerce.database package uses the connection resources and performs
 * queries for individual products.
 * 
 * @author Mushfiqul Huda
 */

public class ConnectionPool {


	// JDBC driver name & database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String JDBC_DB_URL = GetConfigProperty("dbURl");

	// JDBC database credentials
	static final String JDBC_USER = GetConfigProperty("dbuser");
	static final String JDBC_PASS = GetConfigProperty("dbpassword");

	private static GenericObjectPool gPool = null;

	@SuppressWarnings("unused")
	public DataSource setUpPool() throws Exception {
		Class.forName(JDBC_DRIVER).newInstance();

		// creates an instance of GenericObjectPool that holds the pool of connection
		gPool = new GenericObjectPool();
		gPool.setMaxActive(20);

		// creates a ConnectionFactory object which will be used by the pool 
		// to create the connection object!
		ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);

		// creates a PoolableConnectionFactory that wraps the connection object
		// to add object pooling functionality!
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf,gPool, null, null, false, true);
		return new PoolingDataSource(gPool);
	}

	
	
	public GenericObjectPool getConnectionPool() {
		return gPool;
	}

	
	
	// this method prints the status of Connection Pool
	public void printDbStatus() {
		System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: "
				+ getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
	}
	
	
	
	// this method reads the database credentials from the config.properties file
	public static String GetConfigProperty(String propertName)
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
	
	
	
	public static InputStream GetInputStream()
	{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();		
		InputStream resourceStream = loader.getResourceAsStream("config.properties");
		return resourceStream;
	}
}
