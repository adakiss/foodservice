<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hu.foodservice.ejb.customerfacade.CustomerStub" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<% List<CustomerStub> custs = (List<CustomerStub>) request.getAttribute("custs"); %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
		function addCustomerClick() {
			document.getElementById("delCustomerForm").style.display="none";
			document.getElementById("newCustomerForm").style.display="block";
		}
		
		function delCustomerClick() {
			document.getElementById("newCustomerForm").style.display="none";
			document.getElementById("delCustomerForm").style.display="block";
		}
	</script>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: All customers ::</title>
</head>
<body>
	<h1><% out.print("Customers"); %></h1>
	<div><% 
		Iterator<CustomerStub> it = custs.iterator(); 
		while(it.hasNext()) {
			CustomerStub cust = it.next();
			out.println("<div><a class=\"entry\" href=\"http://localhost:8080/fs-weblayer/Customer?name=" + cust.getCustName() + "\">" + cust.getCustName() + "</a></div>");
		}
	%></div>
	<div>
		<button class="button" onClick="addCustomerClick()">Add new</button>
		<button class="button" onClick="delCustomerClick()">Remove</button>
	</div>
	<div>
		<div id="newCustomerForm" style="display:none">
			<form action="CustomerList" method="POST">
				Customer name: <input type = "text" name = "customer_name" required/> <br/>
				Address: <input type = "text" name = "customer_address" required/> <br/>
				Phone: <input type = "number" name = "customer_phone" required/> <br/>
				Email: <input type = "text" name = "customer_email" required/> <br/>
				<input type = "submit" class="button" name = "add" value="Add" /> <br/>
			</form>
		</div>
		<div id="delCustomerForm" style="display:none">
			<form action="CustomerList" method="POST">
				Email: <input type = "text" name = "customer_email" required/> <br/>
				<input type = "submit" class="button" name = "remove" value="Remove" /> <br/>
			</form>
		</div>
	</div>
</body>
</html>