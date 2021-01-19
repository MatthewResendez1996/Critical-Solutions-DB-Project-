<%@page import="com.realtor.controller.SelectAppointment"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="com.realtor.service.AppointmentService"%>
<%@ page import="com.realtor.model.Users" %>
<%@ page import="com.realtor.model.Realtor" %>
<%@ page import="com.realtor.model.Client" %>
<%@ page import="com.realtor.model.Listing" %>
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
			<% Realtor rlInfo= (Realtor) request.getAttribute("realtor_info"); %>
			<h2 style="text-align:center"> Appointments </h2>
			<br>
			
			<table>
				<tr>
					<th>Appointment ID</th>
					<th>Date</th>
					<th>Time</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
					<th>ZipCode</th>
					<th>Client First Name</th>
					<th>Client Last Name</th>
					<th>Comment</th>
					<th>Select</th>
				</tr>
				
				<%
				List<ClientAppointment> caInfo= (List<ClientAppointment>) request.getAttribute("appt_info");
				List<Listing> lsInfo= (List<Listing>) request.getAttribute("listing_info");
				List<Client> clInfo= (List<Client>) request.getAttribute("client_info");
			
				for(Integer i = 0; i < caInfo.size(); i++){
				%>
				<tr>
					<td><%= caInfo.get(i).getAppointmentId() %></td>
					<td>
					<%
               			Calendar cal = Calendar.getInstance();
               			cal.setTime(caInfo.get(i).getApptDate());
               			int year = cal.get(Calendar.YEAR);
               			int month = cal.get(Calendar.MONTH) + 1;
               			int day = cal.get(Calendar.DAY_OF_MONTH);
               		%>
               		<%=year + "-" + month + "-" + day %>
					</td>
					<td><%= caInfo.get(i). getApptTime() %></td>
					<td><%= lsInfo.get(i).getListingAddress()%></td>
					<td><%= lsInfo.get(i).getCity() %></td>
					<td><%= lsInfo.get(i).getListingState() %></td>
					<td><%= lsInfo.get(i).getZipCode() %></td>
					<td><%= clInfo.get(i).getFirst_name() %></td>
					<td><%= clInfo.get(i).getLast_name() %></td>
					<td><%= caInfo.get(i).getApptMessage() %></td>
					<td><a href="/Debbie_Realtor/SelectAppointmentByRealtor" onclick="location.href=this.href+'?id='+<%=rlInfo.getRealtor_id()%>+'&aid='+<%= caInfo.get(i).getAppointmentId() %>;return false;">Select</a></td>
				</tr>
				<%
				
				}
				%>
			</table>
		</div>
			
			<div id="sidebar">
				<br>
				<h2 style="text-align:justify"> Realtor Options</h2>
				<br>
				<div class="vertical-menu">
					<ul>
						<li><a href="/Debbie_Realtor/UpdateRealtor" onclick="location.href=this.href+'?id='+<%=rlInfo.getRealtor_id() %>;return false;">Update Information</a></li>
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