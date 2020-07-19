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
				<h1 class="m-0 text-dark">广告列表</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="__MODULE__/Index/">主页面</a></li>
					<li class="breadcrumb-item active">广告列表</li>
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
									<th>投放商联系方式</th>
									<th>广告内容</th>
									<th>图片</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>操作</th>
									<th>删除</th>
								</tr>
							</thead>
									<tbody>
										<c:forEach var="list" items="${adList}">
											<tr>
												<td>${list.id}</td>
												<td>${list.contact}</td>
												<td>${list.content}</td>
												<td><img src="${list.img}" height="100" width="200"/></td>
												<td>${list.begin}</td>
												<td>${list.end}</td>
												<td align="center">
													<button
														onclick="setId('${list.id}','${list.contact}','${list.content}','${list.img}','${list.begin}','${list.end}' )"
														class="btn btn-primary" data-toggle="modal"
														data-target="#updateAd">修改</button>
												</td>
												<td align="center">
													<button onclick="deleteAdById(${list.id})"
														class="btn btn-danger">删除</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
						</table>
					</div>
					<div class="card-footer">
						<button class="btn btn-primary" data-toggle="modal"
						data-target="#addAd">添加</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
<jsp:include page="footer.jsp"></jsp:include>
	<div class="modal fade" id="updateAd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">修改信息</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<form action="updateAdById" method="post">
					<div class="modal-body">
						<div class="form-group has-feedback">
							<label for="contact">投放商联系方式</label>
							<input type="text" class="form-control" name="contact" id="contact" placeholder="投放商联系方式" required="required" />
							<span class="glyphicon glyphicon-user form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="content">广告内容</label>
							<textarea class="form-control" name="content" id="content" placeholder="广告内容" required="required" ></textarea>
							<span class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="img">图片(Base64格式)</label>
							<textarea class="form-control" name="img" id="img" placeholder="图片(Base64格式)" required="required" ></textarea>
							<span class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="begin">开始时间</label>
							<input type="datetime-local" step="01" class="form-control" name="begin" id="begin" placeholder="开始时间" required="required" />
							<span class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="end">结束时间</label>
							<input type="datetime-local" step="01" class="form-control" name="end" id="end" placeholder="结束时间" required="required" />
							<span class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
						<input type="hidden" name="id" id="id" />
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn btn-primary">修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="addAd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">添加广告</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<form action="addAd" method="post">
					<div class="modal-body">
						<div class="form-group has-feedback">
							<label for="contact">投放商联系方式</label>
							<input type="text" class="form-control" name="contact" id="contact" placeholder="投放商联系方式" required="required" />
							<span class="glyphicon glyphicon-user form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="content">广告内容</label>
							<textarea class="form-control" name="content" id="content" placeholder="广告内容" required="required" ></textarea>
							<span class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="img">图片(Base64格式)</label>
							<textarea class="form-control" name="img" id="img" placeholder="图片(Base64格式)" required="required" ></textarea>
							<span class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="begin">开始时间</label>
							<input type="datetime-local" step="01" class="form-control" name="begin" id="begin" placeholder="开始时间" required="required" />
							<span class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<label for="end">结束时间</label>
							<input type="datetime-local" step="01" class="form-control" name="end" id="end" placeholder="结束时间" required="required" />
							<span class="glyphicon glyphicon-phone form-control-feedback"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn btn-primary">添加</button>
					</div>
				</form>
			</div>
		</div>
	</div>
<script>
function setId(id,contact,content,img,begin,end){
	$("#id").val(id);
	$("#contact").val(contact);
	$("#content").val(content);
	$("#img").val(img);
	$("#begin").val(timeConverter(begin));
	$("#end").val(timeConverter(end));
}
function deleteAdById(id){
	if(confirm('确定要删除吗？')){
		$.post("deleteAdById",{"id":id},function(){
			location.reload();
		});
	}
	return false;
}
function timeConverter(timestamp){
    var a = new Date(timestamp*1);
    var months = ['01','02','03','04','05','06','07','08','09','10','11','12'];
    var days = ['01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31'];
    var hours = ['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'];
    var mins = ['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59'];
    var secs = ['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59'];
    
    
    var year = a.getFullYear();
    var month = months[a.getMonth()];
    var day = days[a.getDay()];
    var hour = hours[a.getHours()];
    var min = mins[a.getMinutes()];
    var sec = secs[a.getSeconds()];
    var time = year + '-' + month + '-' + day + 'T' + hour + ':' + min + ':' + sec;

    return time;
}

</script>