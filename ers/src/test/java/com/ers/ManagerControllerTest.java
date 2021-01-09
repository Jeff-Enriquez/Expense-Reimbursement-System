package com.ers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ers.controllers.ManagerController;
import com.ers.doas.ManagerDoaImp;
import com.ers.doas.ReimbursementTicketDoa;
import com.ers.doas.ReimbursementTicketDoaImp;
import com.ers.exceptions.EmailException;
import com.ers.exceptions.PasswordException;
import com.ers.exceptions.UsernameException;
import com.ers.models.Manager;
import com.ers.models.ReimbursementTicket;

class ManagerControllerTest {
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static HttpSession session;
	private static ReimbursementTicketDoa reimbursementTicketDao;
	
	@BeforeAll
	public static void beforeAll() throws PasswordException, EmailException {
		session = mock(HttpSession.class);
		when(session.getAttribute("manager")).thenReturn(new Manager("userName", "userPassword", "fName", "lName", "email@email.com"));
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
		when(request.getRequestDispatcher("/pages/Manager/Home/index.jsp")).thenReturn(dispatcher);
		ManagerController.home(request, response);
		verify(dispatcher).forward(request, response);
	}
	
	@Test
	void loginGET() throws IOException, ServletException{
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher("/pages/Manager/Login/index.html")).thenReturn(dispatcher);
		when(request.getMethod()).thenReturn("GET");
		ManagerController.login(request, response);
		verify(dispatcher).forward(request, response);
	}
	@Test
	void loginPOST() throws IOException, ServletException, PasswordException, UsernameException, EmailException{
		when(request.getMethod()).thenReturn("POST");
		when(request.getParameter("username")).thenReturn("userName");
		when(request.getParameter("password")).thenReturn("userPassword");
		ManagerDoaImp managerDoa = mock(ManagerDoaImp.class);
		when(managerDoa.selectManager("userName", "userPassword")).thenReturn(new Manager("userName", "userPassword", "fName", "lName", "email@email.com"));
		ManagerController.login(request, response);
		verify(response).sendRedirect("/manager");
	}
	@Test
	void logout() throws IOException, ServletException, PasswordException, EmailException {
		when(request.getMethod()).thenReturn("GET");
		when(request.getSession(false)).thenReturn(session);
		ManagerController.logout(request, response);
		verify(session).setAttribute("manager", null);
		verify(session).invalidate();
	}
	
	@Test
	void getApprovedTickets() throws IOException, ServletException {
		when(request.getMethod()).thenReturn("GET");
		when(reimbursementTicketDao.getAllApproved()).thenReturn(new ArrayList<ReimbursementTicket>());
		when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
		ManagerController.getApprovedTickets(request, response);
		verify(response).setContentType("application/json");
		verify(response).getWriter();
	}
	
	@Test
	void getPendingTickets() throws IOException, ServletException {
		when(request.getMethod()).thenReturn("GET");
		when(reimbursementTicketDao.getAllApproved()).thenReturn(new ArrayList<ReimbursementTicket>());
		when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
		ManagerController.getApprovedTickets(request, response);
		verify(response).setContentType("application/json");
		verify(response).getWriter();
	}
	
//	@Test
//	void denyTicket() throws IOException, ServletException {
//		when(request.getMethod()).thenReturn("PUT");
//		when(request.getSession()).thenReturn(session);
//		ObjectMapper om = mock(ObjectMapper.class);
//		ReimbursementTicket ticket = new ReimbursementTicket();
//		ticket.id = 1;
//		String json = "{\"id\":1, \"employee\":\"userName\", \"amount\":10, \"requestType\":\"other\"}";
//		when(request.getReader()).thenReturn(new BufferedReader(new StringReader(json)));
//		when(om.readValue(request.getReader(), com.ers.models.ReimbursementTicket.class)).thenReturn(ticket);
//		ManagerController.denyTicket(request, response);
//		verify(reimbursementTicketDao).denyTicket(1);
//	}
}
