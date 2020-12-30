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
		String method = req.getMethod();
		System.out.println("in employee logged in switch");
		System.out.println("Method: " + method);
		System.out.println("Request: " + req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/employee":
			if(method.equals("GET")) {
				System.out.println("going to getHOME");
				EmployeeController.getHome(req, resp);
			} else {
				resp.setStatus(405);
			}
			break;
		case "/employee/login":
			if(method.equals("GET")) {
				// get page from employee controller
			} else if(method.equals("POST")) {
				// send to employee controller
			} else {
				resp.setStatus(405);
			}
			break;
		case "employee/create":
			if(method.equals("GET")) {
				// get page from employee controller
			} else if(method.equals("POST")) {
				// send to employee controller
			} else {
				resp.setStatus(405);
			}
			break;
		default:
			System.out.println("Default was hit");
			// redirect to home
			break;	
		}
	}
	private static void notLoggedInSwitch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		switch(req.getRequestURI()) {
		case "/login":
			if(method.equals("GET")) {
				// get page from employee controller
			} else if(method.equals("POST")) {
				// send to employee controller
			} else {
				resp.setStatus(405);
			}
			break;
		case "/create":
			if(method.equals("GET")) {
				// get page from employee controller
			} else if(method.equals("POST")) {
				// send to employee controller
			} else {
				resp.setStatus(405);
			}
			break;
		default:
			// redirect to home
		}
	}
}
