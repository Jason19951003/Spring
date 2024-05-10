<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//接收來自BMRServlet的資料
	List<Map<String, Object>> bmrList = (List<Map<String, Object>>) request.getAttribute("bmrList");
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
<title>BMR List</title>
</head>
<body style="padding: 15px">
	<h2>BMRList</h2>
	<table class="pure-table pure-table-bordered">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Age</th>
				<th>Sex</th>
				<th>Height</th>
				<th>Weight</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 0; i < bmrList.size(); i++) { %>
			<tr>
				<td><%=i%></td>
				<td><%=bmrList.get(i).get("name")%></td>
				<td><%=bmrList.get(i).get("age")%></td>
				<td><%=bmrList.get(i).get("sex")%></td>
				<td><%=bmrList.get(i).get("height")%></td>
				<td><%=bmrList.get(i).get("weight")%></td>
				<td><a class="button-error pure-button" href="/JavaWeb/servlet/bmr?deleteId=<%=i%>">Delete</a></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	<a href="/JavaWeb/BMR.html" class="button-success pure-button">回上一頁</a>
</body>
</html>