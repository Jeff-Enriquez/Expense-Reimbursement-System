package com.ers.models;

import java.util.ArrayList;
import java.util.List;

import com.ers.exceptions.EmailException;
import com.ers.exceptions.PasswordException;

public class Employee extends User{
	public List<ReimbursementTicket> tickets = new ArrayList<ReimbursementTicket>();
	public Employee(){
		super();
	}
	public Employee(String username, String password, String firstName, String lastName, String email) throws PasswordException, EmailException{
		super(username, password, firstName, lastName, email);
	}
	public void setTickets(List<ReimbursementTicket> tickets) {
		this.tickets = tickets;
	}
	public List<ReimbursementTicket> getTickets() {
		return this.tickets;
	}
	public void addTicket(ReimbursementTicket ticket) {
		tickets.add(ticket);
	}
}
