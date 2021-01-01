package com.ers.employee.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.doas.EmployeeDoa;
import com.ers.doas.ReimbursementTicketDoa;
import com.ers.models.Employee;

public class EmployeeController {
	public static void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String method = req.getMethod();
		if(method.equals("GET")) {
			Employee employee = (Employee) req.getSession().getAttribute("employee");
			req.setAttribute("username", employee.username);
			RequestDispatcher redis = req.getRequestDispatcher("/pages/Employee/Home/index.jsp");
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
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Employee employee = null;
			try {				
				employee = EmployeeDoa.selectEmployee(username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpSession sesh = req.getSession();
			if(employee != null) {
				employee.setTickets(ReimbursementTicketDoa.selectTickets(employee.username));
				sesh.setAttribute("employee", employee);
			}
			resp.sendRedirect("/employee");
		} else {
			resp.setStatus(405);
		}
	}
}
