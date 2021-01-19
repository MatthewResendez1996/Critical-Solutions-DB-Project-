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
			<%List<Service> svInfo= (List<Service>) request.getAttribute("service_list"); %>
			<%List<Listing> lsInfo= (List<Listing>) request.getAttribute("listing_list"); %>
			<%List<ContractorListing> clInfo= (List<ContractorListing>) request.getAttribute("contract_list"); %>
			<h2 style="text-align:center"> Listing Assigned  </h2>
			<br>
			
			<table>
				<tr>
					<th>List/Contract ID</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
					<th>Zip Code</th>
					<th>Service Type</th>
					<th>Service Status</th>
					<th>Date</th>
					<th>Price</th>
					<th>Comment</th>
				</tr>
				
				<%			
				
			
				for(Integer i = 0; i < clInfo.size(); i++){
				
				
			
				%>
				<tr>
					<td align="center"><%= clInfo.get(i).getContractorListingId()%></td>
					<td align="center"><%=lsInfo.get(i).getListingAddress()%></td>
					<td align="center"><%= lsInfo.get(i).getCity() %></td>
					<td align="center"><%= lsInfo.get(i).getListingState()%></td>
					<td align="center"><%= lsInfo.get(i).getZipCode()%></td>
					<td align="center"><%= svInfo.get(i).getServiceType() %></td>
					<td align="center"><%= clInfo.get(i).getJobStatus() %></td>
					<td align="center"><%
               			Calendar cal = Calendar.getInstance();
               			cal.setTime(clInfo.get(i).getDate());
               			int year = cal.get(Calendar.YEAR);
               			int month = cal.get(Calendar.MONTH) + 1;
               			int day = cal.get(Calendar.DAY_OF_MONTH);
               		%>
               		<%=year + "-" + month + "-" + day %></td>
					<td align="center"><%= clInfo.get(i).getJobPrice() %></td>
					<td><%=clInfo.get(i).getComment() %></td>
				</tr>
				<%
				
				}
				%>
			</table>
				
				
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