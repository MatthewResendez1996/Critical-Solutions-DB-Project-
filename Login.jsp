
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
			<h3 id="title" style="text-align:center">User Sign in</h3> 
			<br> 
			<form method="POST" action="LoginUser">
			
			<sql:setDataSource var="moodyg" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
     			url="jdbc:sqlserver://172.26.54.44:1433;databaseName=MoodyGarzaDB"
     			user="mgadmin"  password="MoodyGarza1234"/>
			
				<label for="firstname">User ID:</label>
				<input type="text" name="user_id"><br><br>
				
				<label for="lastname">Password:</label>
				<input type="password" name="password"><br><br>
				
				<br>
				<sql:query dataSource="${moodyg}" var="result">
        			SELECT Description FROM [User_Type] ORDER BY Description
       			</sql:query>
				
				<label for="typeuser">Type of User:</label>
				<select id="user_type" name="user_type" width="300"  style="width: 300px">
            		<c:forEach var="row" items="${result.rows}">
            			<option><c:out value="${row.Description}"/></option>
            		</c:forEach>
               	</select><br/><br/>
				<br>
				<br>
				<button type="submit">Login</button>
			</form>
			  
			</div>
			
			<div id="sidebar">
				<img id="debbiepp" src="resources/images/debbie.jpg">
				<center><h5 id="tag">Member of Joann Garza and Barbara K. Moody Team</h5></center>
			</div>
			
		</div>
		
		<footer>
		<h6 id="f1" style="text-align:center"> UH Critical Solutions </h6>
		<h6 id="f2" style="text-align:center">Powered by CIS 3343</h6> 
		</footer>
		
		
	</div>
</body>
</html>