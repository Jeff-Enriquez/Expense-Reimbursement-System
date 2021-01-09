package com.ers.exceptions;

@SuppressWarnings("serial")
public class AmountException extends Exception{
	public AmountException(){
		super("Amount Exception");
	}
	public AmountException(String message){
		super(message);
	}
}
