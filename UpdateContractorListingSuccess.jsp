<%@page import="com.realtor.controller.SelectAppointment"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="com.realtor.service.AppointmentService"%>
<%@ page import="com.realtor.model.Service" %>
<%@ page import="com.realtor.model.Contractor" %>
<%@ page import="com.realtor.model.ContractorListing" %>
<%@ page import="com.realtor.model.Listing" %>
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
			<% Contractor ctInfo= (Contractor) request.getAttribute("contractor_info"); %>
			<h2 style="text-align:center"> Contractor Listing Updated Successfully  </h2>
			<br>
			</div>
			
			<div id="sidebar">
				<br>
				<h2 style="text-align:justify"> Contractor Options</h2>
				<br>
				<div class="vertical-menu">
					<ul>
						<li><a href="/Debbie_Realtor/UpdateContractor" onclick="location.href=this.href+'?id='+<%=ctInfo.getContractor_id()%>;return false;">Update Information</a></li>
						<li><a href="/Debbie_Realtor/ListingBillContractor" onclick="location.href=this.href+'?id='+<%=ctInfo.getContractor_id()%>;return false;">Submit Bill</a></li>
						<li><a href="/Debbie_Realtor/ListingContractor" onclick="location.href=this.href+'?id='+<%=ctInfo.getContractor_id()%>;return false;">See Listing</a></li>
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