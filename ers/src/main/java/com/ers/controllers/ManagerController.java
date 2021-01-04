package com.ers.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManagerController {
	private static Logger logger = LogManager.getLogger(ManagerController.class);

	public static void getPendingTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			List<ReimbursementTicket> tickets = new ArrayList<ReimbursementTicket>();
			tickets = ReimbursementTicketDoa.getAllPending();
			resp.setContentType("application/json");
			ObjectMapper om = new ObjectMapper();
			resp.getWriter().write(om.writeValueAsString(tickets));
		}
	}
	public static void getApprovedTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			List<ReimbursementTicket> tickets = new ArrayList<ReimbursementTicket>();
			tickets = ReimbursementTicketDoa.getAllApproved();
			resp.setContentType("application/json");
			ObjectMapper om = new ObjectMapper();
			resp.getWriter().write(om.writeValueAsString(tickets));
		}
	}
	public static void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		Manager manager = (Manager) req.getSession().getAttribute("manager");
		if(method.equals("GET")) {
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Manager/Home/index.jsp");
			redis.forward(req, resp);
			logger.info("Manager (pendingTickets): " + manager.username + " has requested to view home page.");
		} else {
			logger.warn("Manager (pendingTickets) - Invalid Request: " + manager.username + " tried to make a " + method + " request to " + req.getRequestURI());
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
			resp.sendRedirect("/manager");
		} else {
			logger.warn("User (Invalid request): Attempt to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
}
