let viewAll = document.querySelector("#all")
let viewPast = document.querySelector("#past")
let viewPending = document.querySelector('#pending')
let pastTickets = document.querySelectorAll("[data-approved]")
let pendingTickets = document.querySelectorAll("[data-pending]")
let previousAction = 'all'

viewAll.onclick = function () { displayTickets('all') }
viewPast.onclick = function () { displayTickets('past') }
viewPending.onclick = function () { displayTickets('pending') }

function displayTickets(action){
    viewAll.className = "not-selected"
    viewPast.className = "not-selected"
    viewPending.className = "not-selected"
    if(action === 'all'){
        viewAll.className = "selected"
        if(previousAction === 'pending'){
            displayPast()
        } else if (previousAction === 'past') {
            displayPending()
        }
    } else if(action === 'pending'){
        viewPending.className = "selected"
        hidePast()
        if(previousAction === 'past'){
            displayPending();
        }
    } else if(action === 'past'){
        viewPast.className = "selected"
        hidePending()
        if(previousAction === 'pending'){
            displayPast()
        }
    }
    previousAction = action
}

function displayPast(){
    pastTickets.forEach(ticket => {
        ticket.setAttribute("style", "display: block;")
    })
}
function displayPending(){
    pendingTickets.forEach(ticket => {
        ticket.setAttribute("style", "display: block;")
    })
}
function hidePast(){
    pastTickets.forEach(ticket => {
        ticket.setAttribute("style", "display: none;")
    })
}
function hidePending(){
    pendingTickets.forEach(ticket => {
        ticket.setAttribute("style", "display: none;")
    })
}