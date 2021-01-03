package com.ers.models;

import com.ers.exceptions.EmailException;
import com.ers.exceptions.PasswordException;

public class Manager extends User {
	public Manager(){}
	public Manager(String username, String password, String firstName, String lastName, String email) throws PasswordException, EmailException{
		super(username, password, firstName, lastName, email);
	}

}
