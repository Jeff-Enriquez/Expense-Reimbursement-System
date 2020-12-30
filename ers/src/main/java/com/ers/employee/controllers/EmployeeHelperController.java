package com.ers.employee.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeHelperController {
	public static void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
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
