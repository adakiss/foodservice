<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hu.foodservice.ejb.mealfacade.MealStub" %>
<% MealStub meal = (MealStub) request.getAttribute("meal"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: Meal information ::</title>
</head>
<body>
	<div class="header">
		<a href="EmployeeMainPanel.html">
			<img src="/fs-weblayer/img/coollogo_com-153401633.png" class="header"/>
		</a>
	</div>
	<h1><% out.print(meal.getMealName()); %></h1>
	<div>Description: <% out.print(meal.getMealDescription()); %></div>
	<div>Price: <% out.print(meal.getPrice()); %></div>
	<div>Contains allergenes: <% out.print(meal.getIsAllergic() ? "Yes" : "No" ); %></div>
</body>
</html>