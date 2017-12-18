<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hu.foodservice.ejb.customerfacade.CustomerStub" %>
<% CustomerStub cust = (CustomerStub) request.getAttribute("cust"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: Customer information ::</title>
</head>
<body>
	<div>
		<a href="EmployeeMainPanel.html">
			<img src="/fs-weblayer/img/coollogo_com-153401633.png" class="header"/>
		</a>
	</div>
	<h1><% out.print(cust.getCustName()); %></h1>
	<div>Address: <a><% out.print(cust.getCustAddress()); %></a></div>
	<div>Phone: <% out.print(cust.getCustTelNumber()); %></div>
	<div>Email: <% out.print(cust.getCustEmail()); %></div>
</body>
</html>