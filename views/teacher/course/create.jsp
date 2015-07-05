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
<title>录入课程信息</title>
<link rel="shortcut icon" href="/images/icon.jpg" type="image/x-icon" />
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/common.css">
</head>
<body>
	<jsp:include page="../../common/navbar.jsp"></jsp:include>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-2">
				<!-- sidebar start -->
				<jsp:include page="../common/sidebar.jsp"></jsp:include>
			</div>
			<!-- main-content start  -->
			<div class="col-md-10">
				<div class="container-fluid" id="main-content">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">录入课程信息</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-10">
									<form id="course-form" class="form-horizontal">
										<div class="form-group">
											<label for="name" class="col-sm-2 control-label">课程名称</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" name="name"
													id="name" maxlength="20" placeholder="课程名称(1-20)" required>
											</div>
										</div>
										<div class="form-group">
											<label for="remark" class="col-sm-2 control-label">备注</label>
											<div class="col-sm-offset-2 col-sm-10">
												<textarea class="form-control" id="remark" name="remark"
													placeholder="0-100个字" maxlength="150" rows="3"
													style="resize: none"></textarea>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" id="submit" class="btn btn-primary">保存</button>
											</div>
										</div>
									</form>
								</div>
								<!--<div class="col-md-10.2">  -->
								<div class="col-md-2"></div>
								<!--<div class="col-md-4">  -->
							</div>
							<!--rw2  -->
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
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.form.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/tcommon.js"></script>
	<script src="/js/tccourse.js"></script>
</body>
</html>