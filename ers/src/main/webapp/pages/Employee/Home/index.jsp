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
            <a class="navA" href="../CreateTicket/index.html">Create Ticket</a>
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
        <div class="ticket-card" data-approved>
            <div class="small-heading">
                <p class="ticket-type">Lodging</p>
                <p class="ticket-status approved">Approved</p>
            </div>
            <div class="ticket-information">
                <div class="ticket-heading">
                    <p>ID:</p>
                    <p>Amount:</p>
                    <p>Name:</p>
                    <p>Submitted At:</p>
                </div>
                <div class="ticket-details">
                    <p>132</p>
                    <p>$987</p>
                    <p>Troy Fiawoo</p>
                    <p>09/13/20 @4:39pm</p>
                </div>
                <div class="description-container">
                    <p>Description</p>
                    <p>This is a desc for the ticket</p>
                </div>
            </div>
        </div>
        <div class="ticket-card" data-pending>
            <div class="small-heading">
                <p class="ticket-type">Lodging</p>
                <p class="ticket-status pending">Pending</p>
            </div>
            <div class="ticket-information">
                <div class="ticket-heading">
                    <p>ID:</p>
                    <p>Amount:</p>
                    <p>Name:</p>
                    <p>Submitted At:</p>
                </div>
                <div class="ticket-details">
                    <p>296</p>
                    <p>$1,538</p>
                    <p>Troy Fiawoo</p>
                    <p>10/20/20 @2:00pm</p>
                </div>
                <div class="description-container">
                    <p>Description</p>
                    <p>This is the description</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>