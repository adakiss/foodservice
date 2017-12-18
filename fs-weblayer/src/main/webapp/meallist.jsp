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
	<script type="text/javascript">
		function addMealClick() {
			document.getElementById("delMealForm").style.display="none";
			document.getElementById("newMealForm").style.display="block";
		}
		
		function delMealClick() {
			document.getElementById("newMealForm").style.display="none";
			document.getElementById("delMealForm").style.display="block";
		}
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>:: List of available meals ::</title>
</head>
<body>
	<div class="header">
		<a href="EmployeeMainPanel.html">
			<img src="/fs-weblayer/img/coollogo_com-153401633.png" class="header"/>
		</a>
	</div>
	<h1><% out.print("Meals"); %></h1>
	<div>
		<% 
			Iterator<MealStub> it = meals.iterator(); 
			while(it.hasNext()) {
				MealStub meal = it.next();
				out.println("<div><a class=\"entry\" href=\"http://localhost:8080/fs-weblayer/Meal?name=" + meal.getMealName() + "\">" + meal.getMealName() + "</a></div>");
			}
		%>
		<button class="button" onClick="addMealClick()">Add new</button>
		<button class="button" onClick="delMealClick()">Remove</button>
	</div>
	<div>
		<div id="newMealForm" style="display:none">
			<form action="MealList" method="POST">
				Meal name: <input type = "text" name = "meal_name" required/> <br/>
				Description: <input type = "text" name = "meal_description" /> <br/>
				Price: <input type = "number" name = "meal_price" required/> <br/>
				Contains allergenes: <input type = "checkbox" name = "meal_isAllergic" /> <br/>
				<input type = "submit" class="button" name = "add" value="Add" /> <br/>
			</form>
		</div>
		<div id="delMealForm" style="display:none">
			<form action="MealList" method="POST">
				Meal name: <input type = "text" name = "meal_name" /> <br/>
				<input type = "submit" class="button" name = "remove" value="Remove" /> <br/>
			</form>
		</div>
	</div>
</body>
</html>