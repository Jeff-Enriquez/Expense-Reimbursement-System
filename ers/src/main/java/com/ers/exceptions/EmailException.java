package com.ers.exceptions;

public class EmailException extends Exception{
	public EmailException(){
		super("Email Exception");
	}
	public EmailException(String message){
		super(message);
	}
}
