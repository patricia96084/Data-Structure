<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Beat Google Challenge!</title>
</head>
<body>
	<form action='${requestUri}' method='post'>
		Only inputting your name is allowed, if you have registered before.<br>
		Please make sure there's no marks, otherwise we can find nothing.<br>
		<input type='text' name='name' placeholder='name' /> <br> 
		<input type='text' name='school' placeholder='school' /> 
		<input type='text' name='userArea' placeholder='where you life' /> 
		<input type='text' name='userInterest' placeholder='interested subject (split with space)' />
		<input type='submit' value='submit' />
	</form>
</body>
</html>