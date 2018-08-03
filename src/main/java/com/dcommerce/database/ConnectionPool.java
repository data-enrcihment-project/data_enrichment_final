package com.dcommerce.database;

import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * This class creates a connection pool with Database. DatabaseQuery class 
 * under the same package uses the connection resources and performs queries.
 * 
 * @author Mushfiqul Huda
 */

public class ConnectionPool {

	// JDBC driver name & database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String JDBC_DB_URL = "jdbc:mysql://localhost/enrichment?autoReconnect=true&useSSL=false";

	// JDBC database credentials
	static final String JDBC_USER = "root";
	static final String JDBC_PASS = "Guitarhead03";

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
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
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
}
