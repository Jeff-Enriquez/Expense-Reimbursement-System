package com.ers.exceptions;

@SuppressWarnings("serial")
public class RequestTypeException extends Exception{
	public RequestTypeException(){
		super("Request Type Exception");
	}
	public RequestTypeException(String message){
		super(message);
	}
}
