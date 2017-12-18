<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hu.foodservice.ejb.menufacade.MenuStub" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<% List<MenuStub> menus = (List<MenuStub>) request.getAttribute("menus"); %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function delMenuClick() {
		document.getElementById("delMenuForm").style.display="block";
	}
</script>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: List of available menus ::</title>
</head>
<body>
	<div class="header">
		<a href="EmployeeMainPanel.html">
			<img src="/fs-weblayer/img/coollogo_com-153401633.png" class="header"/>
		</a>
	</div>
	<h1><% out.print("Menus"); %></h1>
	<div>
		<% 
			Iterator<MenuStub> it = menus.iterator(); 
			while(it.hasNext()) {
				MenuStub menu = it.next();
				out.println("<div><a class=\"entry\" href=\"http://localhost:8080/fs-weblayer/Menu?name=" + menu.getMenuName() + "\">" + menu.getMenuName() + "</a></div>");
			}
		%>
		<div>
			<a class="button" id="menuCreator" href="http://localhost:8080/fs-weblayer/MenuCreator">+ Create new menu</a>
		</div>
		<button class="button" onClick="delMenuClick()">Delete menu</button>
		<div id="delMenuForm" style="display:none">
			<form action="MenuList" method="POST">
				Menu name: <input type = "text" name = "menu_name" /> <br/>
				<input type = "submit" class="button" name = "remove" value="Remove" /> <br/>
			</form>
		</div>
	</div>
</body>
</html>