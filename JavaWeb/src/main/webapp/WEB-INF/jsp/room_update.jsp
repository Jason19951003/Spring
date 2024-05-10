<%@ page import="room.model.po.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Room room = (Room)request.getAttribute("room");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
		<title>Room Update</title>
	</head>
	<body style="padding: 15px">
		<!-- 修改Room -->
		<form class="pure-form" action="/JavaWeb/room/update" method="post">
			<fieldset>
				<legend>Room Update</legend>
				RoomId: <input type="text" id="roomId" name="roomId" value="<%=room.getRoomId()%>" readonly><p>
				RoomName: <input type="text" id="roomName" name="roomName" value="<%=room.getRoomName()%>" placeholder="請輸入房號" required><p>
				<button type="submit" class="pure-button pure-button-primary">修改Room</button>
			</fieldset>
		</form>
	</body>
</html>