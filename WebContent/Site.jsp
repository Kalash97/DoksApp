<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.12.0/css/all.css">
<meta charset="UTF-8">
<title>Site</title>
</head>

<body>

	<div class="header">
		<div class="alignLeft siteHeaderWrapper">
			<p class="siteHeader"><i><b>Site</b></i></p>
		</div>
		<div class="alignRight">
			<p class="siteHeader">
				Hello:<font color="blue"><c:out value="${username}"/></font> <a
					href="traffic?action=Logout" class="buttonBig"><i class="fas fa-sign-out-alt"></i>Logout</a>
			</p>
		</div>
	</div>

	<div class="containerSite">
		<div class="toolbar">
			<div class="buttons">
				<i class="fas fa-long-arrow-alt-right"></i><a href="traffic?action=Redirect&target=Site.jsp" class="buttonBig">Home</a> <br><br>
				<i class="fas fa-long-arrow-alt-right"></i><a href="traffic?action=FindProjectsOfUser" class="buttonBig">Projects </a> <br><br>
				<i class="fas fa-long-arrow-alt-right"></i><a href="traffic?action=FindDocumentsOfUser" class="buttonBig">Documents </a> <br><br>
				<i class="fas fa-long-arrow-alt-right"></i><a href="traffic?action=EnableAdmin" class="buttonBig">Admin Screen </a> <br><br>
				<a href="traffic?action=Redirect&target=index.html">Index</a>
			</div>
		</div>

		<div class="content">
			Temporary content <br> <a href="traffic?action=CreateProject">Create Project</a> <br> 
			<a href="traffic?action=CreateDocument">Create Document</a>
			
			<form action="traffic?action=UpdateProject" method="post">
				Project ID
				<input type="text" name="id"/>
				name
				<input type="text" name="projectName"/>
				description
				<input type="text" name="projectDesc"/>	
				<input type="submit" value="Update project"/>
			</form>
			
			<form action="traffic?action=UpdateDocument" method="post">
				Document ID
				<input type="text" name="id"/>
				name
				<input type="text" name="projectName"/>
				description
				<input type="text" name="projectDesc"/>	
				<input type="submit" value="Update document"/>
			</form>
			
			<c:if test="${fn:length(projects444) gt 0}">
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
					<c:forEach items="${projects444}" var="p44">
						<tr>
							<td>${p44.id}</td>
							<td>${p44.name}</td>
							<td>${p44.description}</td>
							<td><a href="<c:url value = "traffic?action=DeleteProject&id=${p44.id}"/>"><i class="fas fa-trash-alt"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:if>
			
			<!--  <table border="1">
				<thead>
					<tr>
						<th>id</th>
						<th>name</th>
						<th>description</th>
						<th>delete</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${projects444}" var="p44">
						<tr>
							<td>${p44.id}</td>
							<td>${p44.name}</td>
							<td>${p44.description}</td>
							<td><a href="<c:url value = "traffic?action=DeleteProject&id=${p44.id}"/>"><i class="fas fa-trash-alt"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>-->
			<br /> <br />

			<c:if test="${fn:length(document444) gt 0}">
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
								<td><a
									href="<c:url value = "traffic?action=DeleteDocument&id=${d66.id}"/>"><i class="fas fa-trash-alt"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			
			<c:if test="${admin}">
				<h1>admin</h1>
			</c:if>
			
		</div>
	</div>
</body>
</html>