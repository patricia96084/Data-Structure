<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Go Search!</title>
</head>
<body>
	<form action='${requestUri}' method='post'>
		Please make sure there's no marks, otherwise we can find nothing.<br>
		Your name/nickname can only contained English or numbers.<br>
		We would show you results that related to information about you.<br>
		<input type='text' name='keyword' placeholder='keyword' /> 
		<input type='submit' value='submit' />
	</form>
</body>
</html>