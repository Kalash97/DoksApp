<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="style.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.12.0/css/all.css">
<meta charset="UTF-8">
<title>Register</title>
</head>

<body>

<div class="containerRegister">

	<div class="siteName">
		<font size=4>Register Site</font>
	</div>
	
	<form action="traffic?action=Register" method="post">
		<br>
		<font color="blue">login</font> 
		<br>
		<input type="text" name="Login" /> 
		<br>
		<font color="blue">password </font>
		<br>
		<input type="text" name="Password" /> 
		<br>
		<font color="blue">name </font>
		<br>
		<input type="text" name="Name" />
		<br>
		<font color="blue">lastName </font>
		<br>
		<input type="text" name="LastName" /> 
		<br> 
		<input type="submit" value="Register" />
		<div class="alignRight"><a href="traffic?action=Redirect&target=Login.jsp">Back</a></div>
	</form>
	</div>
	
	<a href="traffic?action=Redirect&target=index.html">Index</a>

</body>
</html>