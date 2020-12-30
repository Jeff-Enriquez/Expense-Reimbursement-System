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
			String endpoint = req.getRequestURI();
			switch(endpoint) {
				case "/":
					resp.getWriter().write("Hello, you're in home");
					break;
				default:
					System.out.println(endpoint);
					break;
					
			}
			
		}
}
