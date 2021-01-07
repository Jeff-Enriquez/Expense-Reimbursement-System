package com.ers.doas;

import com.ers.exceptions.PasswordException;
import com.ers.exceptions.UsernameException;
import com.ers.models.Employee;

public interface EmployeeDoa {
	public Employee selectEmployee(String username, String password) throws PasswordException, UsernameException;
}
