<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<title>Pass Code</title>
	</head>
	<body style="padding: 15px">
		<!-- Pass Code -->
		<form class="pure-form">
			<fieldset>
				<legend>Pass Code</legend>
				Code: <input type="text" id="code" name="code" placeholder="請輸入PassCode" required>
				<img src="/JavaWeb/passcode" valign="middle"><p>
				<button type="submit" class="pure-button pure-button-primary">驗證</button>
			</fieldset>
		</form>
	</body>
</html>