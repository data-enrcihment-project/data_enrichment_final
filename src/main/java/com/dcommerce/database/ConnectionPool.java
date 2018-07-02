package com.dcommerce.database;

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
	static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/enrichment";//"jdbc:mysql://localhost/product-data?autoReconnect=true&useSSL=false";

	// JDBC database credentials
	static final String JDBC_USER = "root";
	static final String JDBC_PASS = "";

	private static GenericObjectPool gPool = null;

	@SuppressWarnings("unused")
	public DataSource setUpPool() throws Exception {
		Class.forName(JDBC_DRIVER).newInstance();

		// creates an instance of GenericObjectPool that holds the pool of connection
		gPool = new GenericObjectPool();
		gPool.setMaxActive(10);

		// creates a ConnectionFactory object which will be used by the pool 
		// to create the connection object!
		ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);

		// creates a PoolableConnectionFactory that wraps the connection object
		// to add object pooling functionality!
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf,gPool, null, null, false, true);
		return new PoolingDataSource();
	}

	public GenericObjectPool getConnectionPool() {
		return gPool;
	}

	// this method prints the status of Connection Pool
	public void printDbStatus() {
		System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: "
				+ getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
	}
}
