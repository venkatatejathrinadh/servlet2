<%-- These are imports of Java --%>
<%@page import="dto.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
div {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}
</style>
<meta charset="ISO-8859-1">
<title>Todo Home</title>
</head>
<body>
	<%-- To Receive the tasks of user --%>
	<%
	List<Task> list = (List<Task>) request.getAttribute("list");
	%>
	<div>
		<h1>Todo Home</h1>
		<table border="1px solid black">
			<tr>
				<th>Task Name</th>
				<th>Task Description</th>
				<th>Date Created</th>
				<th>Status</th>
				<th>Delete</th>
				<th>Edit</th>
			</tr>
			<%-- Making sure list is present and iterating over it --%>
			<%
			if (list != null) {
				for (Task task : list) {
			%>
			<%--we can not use one tag inside another so opened for loop  --%>
			<tr>
				<th><%=task.getName()%></th>
				<th><%=task.getDescription()%></th>
				<th><%=task.getCreatedTime()%></th>
				<th><%=task.isStatus()%></th>
				<th><button>Delete</button></th>
				<th><button>Edit</button></th>
			</tr>
			<%-- Closing for loop and if here --%>
			<%
			}
			}
			%>
		</table>
		<br> <a href="session-add-task"><button>Add Task</button></a><br>
		<a href="logout"><button>Logout</button></a>
	</div>
</body>
</html>