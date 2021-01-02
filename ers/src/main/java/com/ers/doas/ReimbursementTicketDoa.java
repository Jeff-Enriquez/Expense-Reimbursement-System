package com.ers.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				+ "employee = ?;";
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
		if(tickets.size() > 0) {
			System.out.println(tickets.get(0).toString());
		}
		return tickets;
	}
}
