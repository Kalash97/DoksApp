<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<meta charset="UTF-8">
<title>Site</title>
</head>

<body>

	<div class="header">
		<div class="alignLeft siteHeaderWrapper">
			<p class="siteHeader">Site</p>
		</div>
		<div class="alignRight">
			<p class="siteHeader">
				${username} <a href="traffic?action=Redirect&target=Login.jsp">Logout</a>
			</p>
		</div>
	</div>

	<div class="containerSite">
		<div class="toolbar">
			Home <br> Projects <br> Documents <br> Admin Screen <br>
			<a href="traffic?action=Redirect&target=index.html">Index</a>
		</div>

		<div class="content">
			Temporary content
			<table border="1">
				<thead>
					<tr>
						<th>id</th>
						<th>name</th>
						<th>description</th>
						<th>delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${projects555}" var="p44">
						<tr>
							<td>${p44.id}</td>
							<td>${p44.name}</td>
							<td>${p44.description}</td>
							<td><a href="<c:url value = "traffic?action=DeleteProject&id=${p44.id}"/>">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<table border="1">
				<thead>
					<tr>
						<th>id</th>
						<th>name</th>
						<th>content</th>
						<th>delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${document444}" var="d66">
						<tr>
							<td>${d66.id}</td>
							<td>${d66.name}</td>
							<td>${d66.content}</td>
							<td><a href="<c:url value = "traffic?action=DeleteDocument&id=${d66.id}"/>">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</body>
</html>