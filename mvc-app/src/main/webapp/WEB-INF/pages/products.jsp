<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student List</title>
<style type="text/css">
	table,tr,th,td{
	border: 2px solid blue;
	margin-left: auto;
	margin-right: auto;
	border-collapse: collapse;
	}
	a{
	text-decoration: none;
	}
</style>
</head>
<body>
   <table>
   	<thead>
   		<tr>
   			<th>Product Id</th>
   			<th>Product Name</th>
   			<th>Merchant Name</th>
   			<th>Inventory</th>
   		</tr>
   	</thead>
   	<tbody>
   	    <c:forEach items="${products}" var="product">
   	    <tr>
   			<td>${product.productId}</td>
   			<td>${product.productName}</td>
   			<td>${product.merchantName}</td>
   			<td>${product.inventory}</td>
   		</tr>
   	    </c:forEach>
   	</tbody>
   </table>
   
   <br>
   
   <center>
   <c:if test="${hasPreviousPage}">
       <a href="${previousPagePath}">previous-page</a>
       <br>
   </c:if>
   <c:if test="${hasNextPage}">
       <a href="${nextPagePath}">next-page</a>
       <br>
   </c:if>
   <a href="/product-section">Back to Order Section</a>
   <br>
   <a href="/">Back to Main Section</a>
   </center>

</body>
</html>