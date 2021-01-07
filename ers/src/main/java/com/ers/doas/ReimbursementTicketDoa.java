package com.ers.doas;
import java.util.List;
import com.ers.models.ReimbursementTicket;

public interface ReimbursementTicketDoa {
	public List<ReimbursementTicket> selectTickets(String username);
	public List<ReimbursementTicket> getAllApproved();
	public List<ReimbursementTicket> getAllPending();
	public Boolean createTicket(ReimbursementTicket ticket, String username);
	public void approveTicket(Integer id);
	public void denyTicket(Integer id);
	
}
