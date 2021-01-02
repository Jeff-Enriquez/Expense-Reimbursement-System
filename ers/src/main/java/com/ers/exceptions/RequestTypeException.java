package com.ers.exceptions;

public class RequestTypeException extends Exception{
	public RequestTypeException(){
		super("Request Type Exception");
	}
	public RequestTypeException(String message){
		super(message);
	}
}
