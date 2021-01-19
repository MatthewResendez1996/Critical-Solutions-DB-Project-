<%@ page import="com.realtor.model.Users" %>
<%@ page import="com.realtor.model.Contractor" %>
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
			<h2 style="text-align:center"> Search Listing Assigned to Contractor </h2>
			<br>
			<% Contractor conInfo= (Contractor)request.getAttribute("contractor_info"); %>
			<% Users usInfo= (Users) request.getAttribute("user_info"); %>
				<form method="POST" action="ListingContractor">
     			
     			<sql:setDataSource var="moodyg" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
     			url="jdbc:sqlserver://172.26.54.44:1433;databaseName=MoodyGarzaDB"
     			user="mgadmin"  password="MoodyGarza1234"/>
					
					<label for="listingid">Listing Id:</label>
					<input type="text" name="listing_id"><br><br>
					
					<label for="job_status">Service Status:</label>
					<select name="job_status">
						<option value=""></option>
						<option value="Completed">Completed</option>
						<option value="Pending">Pending</option>
					</select><br><br><br><br>
					
					<sql:query dataSource="${moodyg}" var="serv_type">
        			SELECT Service_Type FROM [Service] ORDER BY Service_Type
       				</sql:query>
					
					<label for="state">Service Type:</label>
					<select id="service_type" name="service_type" width="300"  style="width: 300px">
            			<c:forEach var="row1" items="${serv_type.rows}">
            				<option><c:out value="${row1.Service_Type}"/></option>
            			</c:forEach>
               		</select><br><br>
					
					
				<br> 
				<button type="submit">Get Listing</button>
				<button><a href="/Debbie_Realtor/MainContractor" onclick="location.href=this.href+'?id='+<%=usInfo.getUser_id()%>;return false;">Cancel</a></button>
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