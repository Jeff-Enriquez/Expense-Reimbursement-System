package com.ers.exceptions;

public class UsernameException extends Exception {
	public UsernameException(){
		super("Username Exception");
	}
	public UsernameException(String message){
		super(message);
	}
}
