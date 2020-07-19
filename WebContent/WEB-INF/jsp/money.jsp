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
				<h1 class="m-0 text-dark">货币列表</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="__MODULE__/Index/">主页面</a></li>
					<li class="breadcrumb-item active">货币列表</li>
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
									<th>用户 uid</th>
									<th>CNY 余额</th>
									<th>USD 余额</th>
									<th>操作</th>
								</tr>
							</thead>
									<tbody>
										<c:forEach var="list" items="${moneyList}">
											<tr>
												<td>${list.uid}</td>
												<td>${list.CNY}</td>
												<td>${list.USD}</td>
												<td align="center">
													<button
														onclick="setId('${list.uid}','${list.CNY}','${list.USD}' )"
														class="btn btn-primary" data-toggle="modal"
														data-target="#updateMoney">修改</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
<jsp:include page="footer.jsp"></jsp:include>
	<div class="modal fade" id="updateMoney" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">修改</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<form action="updateMoneyById" method="post">
					<div class="modal-body">
						<div class="form-group has-feedback">
							<label for="CNY">CNY 余额</label>
							<input type="text" class="form-control" name="CNY" id="CNY" placeholder="CNY 余额" required="required" />
							<span class="glyphicon glyphicon-user form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="USD">USD 余额</label>
							<input class="form-control" name="USD" id="USD" placeholder="USD 余额" required="required" />
							<span class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
						<input type="hidden" name="uid" id="uid" />
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn btn-primary">修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
<script>
function setId(uid,CNY,USD){
	$("#uid").val(uid);
	$("#CNY").val(CNY);
	$("#USD").val(USD);
}

</script>