<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<meta charset="UTF-8">
<title>Login</title>
</head>

<body>

	<div class="containerLogin">
		<div class="siteName">
			<font size=4>Login Site</font>
		</div>	

	<form action="traffic?action=Login" method="post">
		<br>
		login 
		<br>
		<input type="text" name="Login"/>
		<br>
		password 
		<br>
		<input type="text" name="Password"/>
		<br>
		<input type="submit" value="Login"/>
		<div class="alignRight"><a href="traffic?action=Redirect&target=Register.jsp">Register</a></div>
	</form>
	</div>
	
	<a href="traffic?action=Redirect&target=index.html">Index</a>

</body>
</html>