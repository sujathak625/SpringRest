<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.sk.MSModel.Employee,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<p>Welcome to home</p>

	<%
		String homeValue = "home";

		if (("home").equals(homeValue)) {
	%>
	<table>
		<tr>
			<td><a href="?value=add">Click here to add an Employee</a></td>
			<td><a href="?value=edit">Click here to edit an Employee</a></td>
			<td><a href="?value=view">Click here to view an Employee</a></td>
			<td><a href="/retrieveAll">Click here to view All
					Employees</a></td>
		</tr>
	</table>
	<%
		} else {
	%>
	Something wrong with page. Fix it.
	<%
		}

		String value = "do";
		value = request.getParameter("value");
		if ("add".equals(value)) {
			session.setAttribute("Action", "add");
	%>
	<form action="save" method="post">
		<table>
			<tr>
				<td><label for="id">Employee id</label></td>
				<td><input type="text" name="id"></input></td>
			</tr>
			<tr>
				<td><label for="EmpName">Employee Name</label></td>
				<td><input type="text" name="empname"></input></td>
			</tr>
			<tr>
				<td><label for="supervisor">Supervisor</label></td>
				<td><input type="text" name="superid"></input></td>
			</tr>
			<tr>
				<td><label for="CTC">CTC</label></td>
				<td><input type="text" name="ctc"></input></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></input></td>
			</tr>
		</table>
	</form>

	<%
		} else if ("edit".equals(value) || ("view").equals(value)) {
			if ("edit".equals(value)) {
				session.setAttribute("Action", "edit");
			} else if ("view".equals(value)) {
				session.setAttribute("Action", "view");
			}
	%>
	<form action="retrieve" method="get">
		<table>
			<tr>
				<td><label for="id">Enter Employee Id</label></td>
				<td><input type="text" name="id"></input></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></input></td>
			</tr>
		</table>
	</form>
	<%
		} else {
			String result = "No results available";
			result = (String) request.getAttribute("Employee");
	%><%
		}
	%>

</body>
</html>