<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<!-- Tomcat 10.x JSTL -->    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!-- Spring Form 表單標籤 -->
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<!--
	+---------+-------------+
	| 預約表單   |
	| xxxxx   |
	+---------+    回
	| 取消預約   |    應
	| xxxxx   |    結
	+---------+    果
	| 查詢預約   |
	+---------+
	| 新增會議室 |
	+------- -+-------------+
 -->
<html>
	<head>
		<meta charset="UTF-8">
		<title>Booking MeetingRoom</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
	</head>
	<body style="padding: 15px">
		<table>
			<tr>
				<td valign="top">
					<!-- 預約表單 -->
					<sp:form modelAttribute="bookingMeetingRoom" 
							 method="post" 
							 action="/booking" 
							 class="pure-form"
							 target="resultFrame">
						<fieldset>
							<legend>預約表單</legend>
							會議室:
								<sp:select path="roomId" items="${ rooms }" itemValue="roomId" itemLabel="roomName" /><p>
							預約人:
								<sp:select path="userId" items="${ users }" itemValue="id" itemLabel="name" /><p>
							預約日:
								<sp:input type="date" path="bookingDate" required="required"/><p>
							<button type="submit" class="pure-button pure-button-primary">送出</button>	
						</fieldset>
					</sp:form>
					
					<!-- 取消預約 -->
					<form class="pure-form" method="post" action="/booking" target="resultFrame">
						<fieldset>
							<legend>取消預約</legend>
							預約Id: <input type="number" id="bookingId" name="bookingId" required="required" /><p>
							<input type="hidden" id="_method" name="_method" value="DELETE" />
							<button type="submit" class="pure-button pure-button-primary">送出</button>
						</fieldset>
					</form>
					
					<!-- 查詢預約 -->
					<form class="pure-form" method="get" action="/booking/findAll" target="resultFrame">
						<fieldset>
							<legend>查詢預約</legend>
							<button type="submit" class="pure-button pure-button-primary">查詢</button>
						</fieldset>
					</form>
					
					<!-- 新增會議室 -->
					
				</td>
				<!-- 回應結果 -->
				<td valign="top">
					<iframe name="resultFrame" style="border: 0px" width="1200px" height="1000px"></iframe>
				</td>
			</tr>
		</table>
		<%=application.getServerInfo() %>
		
	</body>
</html>







