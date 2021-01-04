package com.ers.models;

import java.sql.Timestamp;

import com.ers.exceptions.AmountException;
import com.ers.exceptions.RequestTypeException;

public class ReimbursementTicket {
	public Integer id;
	private Double amount;
	private String requestType;
	private String description;
	public Timestamp timeSubmitted;
	public Boolean isApproved;
	public String employee;
	public ReimbursementTicket(){}
	public ReimbursementTicket(Integer id, String employee, Double amount, String requestType, String description, Timestamp timeSubmitted, Boolean isApproved) throws AmountException, RequestTypeException {
		this.id = id;
		this.employee = employee;
		setAmount(amount);
		setRequestType(requestType);
		this.description = description;
		this.timeSubmitted = timeSubmitted;
		this.isApproved = isApproved;
	}
	public ReimbursementTicket(Integer id, Double amount, String requestType, String description, Timestamp timeSubmitted, Boolean isApproved) throws AmountException, RequestTypeException {
		this.id = id;
		setAmount(amount);
		setRequestType(requestType);
		this.description = description;
		this.timeSubmitted = timeSubmitted;
		this.isApproved = isApproved;
	}
	public ReimbursementTicket(Double amount, String requestType, String description) throws AmountException, RequestTypeException {
		setAmount(amount);
		setRequestType(requestType);
		this.description = description;
	}
	public void setDescription(String description) {
		if(description == "") {
			this.description = null;
		} else {
			this.description = description.trim();
		}
	}
	public String getDescription() {
		return this.description;
	}
	public void setAmount(Double amount) throws AmountException {
		if(amount > 0) {
			this.amount = amount;
		} else {
			throw new AmountException("ReimbursementTicket: cannot set a negative amount");
		}
	}
	public Double getAmount() {
		return this.amount;
	}
	public void setRequestType(String type) throws RequestTypeException {
		switch(type) {
			case "lodging":
			case "travel":
			case "food":
			case "other":
				this.requestType = type;
				break;
			default: 
				throw new RequestTypeException();
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
		String[] dateAndTime = this.timeSubmitted.toString().split(" ");
		String date = dateAndTime[0];
		String time = dateAndTime[1];
		return formatDate(date) + " @" + formatTime(time);
	}
	private String formatDate(String date) {
		String[] yearMonthDay = date.split("-");
		String year = yearMonthDay[0];
		String month = yearMonthDay[1];
		String day = yearMonthDay[2];
		return month + "/" + day + "/" + year.substring(2);
	}
	private String formatTime(String time) {
		String postfix = "am";
		String[] hoursMinutes = time.split(":");
		String hours = hoursMinutes[0];
		String minutes = hoursMinutes[1];
		Integer hr = Integer.parseInt(hours);
		if(hr == 0) {
			hr = 12;
		} else if(hr >= 12) {
			postfix = "pm";
			if(hr > 12) {
				hr -= 12;
			}
		}
		hours = hr.toString();
		return hours + ":" + minutes + postfix;
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
