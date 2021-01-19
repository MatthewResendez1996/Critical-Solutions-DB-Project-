<%@ page import="com.realtor.model.Users" %>
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
			<h2 style="text-align:center"> Contractor Information </h2>
			<br>
			<% Users usInfo= (Users) request.getAttribute("user_info"); %>
				<form method="POST" action="AddContractorByAdmin">
				
				<sql:setDataSource var="moodyg" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
     			url="jdbc:sqlserver://172.26.54.44:1433;databaseName=MoodyGarzaDB"
     			user="mgadmin"  password="MoodyGarza1234"/>
				
				<label for="companyname">Company Name:</label>
				<input type="text" name="company_name"><br><br>
				
				<label for="firstname">First name:</label>
				<input type="text" name="first_name"><br><br>
				
				<label for="lastname">Last name:</label>
				<input type="text" name="last_name"><br><br>
				
				<sql:query dataSource="${moodyg}" var="result_state">
        			SELECT Province_State_Name FROM [Province_State] WHERE Country_ID = 10
       			</sql:query>
				
				<label for="address">Address:</label>
				<input type="text" name="company_address"><br><br>
				
				<label for="city">City:</label>
				<input type="text" name="city"><br><br>
				
				<label for="state">State:</label>
				<select id="state_name" name="state_name" width="300"  style="width: 300px">
            	<c:forEach var="row1" items="${result_state.rows}">
            		<option><c:out value="${row1.Province_State_Name}"/></option>
            	</c:forEach>
               	</select><br><br>
               	
               	<label for="zip_code">Zip Code:</label>
				<input type="text" name="zip_code"><br><br>	
				
				<label for="phonenumber">Phone Number:</label>
				<input type="text" name="phone_number"><br><br>
				
				<label for="email">Email:</label>
				<input type="text" name="email"><br><br>
				
				<br> 
				
				<button type="submit">Add Contractor</button>
				<button><a href="/Debbie_Realtor/AdminPage" onclick="location.href=this.href+'?id='+<%=usInfo.getUser_id()%>;return false;">Cancel</a></button>
			</form>
		</div>
			
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