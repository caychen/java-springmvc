<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="testEmployeeConverter" method="post">
		Employee: <input type="text" name="employee"/>
		<input type="submit" value="提交" />
	</form>
	
	
	<br/><br/>
	<form action="testBindingResult" method="post">
		LastName: <input type="text" name="lastName"/><br/>
		Email: <input type="text" name="email"/><br/>
		Gener: <input type="radio" name="gender" value="1" checked/>男
		<input type="radio" name="gender" value="0"/>女<br/>
		Department: <select name="department.id">
			<option value="101">E-AA</option>
			<option value="102">E-BB</option>
			<option value="103">E-CC</option>
			<option value="104">E-DD</option>
			<option value="105">E-EE</option>
		</select><br/>
		Birth: <input type="text" name="birth"/><br/> 
		Salary: <input type="text" name="salary"/><br/> 
		<input type="submit" value="提交" />
	</form>
</body>
</html>