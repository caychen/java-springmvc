<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
</head>
<body style="margin-top: 20px;">
	<div class="container" style="width: 50%;">
		<div class="pager-header">
			<c:if test="${employee == null }">
				<h3 style="text-align: center;">添加员工信息</h3>
			</c:if>
			<c:if test="${employee != null }">
				<h3 style="text-align: center;">修改员工信息</h3>
			</c:if>
		</div>
		<div class="row">
			<form action="${pageContext.request.contextPath }/emp" method="post" class="form-horizontal">
				<c:if test="${employee != null }">
					<input type="hidden" name="_method" value="put"/>
					<input type="hidden" value="${employee.id }" name="id"/>
				</c:if>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">姓名:</label>
					<div class="col-sm-10">
						<c:if test="${employee == null }">
							<input type="text" name="lastName" class="form-control" placeholder="LastName"/>
						</c:if>
						<c:if test="${employee != null }">
							<input type="text" class="form-control" value="${employee.lastName }" readonly="readonly"/>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">邮箱: </label>
					<div class="col-sm-10">
						<c:if test="${employee == null }">
							<input type="text" name="email" class="form-control" placeholder="Email"/>
						</c:if>
						<c:if test="${employee != null }">
							<input type="text" name="email" class="form-control" value="${employee.email }"/>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">性别:</label>
					<div class="col-sm-10">
						<c:if test="${employee == null }">
							<label class="radio-inline">
								<input type="radio" value="0" name="gender" checked/>Female
							</label>
							<label class="radio-inline">
								<input type="radio" value="1" name="gender"/>Male
							</label>
						</c:if>
						<c:if test="${employee != null }">
							<label class="radio-inline">
								<input type="radio" value="0" name="gender" ${employee.gender eq 0 ? 'checked' : '' }/>Female
							</label>
							<label class="radio-inline">
								<input type="radio" value="1" name="gender" ${employee.gender eq 1 ? 'checked' : '' }/>Male
							</label>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">部门:</label>
					<div class="col-sm-10">
						<select class="form-control" name="department.id">
							<c:forEach items="${departments }" var="department">
								<c:if test="${employee == null }">
									<option value="${department.id }">${department.departmentName }</option>
								</c:if>
								<c:if test="${employee != null }">
									<option value="${department.id }" ${department.id eq employee.department.id ? 'selected' : '' }>${department.departmentName }</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<div>
							<input type="submit" class="btn btn-primary col-sm-offset-2 col-sm-2"/>
							<input type="reset" class="btn btn-danger col-sm-2 col-sm-offset-4"/>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>