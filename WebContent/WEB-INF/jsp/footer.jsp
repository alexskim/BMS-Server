<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	</div>
	<footer class="main-footer">
		<div class="float-right d-none d-sm-inline">Version 0.01</div>
		<strong>Copyright &copy; Alexskim https://www.asjks.cc:2083/</strong>
	</footer>
</div>
<!-- jQuery -->
<script src="resources/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="resources/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Bootstrap 4 -->
<script src="resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="resources/dist/js/adminlte.min.js"></script>
</body>
<script>
if('${role}'!="1"){
	alert("您没有访问权限");
	window.location.href="/bms/logout";
}

$(document).ready(function() {
	if('${message}'=="失败"){
		alert('${message}');
	}
	<%session.setAttribute("message", "");%>
})
</script>
</html>