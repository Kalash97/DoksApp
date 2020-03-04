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
			Temporary content <br>	<br>
				
			<br>
			<br>
			<form action="traffic?action=CreateProject" method="post">
				Create project<br>
				Project name
				<input type="text" name="projectName"/><br>
				Project description
				<input type="text" name="projectDesc"/><br>
				<input type="submit" value="Create project"/>
				<br>
				<br>
				<br>
			</form>
			
			<br>
			<br>
			<form action="traffic?action=CreateDocument" method="post">
				Create document<br>
				Document name
				<input type="text" name="projectName"/><br>
				Document content
				<input type="text" name="projectDesc"/><br>
				<input type="submit" value="Create document"/>
				<br>
				<br>
				<br>
			</form>
			
			<br>
			<br>
			<form action="traffic?action=FindProjectById" method="post">
				Find project by id<br>
				Project id
				<input type="text" name="id"/>
				<input type="submit" value="Find"/>
			</form>
			<c:out value="${Project111}"/>
			<br>
			<br>
			<form action="traffic?action=FindDocById" method="post">
				Find document by id<br>
				Document id
				<input type="text" name="id"/>
				<input type="submit" value="Find"/>
			</form>
			<c:out value="${Documnet123}"/>
			<br>
			<br>
			<br>
			
			<form action="traffic?action=UpdateProject" method="post">
				Project ID
				<input type="text" name="id"/>
				name
				<input type="text" name="projectName"/>
				description
				<input type="text" name="projectDesc"/>	
				<input type="submit" value="Update project"/>
			</form>
			<br>
			<br>
			<form action="traffic?action=UpdateDocument" method="post">
				Document ID
				<input type="text" name="id"/>
				name
				<input type="text" name="projectName"/>
				description
				<input type="text" name="projectDesc"/>	
				<input type="submit" value="Update document"/>
			</form>
			<br>
			<br>
			<form action="traffic?action=FindDocumentsOfProject" method="post">
				Find documnets of project<br>
				Project ID
				<input type="text" name="id"/>
				<input type="submit" value="Find"/>
			</form>
			<c:if test="${fn:length(documentsOfProject123) gt 0}">
				<table border="1">
					<thead>
						<tr>
							<th>id</th>
							<th>name</th>
							<th>content</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${documentsOfProject123}" var="d123">
							<tr>
								<td>${d123.id}</td>
								<td>${d123.name}</td>
								<td>${d123.content}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			
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
			
			<a href="traffic?action=FindAllProjects">Find all projects</a>
			<c:if test="${fn:length(projects555) gt 0}">
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
						<c:forEach items="${projects555}" var="d55">
							<tr>
								<td>${d55.id}</td>
								<td>${d55.name}</td>
								<td>${d55.description}</td>
								<td><a
									href="<c:url value = "traffic?action=DeleteProject&id=${d55.id}"/>"><i class="fas fa-trash-alt"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<br>
			<a href="traffic?action=FindAllDocs">Find all documents</a>
			<c:if test="${fn:length(document123) gt 0}">
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
						<c:forEach items="${document123}" var="d111">
							<tr>
								<td>${d111.id}</td>
								<td>${d111.name}</td>
								<td>${d111.content}</td>
								<td><a
									href="<c:url value = "traffic?action=DeleteDocument&id=${d111.id}"/>"><i class="fas fa-trash-alt"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			
			<!--<c:if test="${fn.length(projects555) gt 0}">
				<table border="1">
					<thead>
						<tr>
							<th>id</th>
							<th>name</th>
							<th>desc</th>
							<th>delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${projects555}" var="p55">
							<tr>
								<td>${p55.id}</td>
								<td>${p55.name}</td>
								<td>${p55.description}</td>
								<td><a href="<c:url value = "traffic?action=DeleteProject&id=${p55.id}"/>"><i class="fas fa-trash-alt"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>-->
			
			<c:if test="${admin}">
				<h1>admin</h1>
				<form action="traffic?action=AssignUserToProject" method="post">
					Assign user to project
					<br>
					User id
					<input type="text" name="id"/>
					<br>
					Project id
					<input type="text" name="Name"/>
					<br>
					<input type="submit" value="Assign"/>
				</form>
				<br>
				<br>
				<form action="traffic?action=AssignUserToDoc" method="post">
					Assign user to document
					<br>
					User id
					<input type="text" name="id"/>
					<br>
					Document id
					<input type="text" name="Name"/>
					<br>
					<input type="submit" value="Assign"/>
				</form>
				<form action="traffic?action=AssignDocumentToProject" method="post">
				Assign documnet to project
				<br>
				Project ID
				<input type="text" name="id"/>
				<br>
				Document ID
				<input type="text" name="Name"/>
				<br>
				<input type="submit" value="Assign"/>
				</form>
			</c:if>
			
			
			
		</div>
	</div>
</body>
</html>