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
import com.ers.doas.ManagerDoaImp;
import com.ers.doas.ReimbursementTicketDoa;
import com.ers.doas.ReimbursementTicketDoaImp;
import com.ers.models.Manager;
import com.ers.models.ReimbursementTicket;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManagerController {
	private static Logger logger = LogManager.getLogger(ManagerController.class);
	private static ManagerDoa managerDoa = new ManagerDoaImp();
	private static ReimbursementTicketDoa reimbursementTicketDoa = new ReimbursementTicketDoaImp(); 
	public static void approveTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		Manager manager = (Manager) req.getSession().getAttribute("manager");
		if(req.getMethod().equals("PUT")) {
			ObjectMapper om = new ObjectMapper();
			ReimbursementTicket ticket = om.readValue(req.getReader(), com.ers.models.ReimbursementTicket.class);
			reimbursementTicketDoa.approveTicket(ticket.id);
			logger.info("Manger (approveTicket): " + manager.username + " approved the ticket: id-" + ticket.id + " employee-" + ticket.employee);
		} else {
			logger.warn("Manager (approveTicket) - Invalid Request: " + manager.username + " tried to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
	public static void denyTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		Manager manager = (Manager) req.getSession().getAttribute("manager");
		if(req.getMethod().equals("PUT")) {			
			ObjectMapper om = new ObjectMapper();
			ReimbursementTicket ticket = om.readValue(req.getReader(), com.ers.models.ReimbursementTicket.class);
			System.out.println("TICKET: " + ticket + ticket.employee);
			reimbursementTicketDoa.denyTicket(ticket.id);
			logger.info("Manger (denyTicket): " + manager.username + " denied the ticket: id-" + ticket.id + " employee-" + ticket.employee);
			reimbursementTicketDoa.denyTicket(ticket.id);
		} else {
			logger.warn("Manager (denyTicket) - Invalid Request: " + manager.username + " tried to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
	public static void getPendingTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			List<ReimbursementTicket> tickets = new ArrayList<ReimbursementTicket>();
			tickets = reimbursementTicketDoa.getAllPending();
			resp.setContentType("application/json");
			ObjectMapper om = new ObjectMapper();
			resp.getWriter().write(om.writeValueAsString(tickets));
		} else {
			Manager manager = (Manager) req.getSession().getAttribute("manager");
			logger.warn("Manager (pendingTickets) - Invalid Request: " + manager.username + " tried to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
	public static void getApprovedTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			List<ReimbursementTicket> tickets = new ArrayList<ReimbursementTicket>();
			tickets = reimbursementTicketDoa.getAllApproved();
			resp.setContentType("application/json");
			ObjectMapper om = new ObjectMapper();
			resp.getWriter().write(om.writeValueAsString(tickets));
		} else {
			Manager manager = (Manager) req.getSession().getAttribute("manager");
			logger.warn("Manager (getApprovedTickets) - Invalid Request: " + manager.username + " tried to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
	}
	public static void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		Manager manager = (Manager) req.getSession().getAttribute("manager");
		if(method.equals("GET")) {
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Manager/Home/index.jsp");
			redis.forward(req, resp);
			logger.info("Manager (home): " + manager.username + " has requested to view home page.");
		} else {
			logger.warn("Manager (home) - Invalid Request: " + manager.username + " tried to make a " + method + " request to " + req.getRequestURI());
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
				manager = managerDoa.selectManager(username, password);
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
	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		HttpSession sesh = req.getSession(false);
		Manager manager = (Manager) sesh.getAttribute("manager");
		if(method.equals("GET")) {
			logger.info("Manager (logout): the EMPLOYEE " + manager.username + " is logging out");
			sesh.setAttribute("manager", null);
			sesh.invalidate();
		} else {
			logger.warn("Manager (Invalid request): Attempt to make a " + method + " request to " + req.getRequestURI());
			resp.setStatus(405);
		}
		resp.sendRedirect("/manager/login");
	}
}
