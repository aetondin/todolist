<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<c:url value="/resources" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath}/css/bootstrap-theme.min.css">
<script src="${cssPath}/js/jquery-3.3.1.min.js"></script>
<script src="${cssPath}/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container">

		<h1>My ToDo List</h1>
		
		<form:form action="${s:mvcUrl('enter').build()}" method="post" commandName="users">
			<div class="form-group">
				<label>User </label> <form:errors path="user" cssClass="bg-danger"/>	
				<form:input path="user" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<label>Password </label><form:errors path="password" cssClass="bg-danger"/>
				<form:input path="password" cssClass="form-control"/>
			</div>
			<button type="submit" class="btn btn-primary">Enter</button>
		</form:form>
		<br/>
		<br/>
		<h1>New User:</h1>
		<h2>${success}</h2>
		<form:form action="${s:mvcUrl('saveUser').build()}" method="post" commandName="users">
			<div class="form-group"> 
				<label>User</label>
				<input type="text" name="user" class="form-control"><br>
				<!-- form:input path="user" / -->
			</div>
			<div class="form-group">
				<label>Password</label>
				<input type="text" name="password" class="form-control"><br>
				<!-- form:input path="password" /-->
			</div>
			<button type="submit" class="btn btn-primary">New User</button>
		</form:form>	

	</div>
</body>
</html>