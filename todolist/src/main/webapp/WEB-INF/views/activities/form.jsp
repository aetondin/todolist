<%@ page language="java" contentType="text/html; charset=UTF-8	"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<c:url value="/resources" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath}/css/bootstrap-theme.min.css">
<script src="${cssPath}/js/jquery-3.3.1.min.js"></script>
<script src="${cssPath}/js/bootstrap.min.js"></script>

<script type="text/javascript">

	function updateTaskOK(){
		alert("update task!");
	}
	
	function updateTask(id){
		$.get("/update/" + id, updateTaskOK )
	}
	
	function pagaAgora(id) {
		$.post("pagaConta", {'id' : id}, function() {
		  $("#conta_"+id).html("Paga");
		});
	}

</script>

</head>

	<div class="container">
		<form:form action="${s:mvcUrl('saveActivitie').build() }" method="post" commandName="activities" >
			<a href="${s:mvcUrl('userLogOff').build()}"> log-off</a>	
			<h1>** Welcome ${userName} **</h1>
			<form:hidden path="id" />
			<div class="form-group">
				<label>Date</label>
				<form:input path="date" cssClass="form-control"/><form:errors path="date" />
			</div>
			<div class="form-group">
				<label>Tasks</label>
				<form:input path="tasks" cssClass="form-control"/><form:errors path="tasks" />
			</div>
			<div class="form-group">
				<label>Priority</label>
				<form:select path="priority" items="${getPriority}" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<label>Status</label>
				<form:select path="status" items="${getStatus}" cssClass="form-control"/>
			</div>
			<button type="submit" class="btn btn-primary">Insert</button>
		</form:form>
		<br />
		<br />
		
		<h1>My ToDo List</h1> 
		<spam>${success}</spam>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Date</th>
				<th>Tasks</th>
				<th>Priority</th>
				<th>Status</th>
				<th>Last Update</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${listActivities}" var="activitie"> 
				<tr>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${activitie.date.time}" /></td>
					<td>${activitie.tasks}</td>
					<td>${activitie.priority}</td>
					<td>${activitie.status}</td>
					<td>${activitie.updated}</td>
					<td><a href="${s:mvcUrl('updateActivitie').arg(0, activitie.id).build()}">Update</a></td>
					<%-- <td><a href="#" onclick="updateTask(${activitie.id});" >Update</a></td> --%>
					<td><a href="${s:mvcUrl('deleteActivitie').arg(0, activitie.id).build()}">Delete</a></td>
				</tr>	
			</c:forEach>
			
		</table>
	</div>
</body>
</html>