package com.ers.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmployeeHelperController {
	private EmployeeHelperController() {}
	public static void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isLoggedIn = false;
		HttpSession sesh = req.getSession(false);
		if(sesh != null && sesh.getAttribute("employee") != null) {
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
		case "/ers/employee":
			EmployeeController.home(req, resp);
			break;
		case "/ers/employee/login":
			EmployeeController.login(req, resp);
			break;
		case "/ers/employee/create-ticket":
			EmployeeController.createTicket(req, resp);
			break;
		case "/ers/employee/logout":
			EmployeeController.logout(req,resp);
			break;
		default:
			resp.setStatus(404);
			break;	
		}
	}
	private static void notLoggedInSwitch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getRequestURI()) {
			case "/ers/employee/login":
				EmployeeController.login(req, resp);
				break;
			default:
				resp.sendRedirect("/ers/employee/login");
		}
	}
}
