<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.12.0/css/all.css">
<meta charset="UTF-8">
<title>Login</title>
</head>

<body>

	<div class="containerLogin">
		<div class="innerDiv">
			<div class="siteName">
				<font size=4>Login Site</font>
			</div>	
	
			<form action="traffic?action=Login" method="post">
				<br>
				<font color="blue">Login</font> 
				<br>
				<input type="text" name="Login"/>
				<br>
				<font color="blue">Password</font> 
				<br>
				<input type="text" name="Password"/>
				<br>
				<input type="submit" value="Login"/>
				<div class="alignRight"><a href="traffic?action=Redirect&target=Register.jsp">Register</a></div>
			</form>
		</div>
	</div>
	
	<a href="traffic?action=Redirect&target=index.html">Index</a>

</body>
</html>