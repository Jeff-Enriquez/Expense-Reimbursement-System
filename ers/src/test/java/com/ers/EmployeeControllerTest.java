package com.ers;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.MockedStatic;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import com.ers.controllers.EmployeeController;
import com.ers.doas.EmployeeDoa;
import com.ers.doas.ReimbursementTicketDoa;
import com.ers.exceptions.PasswordException;
import com.ers.exceptions.UsernameException;
import com.ers.models.Employee;
import com.ers.models.ReimbursementTicket;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest(EmployeeDoa.class)
class EmployeeControllerTest {
	
	private static HttpServletRequest request;
	private static HttpServletResponse response;
//	private static MockedStatic<EmployeeDoa> employee;
	
	@BeforeAll
	public static void beforeAll() {
//		employee = mockStatic(EmployeeDoa.class);
	}
	
	@BeforeEach
	public void setUp() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
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
	void loginPOST() throws IOException, ServletException, PasswordException, UsernameException{
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher("/pages/Employee/Login/index.html")).thenReturn(dispatcher);
		when(request.getMethod()).thenReturn("POST");
		when(request.getParameter("username")).thenReturn("jeff");
		when(request.getParameter("password")).thenReturn("password");
		PowerMockito.mockStatic(EmployeeDoa.class);
		BDDMockito.given(EmployeeDoa.selectEmployee("jeff", "password")).willReturn(new Employee());
		PowerMockito.mockStatic(ReimbursementTicketDoa.class);
		BDDMockito.given(ReimbursementTicketDoa.selectTickets(null)).willReturn(new ArrayList<ReimbursementTicket>());

		EmployeeController.login(request, response);
		
		verify(response).sendRedirect("/employee");
	}
}
