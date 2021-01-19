<%@ page import="com.realtor.model.Users" %>
<%@ page import="com.realtor.model.Client" %>
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
			<% Client clInfo= (Client)request.getAttribute("client_info"); %>
			<h2 style="text-align:center"> Listings </h2>
			<br>
			
			<table>
				<tr>
					<th>Listing ID</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
					<th>ZipCode</th>
					<th>Num. Rooms</th>
					<th>Num. Bathrooms</th>
					<th>Square Feet</th>
					<th>Year Built</th>
					<th>Listing Type</th>
					<th>Property Type</th>
					<th>Price</th>
				</tr>
				
				<%
				List<Listing> lList = (List<Listing>) request.getAttribute("listing_list");
				for(Integer i = 0; i < lList.size(); i++){
				%>
					
				<tr>
					<td><%= lList.get(i).getListingId() %></td>
					<td><%= lList.get(i).getListingAddress() %></td>
					<td><%= lList.get(i).getCity() %></td>
					<td><%= lList.get(i).getListingState() %></td>
					<td><%= lList.get(i).getZipCode() %></td>
					<td><%= lList.get(i).getNumRoom() %></td>
					<td><%= lList.get(i).getNumBath() %></td>
					<td><%= lList.get(i).getSqrFeet() %></td>
					<td>
					<%
               			Calendar cal = Calendar.getInstance();
               			cal.setTime(lList.get(i).getYearBuilt());
               			int year = cal.get(Calendar.YEAR);
               			int month = cal.get(Calendar.MONTH) + 1;
               			int day = cal.get(Calendar.DAY_OF_MONTH);
               		%>
               		<%=year + "-" + month + "-" + day %>
					</td>
					<td>
						<% 
							if(lList.get(i).getListingTypeId() == 511){
						%>
							For Rent
						
						<% 
							}else if(lList.get(i).getListingTypeId() == 512){
						%>
							For Sale
						<% 
							}else{
						%>
							To Buy
						<% 
							}
						%>
					</td>
					<td>
						<% 
							if(lList.get(i).getPropertyTypeId() == 143266){
						%>
							Condo
						
						<% 
							}else if(lList.get(i).getPropertyTypeId() == 143267){
						%>
							Duplex
						<% 
							}else if(lList.get(i).getPropertyTypeId() == 143268){
						%>
							Mobile Home
						<% 
							}else if(lList.get(i).getPropertyTypeId() == 143269){
						%>
							Loft
						<% 
							}else if(lList.get(i).getPropertyTypeId() == 145520){
						%>
							Apartment
						<% 
							}else if(lList.get(i).getPropertyTypeId() == 145521){
						%>
							House
						<% 
							}else if(lList.get(i).getPropertyTypeId() == 145522){
						%>
							House rent to own
						<% 
							}else if(lList.get(i).getPropertyTypeId() == 145523){
						%>
							Boat House
						<% 
							}else{
						%>
							Paired
						<% 
							}
						%>
					</td>
					<td><%= lList.get(i).getPrice() %></td>
				</tr>
				
				<%
				}
				%>
			</table>
				
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