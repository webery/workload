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
<title>我的毕设设工作量</title>
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
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only" for="name"></label> <input type="text"
										class="form-control" id="search-semester" maxlength="4"
										placeholder="xxxx(查询年)">
								</div>
								<div class="form-group">
									<a id="btn-query-semester" class="btn btn-primary">查詢</a>
								</div>
							</form>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>学期</th>
											<th>查看</th>
										</tr>
									</thead>
									<tbody id="semester-content">
									</tbody>
								</table>
							</div>
							<div class="table-responsive">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>学期</th>
											<th>专业年级</th>
											<th>工作量</th>
											<th>计划学时</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody id="grdtdesignActTea-content">
									</tbody>
								</table>
							</div>
							<div class="panel-footer" style="display: none;">
								<nav>
									<ul id="grdtdesignActTea-page" class="pagination">
									</ul>
								</nav>
							</div>
						</div>

					</div>
				</div>
			</div>
			<!-- row2 end -->
		</div>
		<!--rw1  -->
	</div>
	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/JsonPage.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/tcommon.js"></script>
	<script src="/js/mygrdtdesignact.js"></script>
</body>
</html>