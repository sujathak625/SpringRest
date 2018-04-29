<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.sk.MSModel.Employee,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>

</head>
<body>
	<%
		double currencyRate = 0;
		String result = (String) request.getAttribute("Result");
		Employee emp = (Employee) request.getAttribute("EmployeeData");
		String pageAction = "No Action";
		pageAction = (String) request.getAttribute("PageAction");
		if (emp == null || pageAction == null || result == null) {
			pageAction = "No Action";
		}
	%>


	<%
		String action = null;
		String editAction = "edit";
		String viewAction = "view";
		action = (String) session.getAttribute("Action");
		if(action==null) {
			action = (String) request.getAttribute("Action");
		}
		if (action == null) {
			action = "noaction";
		}
		if ("retrieve".equals(pageAction) || "delete".equals(pageAction)) {
	%>

	

		<%
			if (editAction.equals(action)) {
		%>
		
		<form action="editdata" method="POST">
		<table>
			<tr>
				<td><label for="id">Employee Id</label></td>
				<td><input type="text" name="id" value="<%=emp.getId()%>" readonly></td>
			</tr>
			<tr>
				<td><label for="EmpName">Employee Name</label></td>
				<td><input type="text" name="empname"
					value="<%=emp.getEmpname()%>"></td>
			</tr>
			<tr>
				<td><label for="supervisor">Supervisor</label></td>
				<td><input type="text" name="superid"
					value="<%=emp.getSuperid()%>"></td>
			</tr>
			<tr>
				<td><label for="CTC">CTC</label></td>
				<td><input type="text" name="ctc" value="<%=emp.getCtc()%>"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></input></td>
			</tr>
		
		</table>
		</form>
		<%
			} else {
		%>
		<table>
		<tr>
			<td><label for="id">Employee Id</label></td>
			<td><%=emp.getId()%></td>
		</tr>
		<tr>
			<td><label for="EmpName">Employee Name</label></td>
			<td><%=emp.getEmpname()%></td>
		</tr>
		<tr>
			<td><label for="supervisor">Supervisor</label></td>
			<td><%=emp.getSuperid()%></td>
		</tr>
		<tr>
			<td><label for="CTC">CTC</label></td>
			<td><%=emp.getCtc()%></td>
		</tr>

		<%
			}
		%>

	</table>

	<br />
	<br />
	<%
		if (viewAction.equals(action)) {
				request.setAttribute("Action", "edit");
				request.setAttribute("PageAction", "retrieve");
	%>
	<form action="editdata" method="GET">
	<input type="hidden" name="id"></input>
		<input type="submit" value="Edit And Modify"/></input>
	</form>
	<%
		}
	%>

	<%
		} else if ("retrieveAll".equals(pageAction)) {

			List<Employee> empList = (ArrayList) request.getAttribute("EmployeeList");
			if (empList != null) {
				String baseCurrency = (String) request.getAttribute("baseCurrency");
				String currencyName = (String) request.getAttribute("currencyName");
	%>



	<form action="/currencyRate" id="currency">
		<input type="submit" value="Submit"> <select
			value="<%=request.getAttribute("currencyName")%>" name="currency"
			form="currency">
			<option value="USD">USD</option>
			<option value="EUR">EUR</option>
			<option value="INR">INR</option>
			<option value="OMR">OMR</option>
			<option value="GBP">GBP</option>
			<option value="AED">AED</option>
			<option value="CAD">CAD</option>
			<option value="CNY">CNY</option>
			<option value="JPY">JPY</option>
		</select>
	</form>

	<p>Select a currency to know the current rate against Euros</p>
	<%
		String firstLoad = (String) request.getAttribute("firstload");
				if ("false".equals(firstLoad)) {

					currencyRate = (double) request.getAttribute("currencyRate");
	%>
	<p><%=currencyRate%></p>

	<%
		}
	%>

	<table>

		<tr>
			<td col width="200">EMPLOYEE ID</td>
			<td col width="200">EMPLOYEE NAME</td>
			<td col width="200">SUPERVISOR ID</td>
			<td col width="200">CTC in <%=currencyName%></td>
		</tr>
		<%
			for (int c = 0; c < empList.size(); c++) {
						Employee emp1 = empList.get(c);
		%>
		<tr>
			<td col width="200"><%=emp1.getId()%></td>
			<td col width="200"><%=emp1.getEmpname()%></td>
			<td col width="200"><%=emp1.getSuperid()%></td>
			<%
				double ctc = emp1.getCtc();
							int ctcValueInCurrency = 0;
							if (currencyRate > 0.0) {
								ctcValueInCurrency = (int) Math.round(ctc * currencyRate);
							} else {
								ctcValueInCurrency = (int) Math.round(ctc);
							}
			%>
			<td col width="200"><%=ctcValueInCurrency%></td>
			<td col width="200"><a
				href="/delete?id=<%=emp1.getId()%>&currency=<%=request.getAttribute("currencyName")%>">Delete</a></td>
			<td col width="200"><a
				href="/retrieveId?id=<%=emp1.getId()%>">Edit</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}

		} else if ("save".equals(pageAction) || "update".equals(pageAction)) {
	%>
	<p><%=result%></p>
	<%
		} else {
	%><p>.</p>
	<%
		}
	%>
	<br />
	<a href="/test2">Click here to go to home page</a>
</body>
</html>