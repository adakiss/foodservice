<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hu.foodservice.ejb.orderfacade.CustomerOrderStub" %>
<% CustomerOrderStub custOrder = (CustomerOrderStub) request.getAttribute("custOrder"); %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function updateClick() {
		document.getElementById("updateOrder").style.display="block";
	}
</script>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: Order information ::</title>
</head>
<body>
	<h1>Order code: <% out.print(custOrder.getOrderCode()); %></h1>
	<div>Buyer: <a class="entry" href="http://localhost:8080/fs-weblayer/Customer?name=<% out.print(custOrder.getBuyer().getCustName()); %>"><% out.print(custOrder.getBuyer().getCustName()); %></a></div>
	<div>Ordered menu: <a class="entry" href="http://localhost:8080/fs-weblayer/Menu?name=<% out.print(custOrder.getOrderedMenu().getMenuName()); %>"><% out.print(custOrder.getOrderedMenu().getMenuName()); %></a></div>
	<div>Due date: <% out.print(custOrder.getDeadLine().toString()); %></div>
	<div>Status: <% out.print(custOrder.getStatus()); %></div>
	
	<%
		if (!custOrder.getStatus().equals("COMPLETED")) {
			out.print("<button onClick=\"updateClick()\" class=\"button\">Update</button>");
		}	
	%>
	
	
	<div id="updateOrder" style="display:none">
		<form action="Order" method="POST">
			Deadline:<input type="date" name="deadline" value="<% out.print(custOrder.getDeadLine()); %>"/>
			<% 
				if(!custOrder.getStatus().equals("COMPLETED")) {
					out.print("Next phase:<input id=\"nextPhase\" type=\"checkbox\" name=\"next_phase\"/>");
				}
			%>
			<input type="hidden" name="orderCode" value="<% out.print(custOrder.getOrderCode()); %>" />
			
			<%
				if (!custOrder.getStatus().equals("COMPLETED")) {
					out.print("<input type=\"submit\" class=\"button\" value=\"Submit\" name=\"submit\" />");
				}	
			%>
		</form>
	</div>
</body>
</html>