<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>密码设置</title>
<link rel="shortcut icon" href="/images/icon.jpg" type="image/x-icon" />
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/teacher_setting.css">
</head>
<body>
	<jsp:include page="../common/navbar.jsp"></jsp:include>
	<jsp:include page="common/header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row-fluid">
			<!-- sidebar start -->
			<div class="col-md-2">
				<jsp:include page="common/sidebar.jsp"></jsp:include>
			</div>
			<!-- sidebar end -->
			<!-- main-content start  -->
			<div class="col-md-10">
				<div class="container-fluid" id="main-content">
					<div class="panel panel-default">
						<div class="panel-heading">修改密码</div>
						<div class="panel-body">
							<form id="password-form" class="form-horizontal">
								<div class="form-group">
									<label for="code" class="col-sm-2 control-label">原始密码</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="code" id="code"
											pattern="[A-Za-z0-9]{5,10}" maxlength="10"
											placeholder="5-10个数字和字母组合" required readonly>
									</div>
								</div>
								<div class="form-group">
									<label for="code" class="col-sm-2 control-label">新密码</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="code" id="code"
											pattern="[A-Za-z0-9]{5,10}" maxlength="10"
											placeholder="5-10个数字和字母组合" required readonly>
									</div>
								</div>
								<div class="form-group">
									<label for="code" class="col-sm-2 control-label">再次输入新密码</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" name="code" id="code"
											pattern="[A-Za-z0-9]{5,10}" maxlength="10"
											placeholder="5-10个数字和字母组合" required readonly>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" id="btn-submit" class="btn btn-primary">修改</button>
									</div>
								</div>
							</form>
						</div>
						<div class="panel-footer"></div>
					</div>
				</div>
			</div>
			<!-- main-content end -->
		</div>
		<!--rw1  -->
	</div>
	<!--<div class="container-fluid">  -->
	<jsp:include page="common/footer.jsp"></jsp:include>

	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/ajaxAction.js"></script>
</body>
</html>