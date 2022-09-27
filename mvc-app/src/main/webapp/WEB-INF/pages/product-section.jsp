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
	<form:form method="post" modelAttribute="command" action="/payment">
        <div>
            <label>Payment Id</label>
            <form:input path="paymentId" id="paymentId" />
        </div>
        <div>
            <label>Customer Name</label>
            <form:input path="customerName" id="customerName" />
        </div>
        <div>
            <label>Amount</label>
            <form:input path="amount" id="amount" />
        </div>
        <div>
            <label>Payment Date</label>
            <form:input path="paymentDate" id="amountPaymentDate" type="date"/>
        </div>
        <div>
            <input type="submit" value="Add">
        </div>
    </form:form>
</body>
</html>