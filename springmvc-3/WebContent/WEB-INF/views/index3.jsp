<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
		数据校验：
			1)、如何校验？
				(1)、使用JSR303验证标准
				(2)、加入Hibernate Vilidator验证框架
				(3)、在Springmvc配置文件中添加<mvc:annotation-driven/>
				(4)、需要在bean的属性上加入对应的注解
				(5)、在目标方法bean类型的前面添加@Valid注解
				
			2)、校验出错转向什么页面？
			3)、错误信息如何显示，如何把错误信息进行国际化
	 -->
	<form action="testJSR303Validator" method="post">
		LastName: <input type="text" name="lastName"/><form:errors path="lastName"></form:errors>
		<br/>
		Email: <input type="text" name="email"/><form:errors path="email"></form:errors>
		<br/>
		Gener: <input type="radio" name="gender" value="1" checked/>男
		<input type="radio" name="gender" value="0"/>女<br/>
		Department: <select name="department.id">
			<option value="101">D-AA</option>
			<option value="102">D-BB</option>
			<option value="103">D-CC</option>
			<option value="104">D-DD</option>
			<option value="105">D-EE</option>
		</select><br/>
		Birth: <input type="text" name="birth"/><form:errors path="birth"></form:errors>
		<br/> 
		Salary: <input type="text" name="salary"/><br/> 
		<input type="submit" value="提交" />
	</form>
	
</body>
</html>