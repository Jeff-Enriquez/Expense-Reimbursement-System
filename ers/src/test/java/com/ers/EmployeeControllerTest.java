package com.ers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.*;

import com.ers.controllers.EmployeeController;
import com.ers.doas.EmployeeDoaImp;
import com.ers.doas.ReimbursementTicketDoaImp;
import com.ers.exceptions.EmailException;
import com.ers.exceptions.PasswordException;
import com.ers.exceptions.UsernameException;
import com.ers.models.Employee;
import com.ers.models.ReimbursementTicket;

class EmployeeControllerTest {
	
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static HttpSession session;
	private static ReimbursementTicketDoaImp reimbursementTicketDao;
	
	@BeforeAll
	public static void beforeAll() throws PasswordException, EmailException {
		session = mock(HttpSession.class);
		when(session.getAttribute("employee")).thenReturn(new Employee("userName", "userPassword", "fName", "lName", "email@email.com"));
		reimbursementTicketDao = mock(ReimbursementTicketDoaImp.class);
	}
	
	@BeforeEach
	void beforeEach() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
	}
	
	@Test
	void HomeGET() throws IOException, ServletException{
		when(request.getMethod()).thenReturn("GET");
		when(request.getSession()).thenReturn(session);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher("/pages/Employee/Home/index.jsp")).thenReturn(dispatcher);
		EmployeeController.home(request, response);
		verify(dispatcher).forward(request, response);
	}
	
	@Test
	void createTicketGET() throws IOException, ServletException{
		when(request.getMethod()).thenReturn("GET");
		when(request.getSession()).thenReturn(session);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher("/pages/Employee/CreateTicket/index.html")).thenReturn(dispatcher);
		EmployeeController.createTicket(request, response);
		verify(dispatcher).forward(request, response);
	}
	
	@Test
	void createTicketPOST() throws IOException, ServletException{
		when(request.getMethod()).thenReturn("GET");
		when(request.getSession()).thenReturn(session);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher("/pages/Employee/CreateTicket/index.html")).thenReturn(dispatcher);
		when(reimbursementTicketDao.createTicket(new ReimbursementTicket(), "userName")).thenReturn(true);
		EmployeeController.createTicket(request, response);
		verify(dispatcher).forward(request, response);
	}
	
	@Test
	void loginGET() throws IOException, ServletException{
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher("/pages/Employee/Login/index.html")).thenReturn(dispatcher);
		when(request.getMethod()).thenReturn("GET");
		EmployeeController.login(request, response);
		verify(dispatcher).forward(request, response);
	}
	@Test
	void loginPOST() throws IOException, ServletException, PasswordException, UsernameException, EmailException{
		when(request.getMethod()).thenReturn("POST");
		when(request.getParameter("username")).thenReturn("userName");
		when(request.getParameter("password")).thenReturn("userPassword");
		EmployeeDoaImp employeeDoa = mock(EmployeeDoaImp.class);
		Employee employee = null;
		employee = new Employee("userName", "userPassword", "fName", "lName", "email@email.com");
		when(employeeDoa.selectEmployee("userName", "userPassword")).thenReturn(employee);
		EmployeeController.login(request, response);
		verify(response).sendRedirect("/employee");
	}
	@Test
	void logout() throws IOException, ServletException, PasswordException, EmailException {
		when(request.getMethod()).thenReturn("GET");
		when(request.getSession(false)).thenReturn(session);
		EmployeeController.logout(request, response);
		verify(session).setAttribute("employee", null);
		verify(session).invalidate();
	}
}
