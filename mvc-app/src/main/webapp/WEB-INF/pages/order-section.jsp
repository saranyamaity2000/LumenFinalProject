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
	<label for="username">username</label>
	<input type="text" id="username" name="username"/>
	<input type="submit" value="submit"></input>
	</form>
</body>
</html>