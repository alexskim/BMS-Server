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
				<h1 class="m-0 text-dark">交易明细列表</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="__MODULE__/Index/">主页面</a></li>
					<li class="breadcrumb-item active">交易明细列表</li>
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
									<th>用户 uid</th>
									<th>交易日期</th>
									<th>对方账户</th>
									<th>金额</th>
									<th>货币类型</th>
									<th>交易类型</th>
								</tr>
							</thead>
								<tbody>
									<c:forEach var="list" items="${detailsList}">
										<tr>
											<td>${list.id}</td>
											<td>${list.uid}</td>
											<td>${list.date}</td>
											<td>${list.toUid}</td>
											<td>${list.amount}</td>
											<td>${list.currency}</td>
											<c:if test="${list.type==0}">
												<td>转账</td>
											</c:if>
											<c:if test="${list.type==1}">
												<td>存款</td>
											</c:if>
											<c:if test="${list.type==2}">
												<td>取款</td>
											</c:if>
											<c:if test="${list.type==3}">
												<td>后台修改</td>
											</c:if>
											<c:if test="${list.type!=0&&list.type!=1&&list.type!=2&&list.type!=3}">
												<td>${list.type}</td>
											</c:if>
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