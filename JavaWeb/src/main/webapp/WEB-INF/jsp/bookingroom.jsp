<%@ page import="room.model.dto.BookingRoomDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%
	List<BookingRoomDto> bookRoomDto = (List<BookingRoomDto>)request.getAttribute("bookingRoomDtos");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/buttons.css">
		<title>BookingRoom</title>
	</head>
	<body style="padding: 15px">
		<!-- 新增Room -->
		<form class="pure-form" action="/JavaWeb/bookingroom" method="post">
			<fieldset>
				<legend>BookingRoom Add</legend>				
				Room Name: 
				<select id="roomId" name="roomId">
					<c:forEach items="${rooms}" var="room">
						<option value="${room.roomId}">${room.roomName}</option>
					</c:forEach>
				</select><p>
				BookingDate: <input type="date" id="bookingDate" name="bookingDate" required><p>
				UserId: <input type="text" id="userId" name="userId" required><p>
				<button type="submit" class="pure-button pure-button-primary">BookingRoom</button>
			</fieldset>
		</form>
		<!-- Room列表 -->
		<table class="pure-table pure-table-bordered" style="text-align: center">
			<thead>
				<tr>
					<th>BookingId</th>
					<th>RoomId</th>
					<th>RoomName</th>
					<th>UserId</th>
					<th>CheckingDate</th>
					<th>CreateTime</th>
					<th>修改</th>
					<th>刪除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bookingRoomDtos}" var="bookingRoomDto">
					<tr>
						<td>${bookingRoomDto.bookingId}</td>
						<td>${bookingRoomDto.room.roomId}</td>
						<td>${bookingRoomDto.room.roomName}</td>
						<td>${bookingRoomDto.userId}</td>
						<td>${bookingRoomDto.checkinDate}</td>
						<td>${bookingRoomDto.createTime}</td>
						<td><a class="button-secondary pure-button" href="/JavaWeb/bookingroom/update?updateId=${bookingRoomDto.bookingId}">Update</a></td>
						<td><a class="button-error pure-button"	href="/JavaWeb/bookingroom?deleteId=${bookingRoomDto.bookingId}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table class="pure-table pure-table-bordered" style="text-align: center; margin-top: 10px">
			<thead>
				<tr>
					<th>RoomId</th>
					<th>RoomName</th>
					<th>BookingCount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ bookingRoomCounts }" var="bookingRoomCount">
					<tr>
						<td>${bookingRoomCount.roomId }</td>
						<td>${bookingRoomCount.roomName }</td>
						<td>${bookingRoomCount.bookingCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>