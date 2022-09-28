<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Section</title>
</head>
<body>

    <li>
    	<ul><a href="/product-section/products">check all orders</a></ul>
    	<ul>
    	<form action="/product-section/products-by-merchant" method="POST">
		<label for="merchant">Merchant Name</label>
		<input type="text" id="merchant" name="merchant"/>
		<label for="pageSize">Maximum Data in One Page</label>
		<input type="text" id="pageSize" name="pageSize"/>
		<input type="submit" value="submit"></input>
		</form>
		</ul>
		<ul>
		<a href="/product-section/products/available">check available products</a>
		</ul>
		<ul>
		<a href="/product-section/products/not-available">check out of stock products</a>
		</ul>
		<ul>
		<a href="/product-section/save">add/update new product</a>
		</ul>
    </li>
	
</body>
</html>