const ticketsContainer = document.querySelector(".tickets-container")
let pendingTitle = document.querySelector("#pending-title")
let pastTitle = document.querySelector("#past-title")
let tickets = null

tickets = null

getPendingTickets()

pendingTitle.addEventListener("click", () => {
    getPendingTickets()
    pendingTitle.className = "title selected"
    pastTitle.className = "title"
})

pastTitle.addEventListener("click", () => {
    getPastTickets()
    pastTitle.className = "title selected"
    pendingTitle.className = "title"
})

function getPendingTickets() {
    const HTTP = new XMLHttpRequest()
    HTTP.onreadystatechange = e => {
        if(HTTP.readyState == 4 && HTTP.status == 200){
            tickets = JSON.parse(HTTP.response)
            renderPendingTickets()
        }
    }
    HTTP.open("GET", "/manager/get-pending-tickets")
    HTTP.send()
}

function getPastTickets() {
    const HTTP = new XMLHttpRequest()
    HTTP.onreadystatechange = e => {
        if(HTTP.readyState == 4 && HTTP.status == 200){
            tickets = JSON.parse(HTTP.response)
            renderPastTickets()
        }
    }
    HTTP.open("GET", "/manager/get-past-tickets")
    HTTP.send()
}

function renderPendingTickets(){
    ticketsContainer.innerHTML = "";
    for(let ticket of tickets){
        let newDiv = document.createElement("div")
        newDiv.className = "ticket-card"
        newDiv.setAttribute(`data-${ticket.isApproved}`, "")
        newDiv.innerHTML = `
        	<div class="small-heading">
                <p class="ticket-type">${ticket.requestType}</p>
                <p class="username">Employee: ${ticket.employee}</p>
                <p class="ticket-status ${ticket.isApproved}">${ticket.isApproved}</p>
            </div>
            <div class="ticket-information-container">
                <div class="ticket">
                    <div>
                        <p class="ticket-heading">ID:</p>
                        <p class="ticket-details">${ticket.id}</p>
                    </div>
                    <div>
                        <p class="ticket-heading">Amount:</p>
                        <p class="ticket-details">$${ticket.amount}</p>
                    </div>
                    <div>
                        <p class="ticket-heading">Submitted At:</p>
                        <p class="ticket-details">${convertTime(ticket.timeSubmitted)}</p>
                    </div>
                </div>
                <div class="description-container">
                    <p class="ticket-heading">Description</p>
                    <p class="ticket-details">${ticket.description}</p>
                </div>
                <div class="buttons-container">
                    <button onclick="denyTicket(${ticket.id}, '${ticket.employee}')" class="deny">Deny</button>
                    <button onclick="approveTicket(${ticket.id}, '${ticket.employee}')" class="approve">Approve</button>
                </div>
            </div>
        `
        ticketsContainer.appendChild(newDiv)
    }
}
function renderPastTickets(){
    ticketsContainer.innerHTML = "";
    for(let ticket of tickets){
        let newDiv = document.createElement("div")
        newDiv.className = "ticket-card"
        newDiv.setAttribute(`data-${ticket.isApproved}`, "")
        newDiv.innerHTML = `
        	<div class="small-heading">
                <p class="ticket-type">${ticket.requestType}</p>
                <p class="username">Employee: ${ticket.employee}</p>
                <p class="ticket-status ${ticket.isApproved}">${ticket.isApproved}</p>
            </div>
            <div class="ticket-information-container">
                <div class="ticket">
                    <div>
                        <p class="ticket-heading">ID:</p>
                        <p class="ticket-details">${ticket.id}</p>
                    </div>
                    <div>
                        <p class="ticket-heading">Amount:</p>
                        <p class="ticket-details">$${ticket.amount}</p>
                    </div>
                    <div>
                        <p class="ticket-heading">Submitted At:</p>
                        <p class="ticket-details">${convertTime(ticket.timeSubmitted)}</p>
                    </div>
                </div>
                <div class="description-container">
                    <p class="ticket-heading">Description</p>
                    <p class="ticket-details">${ticket.description}</p>
                </div>
            </div>
        `
        ticketsContainer.appendChild(newDiv)
    }
}
function denyTicket(id, employee){
    const HTTP = new XMLHttpRequest()
    HTTP.onreadystatechange = e => {
        if(HTTP.readyState == XMLHttpRequest.DONE && HTTP.status == 200){
            getPendingTickets()
        }
    }
    HTTP.open("PUT", "/manager/deny-ticket")
    HTTP.send(`{ "id":${id}, "employee":"${employee}" }`)
}
function approveTicket(id, employee){
    const HTTP = new XMLHttpRequest()
    HTTP.onreadystatechange = e => {
        if(HTTP.readyState == XMLHttpRequest.DONE && HTTP.status == 200){
            getPendingTickets()
        }
    }
    HTTP.open("PUT", "/manager/approve-ticket")
    HTTP.send(`{ "id":${id}, "employee":"${employee}" }`)
}

function convertTime(timeStamp){
  let time = new Date(timeStamp)
  let times = time.toString().split(" ")
  let month = convertMonth(times[1])
  let day = times[2]
  let year = times[3].substring(2)
  let clock = formatClock(times[4].substring(0,5))
  return `${month}/${day}/${year} @${clock}`
}

function formatClock(clock){
	let postfix = "am"
	let [hour, min] = clock.split(":")
	if(hour == 0) {
		hour = 12
	} else if(hour >= 12) {
		postfix = "pm"
		if(hour > 12) {
			hour -= 12
		}
	}
	return `${hour}:${min}${postfix}`;
}

function convertMonth(month){
	switch(month){
	    case "Jan":
	      month = "01"
	      break
	    case "Feb":
	      month = "02"
	      break
	    case "Mar":
	      month = "03"
	      break
	    case "Apr":
	      month = "04"
	      break
	    case "May":
	      month = "05"
	      break
	    case "Jun":
	      month = "06"
	      break
	    case "Jul":
	      month = "07"
	      break
	    case "Aug":
	      month = "08"
	      break
	    case "Sep":
	      month = "09"
	      break
	    case "Oct":
	      month = "10"
	      break
	    case "Nov":
	      month = "11"
	      break
	    case "Dec":
	      month = "12"
	      break
	    default:
	      month = "--"
	  }
	return month;
}