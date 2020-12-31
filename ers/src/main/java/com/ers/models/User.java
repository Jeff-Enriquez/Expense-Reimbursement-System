package com.ers.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ers.exceptions.EmailException;
import com.ers.exceptions.PasswordException;

public class User {
	public String username;
	private String password;
	public String firstName;
	public String lastName;
	private String email;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
	public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    } 
	
	User(){}
	User(String username, String password, String firstName, String lastName, String email) throws PasswordException, EmailException{
		this.username = username;
		this.setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.setEmail(email);
	}
	public void setPassword(String password) throws PasswordException {
		if(password.length() > 2) {
			this.password = password;
		} else {
			throw new PasswordException("User password must have a length of at least 2");
		}
	}
	public String getPassword() {
		return this.password;
	}
	public void setEmail(String email) throws EmailException {
		if(validate(email)) {
			this.email = email;
		} else {
			throw new EmailException("User email must follow format text@text.text");
		}
	}
	public String getEmail() {
		return this.email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (this.username != other.username || this.password != other.getPassword())
			return false;
		return true;
	}
}
