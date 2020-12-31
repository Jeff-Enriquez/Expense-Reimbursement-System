package com.ers.exceptions;

public class PasswordException extends Exception {
	public PasswordException(){
		super("Password Exception");
	}
	public PasswordException(String message){
		super(message);
	}
}
