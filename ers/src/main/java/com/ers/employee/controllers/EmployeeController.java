package com.ers.employee.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeController {
	public static void getHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		if(req.getMethod().equals("GET")) {
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/Home/index.html");
			redis.forward(req, resp);
		} else {
			resp.setStatus(405);
		}
		
	}
}
