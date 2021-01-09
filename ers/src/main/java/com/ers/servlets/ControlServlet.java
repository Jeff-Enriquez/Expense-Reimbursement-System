package com.ers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.controllers.EmployeeHelperController;
import com.ers.controllers.ManagerHelperController;

@SuppressWarnings("serial")
public class ControlServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String endpoint = req.getRequestURI();
		String uri1 = "";
		String[] uris = endpoint.split("/");
		if(endpoint.length() > 1) {
			uri1 = uris[1];
		}
		if(uri1.equals("manager")) {
			try {				
				ManagerHelperController.process(req, resp);
			} catch(ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if(uri1.equals("employee")) {
			try {				
				EmployeeHelperController.process(req, resp);
			} catch(ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			resp.setStatus(404);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}

}
