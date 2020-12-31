package com.ers.models;

import com.ers.exceptions.EmailException;
import com.ers.exceptions.PasswordException;

public class Employee extends User{
	public Employee(){
		super();
	}
	public Employee(String username, String password, String firstName, String lastName, String email) throws PasswordException, EmailException{
		super(username, password, firstName, lastName, email);
	}
}
