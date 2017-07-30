<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/list.css">
</head>
<body>
	<form id="hiddenForm" action="" method="post">
		<input type="hidden" name="_method" value="DELETE" />
	</form>

	<div class="container">
		<div class="row">
			<c:if test="${empty requestScope.employees }">
				<h3>没有任何员工信息</h3>
			</c:if>
			<c:if test="${!empty requestScope.employees }">
				<div class="page-header">
					<h1>员工列表</h1>
				</div>
				<table class="table table-striped table-bordered table-condensed table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>姓名</th>
							<th>邮箱</th>
							<th>性别</th>
							<th>部门</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${employees }" var="employee">
							<tr>
								<td>${employee.id }</td>
								<td>${employee.lastName }</td>
								<td>${employee.email }</td>
								<td>${employee.gender == 0 ? 'Female' : 'Male' }</td>
								<td>${employee.department.departmentName }</td>
								<td>
									<a href="emp/${employee.id }" class="btn btn-info btn-xs" style="width: 100px;"> 
										<span class="glyphicon glyphicon-pencil"> 编辑</span>
									</a>
								</td>
								<td>
									<a class="delete btn btn-danger btn-xs"	href="emp/${employee.id }" style="width: 100px;"> 
										<span class="glyphicon glyphicon-remove"> 删除</span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<button id="add" class="btn btn-default">添加</button>
			<div class="modal fade" tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button class="close" data-dismiss="modal">
								<span>&times;</span>
							</button>
							<h4 class="modal-title">添加员工信息</h4>
						</div>
						<div class="modal-body">
							<form id="addForm" action="" method="post" class="form-horizontal">
								<div class="form-group">
									<label for="" class="col-sm-2 control-label">姓名:</label>
									<div class="col-sm-10">
										<input id="lastName" type="text" name="lastName" class="form-control"	placeholder="LastName" />
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-sm-2 control-label">邮箱: </label>
									<div class="col-sm-10">
										<input id="email" type="text" name="email" class="form-control" placeholder="Email" />
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-sm-2 control-label">性别:</label>
									<div class="col-sm-10">
										<label class="radio-inline"> 
											<input type="radio" value="0" name="gender" checked />Female
										</label> 
										<label class="radio-inline"> 
											<input type="radio" value="1" name="gender" />Male
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">部门:</label>
									<div class="col-sm-10">
										<select id="department" class="form-control" name="department.id"></select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">生日:</label>
									<div class="col-sm-10">
										<input class="form-control" name="birth" placeholder="yyyy-MM-dd"/>
									</div>
								</div>
							</form>
							<div class="modal-footer">
								<div class="form-group">
									<div class="col-sm-2"></div>
									<div class="col-sm-10">
										<div>
											<input type="submit" class="btn btn-primary" /> 
											<input type="reset" class="btn btn-danger" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(function() {
			$(".delete").click(function() {
				var bDeleteFlag = confirm("确定要删除吗?");
				if (bDeleteFlag) {
					var href = $(this).prop("href");
					$("#hiddenForm").prop('action', href).submit();
				}
				return false;
			});

			$("#add").on('click', function() {
				$.ajax({
					url:'${pageContext.request.contextPath}/emp',
					type:'get',
					dataType:'json',
					success:function(data){
						data = eval(data);
						var $dept = $("#department");
						var str = "<option value='-1'>--请选择--</option>";
						//for(var i = 0; i < data.length;++i){
						$.each(data, function(index, item){
							str += "<option value='" + item.id + "'>" +
							item.departmentName + "</option>";
						})
							
						//}
						$dept.append(str);
					}
				});
				
				$(".modal").modal({
					backdrop : 'static',
					keyboard : false,
				});
			});
			
			$(":input[type='submit']").on('click', function(){
				var lastName = $("#lastName").val();
				var email = $("#email").val();
				var gender = $(":input[name='gender']:checked").val();
				var dept = $("#department option:selected").val();
				
				/* alert($("#addForm").serialize()); */
				$.ajax({
					url:'${pageContext.request.contextPath}/emp?' + $("#addForm").serialize(),
					type:'post',
					success:function(){
						
					}
				});
			});
		});
	</script>
</body>
</html>