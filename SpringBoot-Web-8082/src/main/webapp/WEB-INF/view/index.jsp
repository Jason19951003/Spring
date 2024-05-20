<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSTL 標籤 -->    
<!-- Spring Form 表單標籤 -->
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Booking MeetingRoom</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
	</head>
	<body style="padding: 15px;">
		<table>
			<tr>
				<!-- 預約表單 -->
				<td valign="top">
					<sp:form modelAttribute="bookingMeetingRoom" method="post" action="/booking" class="pure-form" target="resultFrame">
						<fieldset>
							<legend>預約表單</legend>
							會議室:
								<sp:select path="roomId" items="${rooms }" itemValue="roomId" itemLabel="roomName"></sp:select><p>
							預約人:
								<sp:select path="roomId" items="${users }" itemValue="id" itemLabel="name"></sp:select><p>
							預約日:
								<sp:input type="date" path="bookingDate"/><p>
							<button type="submit" class="pure-button pure-button-primary">送出</button>
						</fieldset>
					</sp:form>
				</td>
				<!-- 回應結果 -->
				<td rowspan="4" valign="top">
					<iframe name="resultFrame" style="border: 0px;" width="1200px" height="1000px"></iframe>
				</td>
			</tr>
			<tr>
				<!-- 取消預約 -->
				<td valign="top"></td>
			</tr>
			<tr>
				<!-- 查詢預約 -->
				<td valign="top"></td>
			</tr>
			<tr>
				<!-- 新增會議室 -->
				<td valign="top"></td>
			</tr>
		</table>
	</body>
</html>