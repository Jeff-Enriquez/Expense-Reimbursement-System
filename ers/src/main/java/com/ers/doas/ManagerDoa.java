package com.ers.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ers.exceptions.EmailException;
import com.ers.exceptions.PasswordException;
import com.ers.exceptions.UsernameException;
import com.ers.models.Manager;
import com.ers.util.ConnectionFactory;

public class ManagerDoa {
	public static Manager selectManager(String username, String password) throws PasswordException, UsernameException {
		Manager manager = null;
		Connection conn = ConnectionFactory.getConnection();
		String sql = "select * from manager where "
				+ "username = ?;";
		String firstName = null, lastName = null, email = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				throw new UsernameException("ManagerDoa (selectManager): '" + username + "' was not found in database");
			}
			String confirmPassword = rs.getString("password");
			if(!password.equals(confirmPassword)) {				
				throw new PasswordException("ManagerDoa (selectManager): '" + password + "' was not found in database");
			}
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			email = rs.getString("email");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			manager = new Manager(username, password, firstName, lastName, email);
		} catch(EmailException e) {
			System.out.println("\u001B[31m" + "ManagerDoa (selectManager): The manager email saved in the database for username: " + username + " is not a valid email" + "\u001B[0m");
		} catch(PasswordException e) {
			System.out.println("\u001B[31m" + "ManagerDoa (selectManager): The manager password saved in the database for username: " + username + " is not a valid password" + "\u001B[0m");
		}
		return manager;
	}
}
