<%@ page import="com.realtor.model.Users" %>
<%@ page import="com.realtor.model.Realtor" %>
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
			<h2 style="text-align:center">Realtor Information Update Successfully</h2>
			<br>
			<% Realtor rlInfo= (Realtor)request.getAttribute("realtor_info"); %>
				
				
			</div>
			
			<div id="sidebar">
				<br>
				<h2 style="text-align:justify"> Realtor Options</h2>
				<br>
				<div class="vertical-menu">
					<ul>
						<li><a href="/Debbie_Realtor/UpdateRealtor" onclick="location.href=this.href+'?id='+<%=rlInfo.getRealtor_id()%>;return false;">Update Information</a></li>
						<li><a href="/Debbie_Realtor/RealtorListing" onclick="location.href=this.href+'?id='+<%=rlInfo.getRealtor_id()%>;return false;">See Listings</a></li>
						<li><a href="/Debbie_Realtor/SelectAppointment" onclick="location.href=this.href+'?id='+<%=rlInfo.getRealtor_id()%>;return false;">Select Appointment</a></li>
						<li><a href="/Debbie_Realtor/ListRealtorAppointment" onclick="location.href=this.href+'?id='+<%=rlInfo.getRealtor_id()%>;return false;">See My Appointments</a></li>
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