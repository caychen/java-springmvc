<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 关于国际化：
			1、在页面上能够根据浏览器语言设置的情况对文本，时间，数据等进行本地化处理
			2、可以在bean中获取国际化资源文件Locale对应的消息
			3、可以通过超链接切换Locale，而不再依赖于浏览器的语言设置情况
			
			解决：
			1、使用jstl的fmt标签
			2、在bean中注入ResourceBundleMessageSource的实例，使用其对应的getMessage方法即可
			3、配置LocaleResolver和LocaleChangeInterceptor
	 -->
	 <fmt:message key="i18n.username"></fmt:message>
	 <a href="i18n2">i18n2</a>
	 
	 
	 <br/><br/>
	 <a href="i18n?locale=zh_CN">中文</a>
	 <br/><br/>
	 <a href="i18n?locale=en_US">英文</a>
</body>
</html>