package com.ers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.employee.controllers.EmployeeHelperController;

public class ControlServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String endpoint = req.getRequestURI();
		/*
		 * manager will be the first part of the path.
		 * Example: if the uri is /[first]/[second] then manager == [first]
		 */
		String manager = "";
		String[] uris = endpoint.split("/");
		if(endpoint.length() > 1) {
			manager = uris[1];
		}
		if(manager.equals("manager")) {
			System.out.println("Send to ManagerHelperController");
		} else {
			System.out.println("Sending to EmployeeHelperController");
			EmployeeHelperController.process(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
