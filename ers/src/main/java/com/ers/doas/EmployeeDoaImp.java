package com.ers.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ers.exceptions.EmailException;
import com.ers.exceptions.PasswordException;
import com.ers.exceptions.UsernameException;
import com.ers.models.Employee;
import com.ers.util.ConnectionFactory;

public class EmployeeDoaImp  implements EmployeeDoa{
	private static Logger logger = LogManager.getLogger(EmployeeDoaImp.class);
	@Override
	public Employee selectEmployee(String username, String password) throws PasswordException, UsernameException {
		Employee employee = null;
		Connection conn = ConnectionFactory.getConnection();
		String sql = "select * from employee where "
				+ "username = ?;";
		String firstName = null, lastName = null, email = null;
		try(PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				throw new UsernameException("EmployeeDoa (selectEmployee): '" + username + "' was not found in database");
			}
			String confirmPassword = rs.getString("password");
			if(!password.equals(confirmPassword)) {				
				throw new PasswordException("EmployeeDoa (selectEmployee): '" + password + "' was not found in database");
			}
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			email = rs.getString("email");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			employee = new Employee(username, password, firstName, lastName, email);
		} catch(EmailException e) {
			logger.error("\u001B[31m" + "EmployeeDoaImp (selectEmployee): The employee email saved in the database for username: " + username + " is not a valid email" + "\u001B[0m");
		} catch(PasswordException e) {
			logger.error("\u001B[31m" + "EmployeeDoaImp (selectEmployee): The employee password saved in the database for username: " + username + " is not a valid password" + "\u001B[0m");
		}
		return employee;
	}
}
