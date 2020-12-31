package com.ers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection conn;
	
	public static Connection getConnection() {
		Connection con = null;
		if (System.getenv("ERS_HOSTNAME") != null) {
		      try {
		      Class.forName("org.postgresql.Driver");
//			      String dbName = System.getenv("RDS_DB_NAME");
		      String dbName = "";
		      String userName = System.getenv("DB_USERNAME");
		      String password = System.getenv("DB_PASSWORD");
		      String hostname = System.getenv("ERS_HOSTNAME");
		      String port = System.getenv("DB_PORT");
		      String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
//			      logger.trace("Getting remote connection with connection string from environment variables.");
		      con = DriverManager.getConnection(jdbcUrl);
//			      logger.info("Remote connection successful.");
		      conn = con;
		    }
		    catch (ClassNotFoundException e) { 
		    	e.printStackTrace();
//			    	logger.warn(e.toString());}
		    }
		    catch (SQLException e) { 
		    	e.printStackTrace();
//			    	logger.warn(e.toString());}
		    }
		}
		return conn;
	}
}