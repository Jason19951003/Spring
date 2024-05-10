<%@ page import="guestbook.model.GuestBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	GuestBook gb = (GuestBook)request.getAttribute("guestBook");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
		<title>GuestBook Update</title>
	</head>
	<body style="padding: 15px">
		<!-- 新增留言 -->
		<form class="pure-form" action="/JavaWeb/guestbook/update" method="post">
			<fieldset>
				<legend>GuestBook Update</legend>
				序號: <input type="text" id="id" name="id" value="<%=gb.getId()%>" readonly><p>
				姓名: <input type="text" id="userName" name="userName" value="<%=gb.getUserName()%>" placeholder="請輸入姓名" required><p>
				內容: <br><textarea rows="5" cols="10" id="content" name="content"><%=gb.getContent()%></textarea><p>	
				<button type="submit" class="pure-button pure-button-primary">修改留言</button>
			</fieldset>
		</form>
		<!-- 留言列表 -->
	</body>
</html>