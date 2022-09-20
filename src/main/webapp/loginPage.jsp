<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LoginPage</title>
</head>
<body>
	<form action="login" method="post" >
		<label for="email">email</label><input name="email">
			<br>
		<label for="password">password</label><input name="password">
			<br>
			
		<input type="submit" value="submit">
	</form>
		<a href="index.jsp"><input type="button" value="sign in" style="margin-top: 15px;"></a>
</body>
</html>