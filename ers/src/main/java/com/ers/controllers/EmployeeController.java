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
import com.ers.doas.EmployeeDoaImp;
import com.ers.doas.ReimbursementTicketDoa;
import com.ers.doas.ReimbursementTicketDoaImp;
import com.ers.models.Employee;
import com.ers.models.ReimbursementTicket;

public class EmployeeController {
	private static Logger logger = LogManager.getLogger(EmployeeController.class);
	private static EmployeeDoa employeeDoa = new EmployeeDoaImp();
	private static ReimbursementTicketDoa reimbursementTicketDoa = new ReimbursementTicketDoaImp(); 
	public static void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		HttpSession sesh = req.getSession();
		Employee employee = (Employee) sesh.getAttribute("employee");
		if(method.equals("GET")) {
			employee.setTickets(reimbursementTicketDoa.selectTickets(employee.username));
			sesh.setAttribute("employee", employee);
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/Home/index.jsp");
			logger.info("Employee (home): " + employee.username + " has requested to view home page.");
			redis.forward(req, resp);
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
			logger.info("Employee (createTicket): " + employee.username + " has requested to view create ticket page.");
			redis.forward(req, resp);
		} else if(method.equals("POST")) {
			String number = req.getParameter("amount");
			String requestType = req.getParameter("request-type");
			String description = req.getParameter("description");
			try {
				Double amount = Double.parseDouble(number);
				ReimbursementTicket ticket = new ReimbursementTicket(amount, requestType, description);
				boolean isCreated = reimbursementTicketDoa.createTicket(ticket, employee.username);
				if(isCreated) {
					logger.info("Employee (createTicket): " + employee.username + " has created a new ticket.");
				} else {
					logger.warn("Employee (createTicket): " + employee.username + " attempted to create a ticket but was unable to do so.");
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
//	public static void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		String method = req.getMethod();
//		System.out.println("CREATE ACCOUNT");
//		if(method.equals("GET")) {
//			logger.info("User (createEmployee): User has requested to view create-employee page.");
//			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/CreateAccount/index.html");
//			redis.forward(req, resp);
//		} else if(method.equals("POST")) {
//			
//		} else {
//			logger.warn("User (Invalid Request): Attempt to make a " + method + " request to " + req.getRequestURI());
//			resp.setStatus(405);
//		}
//	}
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			logger.info("User (login): User has requested to view login page.");
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/Login/index.html");
			redis.forward(req, resp);
		} else if(method.equals("POST")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Employee employee = null;
			try {				
				employee = employeeDoa.selectEmployee(username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpSession sesh = req.getSession();
			if(employee != null) {
				employee.setTickets(reimbursementTicketDoa.selectTickets(employee.username));
				sesh.setAttribute("employee", employee);
				logger.info("User (login): " + "the EMPLOYEE " + employee.username + " has logged in.");
			}
			resp.sendRedirect("/employee");
		} else {
			logger.warn("User (Invalid request): Attempt to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		HttpSession sesh = req.getSession(false);
		Employee employee = (Employee) sesh.getAttribute("employee");
		if(method.equals("GET")) {
			logger.info("Employee (logout): the EMPLOYEE " + employee.username + " is logging out");
			sesh.setAttribute("employee", null);
			sesh.invalidate();
		} else {
			logger.warn("Employee (Invalid request): Attempt to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
		resp.sendRedirect("/employee/login");
	}
}
