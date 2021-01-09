package com.ers.exceptions;

@SuppressWarnings("serial")
public class UsernameException extends Exception {
	public UsernameException(){
		super("Username Exception");
	}
	public UsernameException(String message){
		super(message);
	}
}
