<%@ page import="com.realtor.model.Users" %>
<%@ page import="com.realtor.model.Contractor" %>
<%@ page import="com.realtor.model.ContractorListing" %>
<%@ page import="com.realtor.model.Listing" %>
<%@ page import="com.realtor.model.Service" %>
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
			<h2 style="text-align:center"> Update Contractor Listing </h2>
			<br>
				<form method="POST" action="SubmitBillListing">
     			<% Contractor conInfo= (Contractor)request.getAttribute("contractor_info"); %>
     			<% Users uId = (Users) request.getAttribute("user_info");%>
     			<% Listing lsInfo= (Listing)request.getAttribute("listing_info"); %>
     			<% Service svInfo = (Service) request.getAttribute("service_info");%>
     			<% ContractorListing clInfo = (ContractorListing) request.getAttribute("contractlist_info");%>
     							
				<label for="contract_id">List/Contractor Id:</label> 
				<input type="text" name="contract_id" value="<%=clInfo.getContractorListingId()%>" disabled="disabled"><br><br>
				
				<!--<label for="listing_address">Listing Address:</label>
				<input type="text" name="listing_address" value="<%=lsInfo.getListingAddress() %>" disabled="disabled"><br><br>
				
				<label for="listing_city">Listing City:</label>
				<input type="text" name="listing_city" value="<%=lsInfo.getCity() %>" disabled="disabled"><br><br>
								
				<label for="state">State:</label>
				<input type="text" name="state" value="<%=lsInfo.getListingState() %>" disabled="disabled"><br><br>
				
				<label for="zip_code">Zip Code:</label>
				<input type="text" name="zip_code" value="<%=lsInfo.getZipCode() %>" disabled="disabled"><br><br>-->
				
				<label for="service_type">Service Type:</label>
				<input type="text" name="service_type" value="<%= svInfo.getServiceType() %>" disabled="disabled"><br><br>
				
				<% 
               			Calendar cal1 = Calendar.getInstance();
               			cal1.setTime(clInfo.getDate());
               			int yearS = cal1.get(Calendar.YEAR);
               			int monthS = cal1.get(Calendar.MONTH) + 1;
               			String dobMonthRawS = String.valueOf(monthS);
               			String dobMonthS = "";
               			if(dobMonthRawS.length() == 1){
               				dobMonthS = "0" + dobMonthRawS;
               			}else{
               				dobMonthS = dobMonthRawS;
               			}
               			int dayS = cal1.get(Calendar.DAY_OF_MONTH);
               			String dobDayRawS = String.valueOf(dayS);
               			String dobDayS = "";
               			if(dobDayRawS.length() == 1){
               				dobDayS = "0" + dobDayRawS;
               			}else{
               				dobDayS = dobDayRawS;
               			}
               		%>
				
				<label for="date">Date:</label>
				<input type="text" name="date" value="<%=yearS + "-" + dobMonthS + "-" + dobDayS %>" disabled="disabled"><br><br>
				
				<label for="price">Price:</label>
				<input type="text" name="price" value="<%=clInfo.getJobPrice() %>" disabled="disabled"><br><br>
				
				<label for="job_status">Service Status:</label>
					<select name="job_status">
						<option value=""></option>
						<option value="Completed">Completed</option>
						<option value="Pending">Pending</option>
				</select><br><br>
								
				<label for="serv_comment">Comment:</label>
				<textarea name="serv_comment" value="<%=clInfo.getComment()%>"></textarea><br/><br/>
				
				<br> 
				
				<button type="submit">Update Service Status</button>
				<button><a href="/Debbie_Realtor/MainContractor" onclick="location.href=this.href+'?id='+<%=uId.getUser_id()%>;return false;">Cancel</a></button>
			</form>
			
			

				
				
			</div>
			
			<div id="sidebar">
				<br>
				<h2 style="text-align:justify"> Contractor Options</h2>
				<br>
				<div class="vertical-menu">
					<ul>
						<li><a href="/Debbie_Realtor/UpdateContractor" onclick="location.href=this.href+'?id='+<%=conInfo.getContractor_id()%>;return false;">Update Information</a></li>
						<li><a href="/Debbie_Realtor/ListingBillContractor" onclick="location.href=this.href+'?id='+<%=conInfo.getContractor_id()%>;return false;">Submit Bill</a></li>
						<li><a href="/Debbie_Realtor/ListingContractor" onclick="location.href=this.href+'?id='+<%=conInfo.getContractor_id()%>;return false;">See Listing</a></li>
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