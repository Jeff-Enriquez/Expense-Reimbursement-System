package com.ers.exceptions;

public class AmountException extends Exception{
	public AmountException(){
		super("Amount Exception");
	}
	public AmountException(String message){
		super(message);
	}
}
