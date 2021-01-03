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
		If you haven't tell us about yourself, please answer the following question.<br>
		You should answer at least one question.<br>
		<input type='text' name='school' placeholder='school' /> 
		<input type='text' name='place of residence' placeholder='area' /> 
		<input type='text' name='interested subject (split with space)' placeholder='interest' /> 
		<input type='submit' value='submit' />
	</form>
</body>
</html>