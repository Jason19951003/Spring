<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	boolean state = (boolean)request.getAttribute("state");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
		<title>GuestBook Result</title>
	</head>
	<body style="padding: 15px">
		<form class="pure-form">
			<fieldset>
				<legend>GuetBook Result</legend>
				<%=state ? "新增成功" : "新增失敗" %><p>
				<a href="/JavaWeb/guestbook" class="pure-button pure-button-primary">Back</a>
			</fieldset>
		</form>
	</body>
</html>