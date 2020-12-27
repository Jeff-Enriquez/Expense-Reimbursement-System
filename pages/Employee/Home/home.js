console.log('hit')
let viewAll = document.querySelector("#all")
let viewPast = document.querySelector("#past")
let viewPending = document.querySelector('#pending')
let pastTickets = document.querySelectorAll('.past')
let pendingTickets = document.querySelectorAll('.pending')
let previousAction = 'all'

viewAll.addEventListener('change', function() {
    if (this.checked) {
      displayTickets('all')
    }
})

viewPast.addEventListener('change', function() {
    if (this.checked) {
      displayTickets('past')
    }
})

viewPending.addEventListener('change', function() {
    if (this.checked) {
      displayTickets('pending')
    }
})

function displayTickets(action){
    if(action === 'all'){
        if(previousAction === 'pending'){
            displayPast()
        } else if (previousAction === 'past') {
            displayPending()
        }
    } else if(action === 'pending'){
        hidePast()
        if(previousAction === 'past'){
            displayPending();
        }
    } else if(action === 'past'){
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