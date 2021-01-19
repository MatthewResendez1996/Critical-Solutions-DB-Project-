<%@page import="com.realtor.controller.SelectAppointment"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="com.realtor.service.AppointmentService"%>
<%@ page import="com.realtor.model.Users" %>
<%@ page import="com.realtor.model.Realtor" %>
<%@ page import="com.realtor.model.Client" %>
<%@ page import="com.realtor.model.Listing" %>
<%@ page import="com.realtor.model.Contractor" %>
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
			<% Users usInfo = (Users) request.getAttribute("user_info"); %>
			<% Contractor ctInfo= (Contractor) request.getAttribute("contractor_info"); %>
			<h2 style="text-align:center"> Contractor </h2>
			<br>
			
			<table>
				<tr>
					<th>Contractor ID</th>
					<th>Company Name</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Action</th>
				</tr>
				<tr>
					<% 	
						String companyName = "";
						String firstName = "";
						String lastName = "";
						if(!ctInfo.getCompanyName().equals("NULL")){
							companyName = ctInfo.getCompanyName();						
						}else{
							companyName = "";
						}
						
						if(!ctInfo.getFirstName().equals("NULL")){
							firstName = ctInfo.getFirstName();
						}else{
							firstName = "";
						}
						
						if(!ctInfo.getLastName().equals("NULL")){
							lastName = ctInfo.getLastName();
						}else{
							lastName = "";
						}
					%>
					<td><%= ctInfo.getContractor_id() %></td>
					<td><%= companyName %></td>
					<td><%= firstName%></td>
					<td><%= lastName%></td>
					<td><a href="/Debbie_Realtor/DeleteContractor" onclick="location.href=this.href+'?id='+<%=ctInfo.getContractor_id()%>+'&uid='+<%=usInfo.getUser_id()%>;return false;">Delete</a></td>
				</tr>
			</table></div>
			
			<div id="sidebar">
				<br>
				<h2 style="text-align:justify"> Administrator Options</h2>
				<br>
				<div class="vertical-menu">
					<ul>
						<li><a href="/Debbie_Realtor/AddRealtorByAdmin" onclick="location.href=this.href+'?id='+<%=usInfo.getUser_id()%>;return false;">Add New Realtor</a></li>
						<li><a href="/Debbie_Realtor/AddClientByAdmin" onclick="location.href=this.href+'?id='+<%=usInfo.getUser_id()%>;return false;">Add New Client</a></li>
						<li><a href="/Debbie_Realtor/AddContractorByAdmin" onclick="location.href=this.href+'?id='+<%=usInfo.getUser_id()%>;return false;">Add New Contractor</a></li>
						<li><a href="/Debbie_Realtor/SearchRealtor" onclick="location.href=this.href+'?id='+<%=usInfo.getUser_id()%>;return false;">Delete Realtor</a></li>
						<li><a href="/Debbie_Realtor/SearchClient" onclick="location.href=this.href+'?id='+<%=usInfo.getUser_id()%>;return false;">Delete Client</a></li>
						<li><a href="/Debbie_Realtor/SearchContractor" onclick="location.href=this.href+'?id='+<%=usInfo.getUser_id()%>;return false;">Delete Contractor</a></li>
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