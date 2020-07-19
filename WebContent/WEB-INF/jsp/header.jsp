<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>主页面</title>
	<!-- Font Awesome Icons -->
	<link rel="stylesheet" href="resources/plugins/fontawesome-free/css/all.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="resources/dist/css/adminlte.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
	<nav class="main-header navbar navbar-expand navbar-white navbar-light">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
			</li>
			<li class="nav-item d-none d-sm-inline-block">
				<a href="logout" class="nav-link">安全退出</a>
			</li>
			<li class="nav-item d-none d-sm-inline-block">
				<a id="time" class="nav-link"></a>
			</li>
		</ul>
	</nav>
	<aside class="main-sidebar sidebar-dark-primary elevation-4">
		<a href="#" class="brand-link">
			<img src="resources/dist/img/bmsLogo.png" alt="Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
			<span class="brand-text font-weight-light">管理员后台</span>
		</a>
		<div class="sidebar">
			<nav class="mt-2">
				<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
					<li class="nav-header">管理面板</li>
					<li class="nav-item has-treeview">
						<a href="/bms/index" class="nav-link">
							<i class="nav-icon fa fa-user"></i>
							<p>
								用户管理
							</p>
						</a>
					</li>
					<li class="nav-item has-treeview">
						<a href="/bms/ad" class="nav-link">
							<i class="nav-icon fa fa-cart-plus"></i>
							<p>
								广告管理
							</p>
						</a>
					</li>
					<li class="nav-item has-treeview">
						<a href="/bms/money" class="nav-link">
							<i class="nav-icon fa fa-credit-card"></i>
							<p>
								余额管理
							</p>
						</a>
					</li>
					<li class="nav-item has-treeview">
						<a href="/bms/details" class="nav-link">
							<i class="nav-icon fa fa-info-circle"></i>
							<p>
								交易明细
							</p>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</aside>
	<div class="content-wrapper">
	<!-- 内容显示 -->