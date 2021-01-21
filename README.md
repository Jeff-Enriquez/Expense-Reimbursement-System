# Expense Reimbursement System

## Project Description

The ERS allows employees to login and create an expensive reimbursement ticket. The employee can create a ticket by specifing the amount, request type, and description. Employees can view all of their own tickets and view them by approved or pending. Managers can log in and approve / deny pending tickets. Managers can also view all approved tikets.

## Technologies Used

* Java - SE 1.8
* JavaScript - ECMAScript 5
* JUnit - Jupiter 5.5.2
* HTML - 5
* CSS - 3
* Mockito - 3.6.28
* PostgreSQL - 12
* JDBC - 42.2.18
* Tomcat - 9.0.41
* Maven - 1.8
* Log4J - 1.2.17
* JSP - 2.3

## Features

\- Responsive Design (Mobile First): Looks great on mobile and desktop.

\- Login Validation: Fields are required on the client side and are validated on the server side.

\- Employee Home page will grab all of the employee tickets from the server. Employee can view 'All', 'Approved', or 'Pending' tickets. This change in view is done on the client side via JavaScript.

\- Employee can create Ticket. There is field validation on the client and server side.

\- Manager can view all pending and approved tickets.

\- Manager and Employee pages (Except for login) cannot be accessed unless logged in.

**To-do list:**
* Have a modal to confirm if a manager wants to approve / deny a ticket
* Delete web cache so a user cannot view the previous page after logging out
* Manager can search tickets by employee name

## Getting Started
   
   1) Clone the repo `git clone https://github.com/Jeff-Enriquez/Expense-Reimbursement-System.git`
   2) Launch Spring Tool Suite
   3) Import the project - select the folder that contains the repo
   
<img style='max-width: 650px' src='https://imgur.com/ev6VsYK.png' />

   4) Update maven (right click on the project) 
   
<img style='max-width: 650px' src='https://imgur.com/sxXwZBk.png' />

   5) Set up your own environment variables to connect to your database. Include: hostname, username, password, and port. 
      * [View ER Diagram](#Database-ER-Diagram)
   
<img style='max-width: 650px' src='https://imgur.com/3pIargg.png' />

   6) Run on a server (right click on the project) [more details for Tomcat](#Set-Up-With-Tomcat)
   
<img style='max-width: 650px' src='https://imgur.com/alF9vIa.png' />

## Usage

For best user experience (after starting the server) open up your browser and go to: `http://localhost:8080/ers` This url will direct you to the employee login page.

To login as a manager go to: `http://localhost:8080/ers/manager/login`

### **Employee**
Login: `http://localhost:8080/ers/employee/login`

<img style='max-width: 650px' src='https://imgur.com/RgfJi6X.png' />

Home: `http://localhost:8080/ers/employee`

<img style='max-width: 650px' src='https://imgur.com/VeEa6MP.png' />

Create Ticket: `http://localhost:8080/ers/employee/create-ticket`

<img style='max-width: 650px' src='https://imgur.com/6LolyW0.png' />

Logout `http://localhost:8080/ers/employee/logout`

### **Manager**
Login: `http://localhost:8080/ers/manager/login`

<img style='max-width: 650px' src='https://imgur.com/3FY53Yz.png' />

Home: `http://localhost:8080/ers/manager`

<img style='max-width: 650px' src='https://imgur.com/8ANjLq5.png' />

<img style='max-width: 650px' src='https://imgur.com/HNwT8RF.png' />



## Further Instructions for [Getting Started](#Getting-Started)

### Set Up With Tomcat
1) [Install Tomcat](http://tomcat.apache.org/)
2) Under the Apache folder, select your version of Tomcat and click 'next'

<img style='max-width: 650px' src='https://imgur.com/0tNNfs9.png' />

3) Click 'browse', select your apache-tomcat folder, then click 'finish'
   
<img style='max-width: 650px' src='https://imgur.com/zxkiH2c.png' />

### Database ER Diagram
<img style='max-width: 650px' src='https://imgur.com/axsd2c7.png' />