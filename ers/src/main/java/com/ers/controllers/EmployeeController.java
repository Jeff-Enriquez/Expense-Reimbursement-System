package com.ers.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ers.doas.EmployeeDoa;
import com.ers.doas.ReimbursementTicketDoa;
import com.ers.models.Employee;
import com.ers.models.ReimbursementTicket;

public class EmployeeController {
	private static Logger logger = LogManager.getLogger(EmployeeController.class);

	public static void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		HttpSession sesh = req.getSession();
		Employee employee = (Employee) sesh.getAttribute("employee");
		if(method.equals("GET")) {
			employee.setTickets(ReimbursementTicketDoa.selectTickets(employee.username));
			sesh.setAttribute("employee", employee);
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/Home/index.jsp");
			redis.forward(req, resp);
			logger.info("Employee (home): " + employee.username + " has requested to view home page.");
		} else {
			logger.warn("Employee (Invalid request): " + employee.username + " tried to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
		
	}
	public static void createTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		Employee employee = (Employee) req.getSession().getAttribute("employee");
		if(method.equals("GET")) {
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/CreateTicket/index.html");
			redis.forward(req, resp);
			logger.info("Employee (createTicket): " + employee.username + " has requested to view create ticket page.");
		} else if(method.equals("POST")) {
			String number = req.getParameter("amount");
			String requestType = req.getParameter("request-type");
			String description = req.getParameter("description");
			try {
				Double amount = Double.parseDouble(number);
				try {
					ReimbursementTicket ticket = new ReimbursementTicket(amount, requestType, description);
					boolean isCreated = ReimbursementTicketDoa.createTicket(ticket, employee.username);
					if(isCreated) {
						logger.info("Employee (createTicket): " + employee.username + " has created a new ticket.");
					} else {
						logger.warn("Employee (createTicket): " + employee.username + " attempted to create a ticket but was unable to do so.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			resp.sendRedirect("/employee");
		} else {
			logger.warn("Employee (Invalid request): " + employee.username + " tried to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/Login/index.html");
			redis.forward(req, resp);
			logger.info("User (login): User has requested to view login page.");
		} else if(method.equals("POST")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Employee employee = null;
			try {				
				employee = EmployeeDoa.selectEmployee(username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpSession sesh = req.getSession();
			if(employee != null) {
				employee.setTickets(ReimbursementTicketDoa.selectTickets(employee.username));
				sesh.setAttribute("employee", employee);
				logger.info("User (login): " + "the EMPLOYEE " + employee.username + " has logged in.");
			}
			resp.sendRedirect("/employee");
		} else {
			logger.warn("User (Invalid request): Attempt to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
}