<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="springmvc/testForward">test Forward</a>
	<br/><br/>

	<a href="springmvc/testRedirect">test Redirect</a>
	<br/><br/>
	
	<a href="springmvc/testCustomerView">test CustomerView</a>
	<br/><br/>

	<a href="springmvc/testViewAndViewResolver">test ViewAndViewResolver</a>
	<br/><br/>

	<!-- 
		模拟修改操作原始数据
		username: Cay
		password: 123456
		email: 1111@qq.com
		age: 12
	 -->
	 <form action="springmvc/testModelAttribute" method="post">
	 	<input type="hidden" name="id" value="1"/>
	 	username: <input name="username" value="Cay"/>
	 	<br/>
	 	email: <input name="email" value="1111@qq.com"/>
	 	<br/>
	 	age: <input name="age" value="12" />
	 	<br/>
	 	<input type="submit" value="submit"/> 
	 </form>
	 <br/><br/>

	<a href="springmvc/testSessionAttributes">Test SessionAttributes</a>
	<br/><br/>
	
	<a href="springmvc/testMap">Test Map</a>
	<br/><br/>
	
	<a href="springmvc/testModelAndView">Test ModelAndView</a>
	<br/><br/>
	
	<a href="springmvc/testServletAPI">Test ServletAPI</a>
	<br/><br/>

	<form action="springmvc/testPojo">
		username: <input name="username"/> 
		<br/>
		password: <input type="password" name="password" />
		<br/>
		email: <input name="email" />
		<br/>
		age: <input name="age" />
		<br/>
		
		province: <input name="address.province" />
		<br/>
		city: <input name="address.city" />
		<br/>
		
		
		<input type="submit" value="submit" />
	</form>
	<br/><br/>

	<a href="springmvc/testCookieValue">Test CookieValue</a>
	<br/><br/>
	
	<a href="springmvc/testRequestHeader">Test RequestHeader</a>
	<br/><br/>
	<a href="springmvc/testRequestParam?username=Cay&age=10">Test RequestParam</a>
	<br/><br/>

	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="testRestPut"/>
	</form>
	<br/><br/>
	
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="testRestDelete" />
	</form>
	<br/><br/>
	
	<form action="springmvc/testRest" method="post">
		<input type="submit" value="testRestPost" />
	</form>
	<br/><br/>
	
	<a href="springmvc/testRest/1">Test Rest Get</a>
	<br/><br/>
	
	<a href="springmvc/testPathVariable/1">testPathVariable</a>
	<br/><br/>
	
	<a href="springmvc/testAnt/aaa/xyz">testAnt</a>
	<br/><br/>
	
	<a href="springmvc/testParamsAndHeaders?username=Cay&age=10">testParamsAndHeaders</a>
	<br/><br/>
	
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="submit"/>
	</form>
	<br/><br/>
	
	<a href="springmvc/testRequestMapping">Test RequestMapping</a>
	<br/><br/>
	
	<a href="helloworld">Hello World</a>
</body>
</html>