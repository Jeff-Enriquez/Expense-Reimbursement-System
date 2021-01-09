package com.ers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection conn;
	private ConnectionFactory() {}
	public static Connection getConnection() {
		Connection con = null;
		if (System.getenv("ERS_HOSTNAME") != null) {
		      try {
		      Class.forName("org.postgresql.Driver");
		      String dbName = "";
		      String userName = System.getenv("DB_USERNAME");
		      String password = System.getenv("DB_PASSWORD");
		      String hostname = System.getenv("ERS_HOSTNAME");
		      String port = System.getenv("DB_PORT");
		      String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
		      con = DriverManager.getConnection(jdbcUrl);
		      conn = con;
		    }
		    catch (ClassNotFoundException e) { 
		    	e.printStackTrace();
		    }
		    catch (SQLException e) { 
		    	e.printStackTrace();
		    }
		}
		return conn;
	}
}