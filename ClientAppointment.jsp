<%@ page import="com.realtor.model.Users" %>
<%@ page import="com.realtor.model.Client" %>
<%@ page import="com.realtor.model.ClientAppointment" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/styles.css" />
</head>
<body>
	<div id="container">
		<header>
			<h1 id="banner" style="text-align:center">Debbie Zepeda</h1> 
			<h4 id="minibanner" style="text-align:center">Helping Houstonians turn houses into homes</h4>
		</header> 
		
		<div id="main">
			<div id="c1">
			<h2 style="text-align:center"> Request Appointment </h2>
			<br>
			<% Client clInfo= (Client) request.getAttribute("client_info"); %>
     		<% Users uId = (Users) request.getAttribute("user_info");%>
				<form method="POST" action="AddAppointment">
     			
     				<label for="listing_id">Listing ID:</label>
					<input type="text" name="listing_id"/><br/><br/>
					
					<label for="appt_date"> Appointment Date:</label>
					<input type="date" name="appt_date"><br><br>
				
					<label for=appt_time>Appointment Time:</label>
					<input type="time" name="appt_time"><br><br>
					
					<label for="appt_time">Message:</label>
					<textarea name="appt_message"></textarea><br/><br/>
				
				<br> 
				<button type="submit">Request Appointment</button>
				<button><a href="/Debbie_Realtor/MainClient" onclick="location.href=this.href+'?id='+<%=uId.getUser_id()%>;return false;">Cancel</a></button>
			</form>
				
			</div>
			
			<div id="sidebar">
				<br>
				<h2 style="text-align:justify"> Client Options</h2>
				<br>
				<div class="vertical-menu">
					<ul>
						<li><a href="/Debbie_Realtor/UpdateClient" onclick="location.href=this.href+'?id='+<%=clInfo.getClient_id()%>;return false;">Update Information</a></li>
						<li><a href="/Debbie_Realtor/ClientListing" onclick="location.href=this.href+'?id='+<%=clInfo.getClient_id()%>;return false;">See Listings</a></li>
						<li><a href="/Debbie_Realtor/AddAppointment" onclick="location.href=this.href+'?id='+<%=clInfo.getClient_id()%>;return false;">Request Appointment</a></li>
						<li><a href="/Debbie_Realtor/LogoutUser">Logout</a></li>
					</ul>
				</div>
			
				
				
			</div>
			
		</div>
		
		<footer>
		<h6 id="f1" style="text-align:center"> UH Critical Solutions </h6>
		<h6 id="f2" style="text-align:center">Powered by CIS 3343</h6> 
		</footer>
		
		
	</div>
</body>
</html>