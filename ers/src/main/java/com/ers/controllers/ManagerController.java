package com.ers.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ers.doas.ManagerDoa;
import com.ers.doas.ReimbursementTicketDoa;
import com.ers.models.Manager;
import com.ers.models.ReimbursementTicket;

public class ManagerController {
	private static Logger logger = LogManager.getLogger(ManagerController.class);

	public static void pendingTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		Manager manager = (Manager) req.getSession().getAttribute("manager");
		if(method.equals("GET")) {
			req.getSession().setAttribute("pendingTickets", ReimbursementTicketDoa.getAllPending());
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Manager/PendingTickets/index.jsp");
			redis.forward(req, resp);
			logger.info("Manager (pendingTickets): " + manager.username + " has requested to view pending tickets page.");
		} else {
			logger.warn("Manager (pendingTickets) - Invalid Request: " + manager.username + " tried to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
	public static void approvedTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		Manager manager = (Manager) req.getSession().getAttribute("manager");
		if(method.equals("GET")) {
			req.getSession().setAttribute("pendingTickets", ReimbursementTicketDoa.getAllPending());
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Manager/PendingTickets/index.jsp");
			redis.forward(req, resp);
			logger.info("Manager (pendingTickets): " + manager.username + " has requested to view pending tickets page.");
		} else {
			logger.warn("Manager (pendingTickets) - Invalid Request: " + manager.username + " tried to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
	public static void createTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		Manager manager = (Manager) req.getSession().getAttribute("manager");
		if(method.equals("GET")) {
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Manager/CreateTicket/index.html");
			redis.forward(req, resp);
			logger.info("Manager (createTicket): " + manager.username + " has requested to view create ticket page.");
		} else if(method.equals("POST")) {
			String number = req.getParameter("amount");
			String requestType = req.getParameter("request-type");
			String description = req.getParameter("description");
			try {
				Double amount = Double.parseDouble(number);
				try {
					ReimbursementTicket ticket = new ReimbursementTicket(amount, requestType, description);
					boolean isCreated = ReimbursementTicketDoa.createTicket(ticket, manager.username);
					if(isCreated) {
						logger.info("Manager (createTicket): " + manager.username + " has created a new ticket.");
					} else {
						logger.warn("Manager (createTicket): " + manager.username + " attempted to create a ticket but was unable to do so.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			resp.sendRedirect("/manager");
		} else {
			logger.warn("Manager (Invalid request): " + manager.username + " tried to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Manager/Login/index.html");
			redis.forward(req, resp);
			logger.info("User (login): User has requested to view login page.");
		} else if(method.equals("POST")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Manager manager = null;
			try {
				manager = ManagerDoa.selectManager(username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpSession sesh = req.getSession();
			if(manager != null) {
				sesh.setAttribute("manager", manager);
				logger.info("User (login): " + "the MANAGER " + manager.username + " has logged in.");
			}
			resp.sendRedirect("/manager/pending-tickets");
		} else {
			logger.warn("User (Invalid request): Attempt to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
}
