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
		return day + "/" + month + "/" + year.substring(2);
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
