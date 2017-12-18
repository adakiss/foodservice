<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hu.foodservice.ejb.mealfacade.MealStub" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<% List<MealStub> meals = (List<MealStub>) request.getAttribute("meals"); %>
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
	<h1>Create a menu</h1>
	<div>
		<form action="MenuCreator" method="POST">
			<% 
				Iterator<MealStub> it = meals.iterator(); 
				while(it.hasNext()) {
					MealStub meal = it.next();
					out.println(meal.getMealName() + ":<input type=\"checkbox\" name=\"meals\" value=\"" + meal.getMealName() + "\" /><br/>");
				}
			%>
			Menu name: <input type="text" name="menu_name" required/><br/>
			Menu price: <input type="number" name="menu_price" required/><br/>
			<input type="submit" class="button" value="Create" name="create"/><br/>
		</form>
	</div>
</body>
</html>