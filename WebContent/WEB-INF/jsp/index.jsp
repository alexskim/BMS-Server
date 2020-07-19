<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<jsp:include page="header.jsp"></jsp:include>
<div class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1 class="m-0 text-dark">用户列表</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="__MODULE__/Index/">主页面</a></li>
					<li class="breadcrumb-item active">用户列表</li>
				</ol>
			</div>
		</div>
	</div>
</div>
<section class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="card card-success">
					<div class="card-header">
						<h3 class="card-title">列表</h3>
					</div>
					<div class="card-body">
						<table id="example2" class="table table-bordered table-hover">
							<thead>
								<tr align="center">
									<th>编号</th>
									<th>用户名</th>
									<th>密码</th>
									<th>权限</th>
									<th>姓名</th>
									<th>电话</th>
									<th>操作</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="list" items="${userList}">
									<tr>
										<td>${list.uid}</td>
										<td>${list.username}</td>
										<td>${list.password}</td>
										<c:if test="${list.role==-1}">
											<td>封禁</td>
										</c:if>
										<c:if test="${list.role==0}">
											<td>普通用户</td>
										</c:if>
										<c:if test="${list.role==1}">
											<td>管理员</td>
										</c:if>
										<c:if test="${list.role!=-1&&list.role!=0&&list.role!=1}">
											<td>${list.role}</td>
										</c:if>
										<td>${list.trueName}</td>
										<td>${list.tel}</td>
										<td align="center">
											<button
												onclick="setId('${list.uid}','${list.trueName}','${list.tel}' )"
												class="btn btn-primary" data-toggle="modal"
												data-target="#updateUser">修改用户</button>
										</td>
										<td align="center">
											<button onclick="deleteUserById(${list.uid})" class="btn btn-danger">删除</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="card-footer">
						<button class="btn btn-primary" data-toggle="modal"
						data-target="#addUser">添加</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
	<div class="modal fade" id="updateUser" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">修改信息</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<form action="updateInfoById" method="post">
					<div class="modal-body">
						<div class="form-group has-feedback">
							<label for="trueName">姓名</label>
							<input type="text" class="form-control" name="trueName" id="trueName"
								placeholder="姓名" required="required" /> <span
								class="glyphicon glyphicon-user form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="tel">电话</label>
							<input type="tel" class="form-control" name="tel" id="tel"
								placeholder="电话" required="required" /> <span
								class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
						 
						<input type="hidden" name="uid" id="id" />
					
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn btn-primary">修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="addUser" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">添加用户</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<form action="addUser" method="post">
					<div class="modal-body">
						<div class="form-group has-feedback">
							<label for="username">用户名</label>
							<input type="text" class="form-control" id="username" name="username"
								 required="required" /> <span
								class="glyphicon glyphicon-user form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="password">密码</label>
							<input type="password" class="form-control" id="password" name="password"
								 required="required" /> <span
								class="glyphicon glyphicon-lock form-control-feedback"></span>
						</div>
						 
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn btn-primary">添加</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script>
	function deleteUserById(id){
		if(confirm('确定要删除吗？')){
			$.post("deleteUserById",{"uid":id},function(){
				location.reload();
			});
		}
		return false;
	}
 
	
	function setId(id,name,tel){
		$("#id").val(id);
		$("#trueName").val(name);
		$("#tel").val(tel);
	}
	
	$(document).ready(function() {
		if('${message}'=="失败"){
			alert('${message}');
		}
		<%session.setAttribute("message", "");%>
	})
</script>