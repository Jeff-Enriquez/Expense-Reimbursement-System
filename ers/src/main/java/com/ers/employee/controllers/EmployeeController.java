package com.ers.employee.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeController {
	public static void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/Home/index.html");
			redis.forward(req, resp);
		} else {
			resp.setStatus(405);
		}
		
	}
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/Login/index.html");
			redis.forward(req, resp);
		} else if(method.equals("POST")) {
			
		} else {
			resp.setStatus(405);
		}
	}
}
