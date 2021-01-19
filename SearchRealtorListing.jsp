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
			<h2 style="text-align:center">Search Listing </h2>
			<br>
				<form method="POST" action="RealtorListing">
				
				<sql:setDataSource var="moodyg" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
     			url="jdbc:sqlserver://172.26.54.44:1433;databaseName=MoodyGarzaDB"
     			user="mgadmin"  password="MoodyGarza1234"/>
     			<% Realtor rlInfo= (Realtor)request.getAttribute("realtor_info"); %>
     			<% Users uId = (Users) request.getAttribute("user_info");%>
				
					<sql:query dataSource="${moodyg}" var="result_listtype">
        			SELECT Listing_Type_Name FROM [Listing_Type] ORDER BY Listing_Type_Name
       				</sql:query>
       				
       				<sql:query dataSource="${moodyg}" var="result_propertype">
        			SELECT Description FROM [Property_Type] ORDER BY Description
       				</sql:query>
       				
       				<sql:query dataSource="${moodyg}" var="result_county">
        			SELECT County_Name FROM [County] ORDER BY County_Name
       				</sql:query>
       				
       				<label for="listtype">Listing Type:</label>
					<select id="list_type" name="list_type" width="300"  style="width: 300px">
            		<c:forEach var="row" items="${result_listtype.rows}">
            			<option><c:out value="${row.Listing_Type_Name}"/></option>
            		</c:forEach>
               		</select><br><br>
               		
               		<label for="propertype">Property Type:</label>
					<select id="proper_type" name="proper_type" width="300"  style="width: 300px">
            		<c:forEach var="row" items="${result_propertype.rows}">
            			<option><c:out value="${row.Description}"/></option>
            		</c:forEach>
               		</select><br><br>
               		
               		<label for="county">County:</label>
					<select id="county" name="county" width="300"  style="width: 300px">
            		<c:forEach var="row" items="${result_county.rows}">
            			<option><c:out value="${row.County_Name}"/></option>
            		</c:forEach>
               		</select><br><br>
     			
     				<label for="budget">Budget:</label>
					<input type="budget" name="budget"/><br/><br/>
					
					<button type="submit">SearchListing</button>
					<button><a href="/Debbie_Realtor/MainRealtor" onclick="location.href=this.href+'?id='+<%=uId.getUser_id()%>;return false;">Cancel</a></button>
				</form>
				
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