<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="resources/plugins/fontawesome-free/css/all.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<!-- icheck bootstrap -->
	<link rel="stylesheet" href="resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="resources/dist/css/adminlte.min.css">
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	<link rel="stylesheet" href="resources/plugins/fontawesome-free/css/all.min.css">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
	
	<div class="login-logo">
		<a href="#"><b>管理员</b>后台</a>
	</div>
		<div class="card">
			<div class="card-body login-card-body">
			<p class="login-box-msg">登录以开始会话</p>

			<form method="post" action="login.html">
				<div class="input-group mb-3">
					<input type="text" name="username" class="form-control" placeholder="用户名">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-user"></span>
						</div>
					</div>
				</div>
				<div class="input-group mb-3">
					<input type="password" name="password" class="form-control" placeholder="密码">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-4">
						<button type="submit" class="btn btn-primary btn-block">登录</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- jQuery -->
<script src="resources/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="resources/dist/js/adminlte.min.js"></script>

<script>
	$(document).ready(function() {
		if ('${what}' == "1") {
			alert('${message}');
		}
<%session.setAttribute("message", "");%>
	
<%session.setAttribute("what", "");%>
	})
	if ('${role}' == "1") {
		window.location.href = "/bms/index";
	}
</script>

</body>
</html>
