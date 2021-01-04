<%@page import="java.util.List"%>
<%@page import="com.ers.models.ReimbursementTicket"%>
<%@page import="com.ers.models.Employee"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ERS - Home</title>
    <link rel="stylesheet" type="text/css" href="/pages/Employee/Home/index.css">
    <link rel="stylesheet" type="text/css" href="/pages/nav.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <script defer src="/pages/Employee/Home/viewOptionsController.js"></script>
</head>
<body>
    <nav class='navbar'>
        <div class="navDiv">
            <a class="navA" href="">
                <img class="navImg" src="https://img.icons8.com/small/32/ffffff/home.png"/>
            </a>
        </div>
        <div class="navDiv">
            <a class="navA" href="/employee/create-ticket">Create Ticket</a>
        </div>
        <div class="navDiv">
            <a class="navA" href="">
                <span class="material-icons icon">settings</span>
            </a>
        </div>
    </nav>
    <h1 class="title">My Tickets</h1>
    <div class="filter-container">
        <div class="filter-status">
            <button class="selected" id="all">All</button>
            <button class="not-selected" id="past">Approved</button>
            <button class="not-selected" id="pending">Pending</button>
        </div>
    </div>
    <div class="tickets-container"> 
    <% 
    	Employee employee = (Employee)request.getSession().getAttribute("employee");
 		List<ReimbursementTicket> tickets = employee.getTickets();
	    for(ReimbursementTicket ticket : tickets)
	    {
	        out.print("<div class='ticket-card' data-" + ticket.getIsApproved() + ">");
	        out.print("<div class='small-heading'>");
	        out.print("<p class='ticket-type'>" + ticket.getRequestType() + "</p>");
	        out.print("<p class='ticket-status " + ticket.getIsApproved() + "'>" + ticket.getIsApproved() + "</p>");
	        out.print("</div>");
	        out.print("<div class='ticket-information-container'>");
	        out.print("<div class='ticket'>");
	        out.print("<div>");
	        out.print("<p class='ticket-heading'>ID:</p>");
	        out.print("<p class='ticket-details'>" + ticket.id + "</p>");
	        out.print("</div>");
	        out.print("<div>");
	        out.print("<p class='ticket-heading'>Amount:</p>");
	        out.print("<p class='ticket-details'>$" + String.format("%.2f", ticket.getAmount()) + "</p>");
	        out.print("</div>");
	        out.print("<div>");
	        out.print("<p class='ticket-heading'>Submitted At:</p>");
	        out.print("<p class='ticket-details'>" + ticket.timeSubmitted() + "</p>");
	        out.print("</div>");
	        out.print("</div>");
	        out.print("<div class='description-container'>");
	        out.print("<p class='ticket-heading'>Description</p>");
	        if(ticket.getDescription() != null){
		        out.print("<p class='ticket-details'>" + ticket.getDescription() + "</p>");        	
	        } else {
	        	out.print("<p class='ticket-details'>No description</p>");
	        }
	        out.print("</div>");
	        out.print("</div>");
	        out.print("</div>");
	    }
	 
	%>
   </div>
</body>
</html>