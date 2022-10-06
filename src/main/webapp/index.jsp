<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<form action="registration" method="post">
		<label for="username">Name</label><input name="username">
			<br>
		<label for="lastName">last name</label><input name="lastName">
			<br>
		<label for="email">email</label><input name="email">
			<br>
		<label for="password">password</label><input name="password">
			<br>
			
		<input type="submit" value="submit">
		<h3><a href="loginPage.jsp">login</a></h3>
	</form>
</body>
</html>
