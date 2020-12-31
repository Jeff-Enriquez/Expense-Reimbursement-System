package com.ers.employee.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmployeeHelperController {
	public static void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession sesh = req.getSession(false);
//		if(sesh == null) {
//			resp.getWriter().write("Get a session!");
//		}else{
//			System.out.println(sesh.getAttribute("user"));
//			if(sesh.getAttribute("user").equals(new User("Bob","superSecurePassword"))) {
//				resp.getWriter().write("Welcome! " + sesh.getAttribute("username"));
//			}else {
//				response.getWriter().write("you got a session, but you aint bobby");
//			}
//		}
		boolean sesh = true;
		if(sesh) {
			isLoggedInSwitch(req, resp);
		} else {			
			notLoggedInSwitch(req, resp);
		}
		
	}
	private static void isLoggedInSwitch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getRequestURI()) {
		case "/employee":
			EmployeeController.home(req, resp);
			break;
		case "/employee/login":
			EmployeeController.login(req, resp);
			break;
		case "employee/create":
			
			break;
		default:
			resp.setStatus(404);
			break;	
		}
	}
	private static void notLoggedInSwitch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
			case "/employee/login":
				EmployeeController.login(req, resp);
				break;
			case "/employee/create":
				
				break;
			default:
				resp.sendRedirect("/employee/login");
		}
	}
}
