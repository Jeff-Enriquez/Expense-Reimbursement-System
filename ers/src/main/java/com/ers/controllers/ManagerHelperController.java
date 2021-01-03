package com.ers.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerHelperController {
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
			case "/manager/pending-tickets":
				ManagerController.pendingTickets(req, resp);
				break;
			case "/manager/approved-tickets":
				break;
			case "/manager/login":
				ManagerController.login(req, resp);
				break;
			default:
				resp.setStatus(404);
				break;	
		}
	}
	private static void notLoggedInSwitch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getRequestURI()) {
			case "/manager/login":
				ManagerController.login(req, resp);
				break;
			case "/manager/create-manager":
				
				break;
			default:
				resp.sendRedirect("/manager/login");
		}
	}
}
