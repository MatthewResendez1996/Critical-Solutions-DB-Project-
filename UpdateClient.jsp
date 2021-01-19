<%@ page import="com.realtor.model.Users" %>
<%@ page import="com.realtor.model.Client" %>
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
			<h2 style="text-align:center"> Client Information </h2>
			<br>
				<form method="POST" action="UpdateClient">
				
				<sql:setDataSource var="moodyg" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
     			url="jdbc:sqlserver://172.26.54.44:1433;databaseName=MoodyGarzaDB"
     			user="mgadmin"  password="MoodyGarza1234"/>
     			<% Client clInfo= (Client) request.getAttribute("client_info"); %>
     			<% Users uId = (Users) request.getAttribute("user_info");%>
     			
					<label for="firstname">First name:</label>
					<input type="text" name="first_name" value="<%=clInfo.getFirst_name() %>"><br><br>
				
					<label for="lastname">Last name:</label>
					<input type="text" name="last_name" value="<%=clInfo.getLast_name() %>"><br><br>
				
					<label for="gender">Gender:</label>
					<select name="gender" >
						<option value="Male">Male</option>
						<option value="Female">Female</option>
						<option value="Not_To_Say">Prefer not to say</option>
					</select><br><br>
					
					<sql:query dataSource="${moodyg}" var="result">
        			SELECT Description FROM [Client_Type]
       				</sql:query>
       				
       				<sql:query dataSource="${moodyg}" var="result_state">
        			SELECT Province_State_Name FROM [Province_State] WHERE Country_ID = 10 ORDER BY Province_State_Name
       				</sql:query>
       			
					<label for="clienttype">Client Type:</label>
					<select id="client_type" name="client_type" width="300"  style="width: 300px" >
            		<c:forEach var="row" items="${result.rows}">
            			<option><c:out value="${row.Description}"/></option>
            		</c:forEach>
               		</select><br><br>
               		<%
               			Calendar cal = Calendar.getInstance();
               			cal.setTime(clInfo.getDob());
               			int year = cal.get(Calendar.YEAR);
               			int month = cal.get(Calendar.MONTH) + 1;
               			String dobMonthRaw = String.valueOf(month);
               			String dobMonth = "";
               			if(dobMonthRaw.length() == 1){
               				dobMonth = "0" + dobMonthRaw;
               			}else{
               				dobMonth = dobMonthRaw;
               			}
               			int day = cal.get(Calendar.DAY_OF_MONTH);
               			String dobDayRaw = String.valueOf(day);
               			String dobDay = "";
               			if(dobDayRaw.length() == 1){
               				dobDay = "0" + dobDayRaw;
               			}else{
               				dobDay = dobDayRaw;
               			}
               		%>
				
					<label for="dob"> Date of Birth:</label>
					<input type="date" name="dob" value="<%= year + "-" + dobMonth + "-" + dobDay%>"><br><br>
				
					<label for="address">Address:</label>
					<input type="text" name="address" value="<%=clInfo.getHomeAddress() %>"><br><br>
				
					<label for="city">City:</label>
					<input type="text" name="city" value="<%=clInfo.getCity() %>"><br><br>
				
					<label for="state">State:</label>
					<select id="state_name" name="state_name" width="300"  style="width: 300px">
            		<c:forEach var="row1" items="${result_state.rows}">
            			<option><c:out value="${row1.Province_State_Name}"/></option>
            		</c:forEach>
               		</select><br><br>
				
					<label for="zip_code">Zip Code:</label>
					<input type="text" name="zip_code" value="<%=clInfo.getZipCode() %>"><br><br>		
				
					<label for="phonenumber">Phone Number:</label>
					<input type="text" name="phone_number" value="<%=clInfo.getPhoneNumber() %>"><br><br>
				
					<label for="email">Email:</label>
					<input type="text" name="email" value="<%=clInfo.getEmail() %>"><br><br>
				
					<label for="budget">Budget:</label>
					<input type="text" name="budget" value="<%=clInfo.getBudget() %>"><br><br>
				
				<br> 
				<button type="submit">Update Client</button>
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