<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<button>Test Json</button>
<br/>
<br/>
<form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
	File:<input type="file" name="file" />
	Desc:<input name="desc" />
	<input type="submit" value="submit"/>
</form>
<br/>
<br/>
<a href="testResponseEntity">下载</a>jquery-3.1.1.min.js
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("button").on('click',function(){
			$.ajax({
				url:'${pageContext.request.contextPath }/testjson',
				type:'post',
				dataType:'json',
				success:function(data){
					$.each(data, function(index, item){
						alert(item.id + ": " + item.lastName);
					});
				}
			});
		});
	});
</script>
</body>
</html>