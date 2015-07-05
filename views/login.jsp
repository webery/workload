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
<title>登陆</title>
<link rel="shortcut icon" href="/images/title_logo.jpg"
	type="image/x-icon" />
<link rel="stylesheet" href="/css/bootstrap.min.css">
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-info">
			<div class="panel-heading" style="text-align: center;">
				<h3 class="panel-title" style="font-size: 26px;">工作量管理系统</h3>
			</div>
			<div class="panel-body">
				<div class="container">
					<div id="in" class="row">
						<div class="col-md-6"></div>
					</div>
					<c:if test="${error != null}">
						<div class="alert alert-warning alert-dismissible fade in"
							style="text-align: center;" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
							${error}
						</div>
					</c:if>
					<form id="login-form" class="form-horizontal" method="POST"
						action="/login">
						<div class="form-group has-feedback">
							<label for="account" class="col-sm-4 control-label">账号</label>
							<div class="col-sm-4">
								<input type="text" value="" class="form-control" id="account"
									pattern="[A-Za-z0-9]{6,10}" maxlength="10" name="account"
									placeholder="账号(6-10数字或者字母)" required autofocus>
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="password" class="col-sm-4 control-label">密码</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="密码(6-10数字或者字母)"
									pattern="[A-Za-z0-9]{6,10}" maxlength="10" required autofocus>
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="password" class="col-sm-4 control-label">验证码</label>
							<div class="col-sm-2">
								<input type="password" class="form-control" id="jcaptchaCode"
									name="jcaptchaCode" pattern="[A-Za-z0-9]{4,6}" maxlength="6"
									required autofocus>
							</div>
							<div class="col-sm-2">
								<img class="jcaptcha-btn jcaptcha-img"
									src="${pageContext.request.contextPath}/jcaptcha.jpg"
									title="点击更换验证码">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-4">
								<button type="submit" id="login-submit" class="btn btn-primary">登陆</button>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			$(".jcaptcha-btn").click(
					function() {
						$(".jcaptcha-img").attr(
								"src",
								'${pageContext.request.contextPath}/jcaptcha.jpg?'
										+ new Date().getTime());
					});
		});
	</script>
</body>
</html>