<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hu.foodservice.ejb.menufacade.MenuStub" %>
<%@ page import="java.util.Iterator" %>
<% MenuStub menu = (MenuStub) request.getAttribute("menu"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: Menu information ::</title>
</head>
<body>
	<div class="header">
		<a href="EmployeeMainPanel.html">
			<img src="/fs-weblayer/img/coollogo_com-153401633.png" class="header"/>
		</a>
	</div>
	<h1><% out.print(menu.getMenuName()); %></h1>
	<div>Price: <a><% out.print(menu.getPrice()); %></a></div>
	<div>Included meals: 
	<%  Iterator<String> it = menu.getIncludedMeals().iterator();
		while(it.hasNext()) {
			String meal = it.next();
			out.print("<a class=\"entry\" href=\"http://localhost:8080/fs-weblayer/Meal?name=" + meal + "\">[" + meal + "]</a> ");
		}
	%></div>
</body>
</html>