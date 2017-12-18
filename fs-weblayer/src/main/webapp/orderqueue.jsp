<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hu.foodservice.ejb.orderfacade.CustomerOrderStub" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<% List<CustomerOrderStub> custOrders = (List<CustomerOrderStub>) request.getAttribute("custOrders"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: Customer order queue ::</title>
</head>
<body>
	<div class="header">
		<a href="EmployeeMainPanel.html">
			<img src="/fs-weblayer/img/coollogo_com-153401633.png" class="header"/>
		</a>
	</div>
	<h1><% out.print("Customer Orders"); %></h1>
	<div><% 
		Iterator<CustomerOrderStub> it = custOrders.iterator(); 
		while(it.hasNext()) {
			CustomerOrderStub custOrder = it.next();
			out.println("<div><a class=\"entry\" href=\"http://localhost:8080/fs-weblayer/Order?orderCode=" + custOrder.getOrderCode() + "\">" + custOrder.getOrderCode() + "</a> --- " + custOrder.getDeadLine() + "</div>");
		}
	%></div>
</body>
</html>