<%@ page import="room.model.po.BookingRoom"%>
<%@ page import="room.model.po.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BookingRoom bookingRoom = (BookingRoom)request.getAttribute("bookingroom");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
		<title>BookingRoom Update</title>
	</head>
	<body style="padding: 15px">
		<!-- 修改Room -->
		<form class="pure-form" action="/JavaWeb/bookingroom/update" method="post">
			<fieldset>
				<legend>Room Update</legend>
				BookingRoomId: <input type="text" id="bookingRoomId" name="bookingRoomId" value="<%=bookingRoom.getBookingId()%>" readonly><p>
				RoomId: <input type="text" id="roomId" name="roomId" value="<%=bookingRoom.getRoomId()%>" required><p>
				UserId: <input type="text" id="userId" name="userId" value="<%=bookingRoom.getUserId()%>" required><p>
				CheckinDate: <input type="date" id="checkinDate" name="checkinDate" value="<%=bookingRoom.getCheckinDate()%>" required><p>
				<button type="submit" class="pure-button pure-button-primary">修改BookingRoom</button>
			</fieldset>
		</form>
	</body>
</html>