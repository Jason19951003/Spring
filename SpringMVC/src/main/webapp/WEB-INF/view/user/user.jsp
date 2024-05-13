<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Spring Form 表單標籤 -->
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User 首頁</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
	</head>
	<body style="padding: 15px;">
		<h1>User 資料維護</h1>
		<hr/>
		<table>
			<tr>
				<!-- User 表單 -->
				<td>
					<%@include file="userform.jspf" %>
				</td>
				<!-- User 圖表 -->
				
				<td></td>
				<td></td>
			</tr>
			<tr>
				<!-- User 列表 -->
				<td colspan="2">
					<%@include file="userlist.jspf" %>
				</td>
			</tr>
		</table>
	</body>
</html>