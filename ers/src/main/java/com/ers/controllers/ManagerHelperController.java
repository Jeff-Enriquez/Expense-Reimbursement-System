package com.ers.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerHelperController {
	private ManagerHelperController() {}
	public static void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isLoggedIn = false;
		HttpSession sesh = req.getSession(false);
		if(sesh != null && sesh.getAttribute("manager") != null) {
			isLoggedIn = true;
		}
		if(isLoggedIn) {
			isLoggedInSwitch(req, resp);
		} else {			
			notLoggedInSwitch(req, resp);
		}
	}
	private static void isLoggedInSwitch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getRequestURI()) {
			case "/ers/manager":
				ManagerController.home(req, resp);
				break;
			case "/ers/manager/get-pending-tickets":
				ManagerController.getPendingTickets(req, resp);
				break;
			case "/ers/manager/get-past-tickets":
				ManagerController.getApprovedTickets(req, resp);
				break;
			case "/ers/manager/deny-ticket":
				ManagerController.denyTicket(req, resp);
				break;
			case "/ers/manager/approve-ticket":
				ManagerController.approveTicket(req, resp);
				break;
			case "/ers/manager/login":
				ManagerController.login(req, resp);
				break;
			case "/ers/manager/logout":
				ManagerController.logout(req,resp);
				break;
			default:
				resp.setStatus(404);
				break;	
		}
	}
	private static void notLoggedInSwitch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getRequestURI()) {
			case "/ers/manager/login":
				ManagerController.login(req, resp);
				break;
			default:
				resp.sendRedirect("/ers/manager/login");
		}
	}
}
