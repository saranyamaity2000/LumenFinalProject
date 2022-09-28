<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Section</title>
</head>
<body>

	<a href="/order-section/orders">check all orders</a>
	
	<form action="/order-section/orders-by-user" method="POST">
	<label for="username">Username</label>
	<input type="text" id="username" name="username"/>
	<br>
	<label for="pageSize">Maximum Data in One Page</label>
	<input type="text" id="pageSize" name="pageSize"/>
	<br>
	<input type="submit" value="submit"></input>
	</form>
	
</body>
</html>