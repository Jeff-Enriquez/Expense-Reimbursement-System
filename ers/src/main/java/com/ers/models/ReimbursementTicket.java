package com.ers.models;

import java.sql.Timestamp;

public class ReimbursementTicket {
	public Integer id;
	public Double amount;
	private String requestType;
	public String description;
	public Timestamp timeSubmitted;
	public Boolean isApproved;
	public ReimbursementTicket(){}
	public ReimbursementTicket(Integer id, Double amount, String requestType, String description, Timestamp timeSubmitted, Boolean isApproved) {
		this.id = id;
		this.amount = amount;
		setRequestType(requestType);
		this.description = description;
		this.timeSubmitted = timeSubmitted;
		this.isApproved = isApproved;
	}
	public void setRequestType(String type) {
		switch(type) {
			case "lodging":
			case "travel":
			case "food":
			case "other":
				this.requestType = type;
				break;
			default: 
				this.requestType = "";
		}
	}
	public String getIsApproved() {
		if(isApproved) {
			return "approved";
		} else {
			return "pending";
		}
	}
	public String getRequestType() {
		return this.requestType;
	}
	public String timeSubmitted() {
		return this.timeSubmitted.toString();
	}
	@Override
	public String toString() {
		return "TICKET (" + this.id + ")\n" + 
				"AMOUNT: " + this.amount + "\n" +
				"REQUEST_TYPE: " + this.requestType + "\n" +
				"DESCRIPTION: " + this.description + "\n" +
				"TIME_SUBMITTED: " + this.timeSubmitted + "\n" +
				"IS_APPROVED: " + this.isApproved;
	}
}
