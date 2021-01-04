package com.ers.doas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ers.exceptions.AmountException;
import com.ers.exceptions.RequestTypeException;
import com.ers.models.ReimbursementTicket;
import com.ers.util.ConnectionFactory;

public class ReimbursementTicketDoa {
	public static List<ReimbursementTicket> selectTickets(String username){
		List<ReimbursementTicket> tickets = new ArrayList<ReimbursementTicket>();
		Connection conn = ConnectionFactory.getConnection();
		String sql = "select * from reimbursement_ticket where "
				+ "employee = ? order by time_submitted desc;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				Double amount = rs.getDouble("amount");
				String requestType = rs.getString("request_type");
				String description = rs.getString("description");
				Timestamp timeSubmitted = rs.getTimestamp("time_submitted");
				Boolean isApproved = rs.getBoolean("is_approved");
				try {
					ReimbursementTicket ticket = new ReimbursementTicket(id, amount, requestType, description, timeSubmitted, isApproved);
					tickets.add(ticket);
				} catch (AmountException e) {
					e.printStackTrace();
				} catch (RequestTypeException e) {
					e.printStackTrace();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}
	public static List<ReimbursementTicket> getAllPending(){
		return getTickets(false);
	}
	public static List<ReimbursementTicket> getAllApproved(){
		return getTickets(true);
	}
	private static List<ReimbursementTicket> getTickets(boolean isApproved){
		List<ReimbursementTicket> tickets = new ArrayList<ReimbursementTicket>();
		Connection conn = ConnectionFactory.getConnection();
		String sql;
		if(isApproved) {
			sql = "select * from reimbursement_ticket where "
					+ "is_approved = true order by time_submitted desc;";
		} else {
			sql = "select * from reimbursement_ticket where "
					+ "is_approved = true order by time_submitted";
		}
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String employee = rs.getString("employee");
				Double amount = rs.getDouble("amount");
				String requestType = rs.getString("request_type");
				String description = rs.getString("description");
				Timestamp timeSubmitted = rs.getTimestamp("time_submitted");
				Boolean approved = rs.getBoolean("is_approved");
				try {
					ReimbursementTicket ticket = new ReimbursementTicket(id, employee, amount, requestType, description, timeSubmitted, approved);
					tickets.add(ticket);
				} catch (AmountException e) {
					e.printStackTrace();
				} catch (RequestTypeException e) {
					e.printStackTrace();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}
	public static Boolean createTicket(ReimbursementTicket ticket, String username) {
		Boolean isSuccess = false;
		Connection conn = ConnectionFactory.getConnection();
		String sql = null;
		if(ticket.getDescription() != null) {
			sql = "call add_reimbursement_ticket(?,?,?,?);";
		} else {
			sql = "call add_reimbursement_ticket(?,?,?);";
		}
		try {
			CallableStatement callSt = conn.prepareCall(sql);
			callSt.setString(1, username);
			callSt.setDouble(2, ticket.getAmount());
			callSt.setString(3, ticket.getRequestType());
			if(ticket.getDescription() != null) {
				callSt.setString(4, ticket.getDescription());
			}
			callSt.execute();
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	public static void approveTicket(Integer id) {
		Connection conn = ConnectionFactory.getConnection();
		String sql = "update reimbursement_ticket set is_approved = true where id = ?;";
		try {
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void denyTicket(Integer id) {
		Connection conn = ConnectionFactory.getConnection();
		String sql = "delete from reimbursement_ticket where id = ? && is_approved = false;";
		try {
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
