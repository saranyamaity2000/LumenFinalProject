<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Payment</title>
</head>
<body>
   
	<form:form method="post" modelAttribute="command" action="/product-section/save">
        <div>
            <label>Product Id</label>
            <form:input path="productId" id="productId" />
        </div>
        <div>
            <label>Product Name</label>
            <form:input path="productName" id="productName" />
        </div>
        <div>
            <label>Merchant Name</label>
            <form:input path="merchantName" id="merchantName" />
        </div>
        <div>
            <label>Inventory</label>
            <form:input path="inventory" id="inventory"/>
        </div>
        <div>
            <input type="submit" value="Add">
        </div>
    </form:form>
    <br>
    <p>${message}</p>
</body>
</html>