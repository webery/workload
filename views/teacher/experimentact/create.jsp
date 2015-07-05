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
<title>录入实验工作量</title>
<link rel="shortcut icon" href="/images/icon.jpg" type="image/x-icon" />
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/common.css">
</head>
<body>
	<jsp:include page="../../common/navbar.jsp"></jsp:include>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row-fluid">
			<!-- sidebar start -->
			<div class="col-md-2">
				<!-- sidebar start -->
				<jsp:include page="../common/sidebar.jsp"></jsp:include>
			</div>
			<!-- sidebar end -->
			<!-- main-content start  -->
			<div class="col-md-10">
				<div class="container-fluid" id="main-content">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">录入实验信息</h3>
						</div>
						<div class="panel-body">
							<form id="expAct-form" class="form-horizontal">
								<div class="form-group">
									<label for="expAct-name" class="col-sm-2 control-label">实验名称</label>
									<div class="col-sm-4">
										<input type="text" style="display: none;" class="form-control"
											name="experiment.id" id="expAct-exp-id"> <input
											type="text" class="form-control" name="experiment.name"
											id="expAct-exp-name" required readonly>
									</div>
									<div class="col-sm-1">
										<a class="btn btn-info" data-toggle="modal"
											data-target="#experimentModal">选择</a>
									</div>
								</div>
								<div class="form-group">
									<label for="expAct-campus" class="col-sm-2 control-label">校区</label>
									<div class="col-sm-2">
										<select class="form-control" name="campus" id="expAct-campus">
											<option value="金石滩" selected>金石滩</option>
											<option value="开发区">开发区</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" id="expAct-submit"
											class="btn btn-primary">保存</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>
			</div>
		</div>
		<!-- main-content end -->
	</div>
	<!--<div class="container-fluid">  -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<!-- 选择课程弹窗 -->
	<div id="experiment-detail">
		<div class="modal fade" id="experimentModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">实验选择</h4>
					</div>
					<div class="modal-body">
						<div class="panel panel-default">
							<div class="panel-heading">
								<form class="form-inline">
									<div class="form-group">
										<label class="sr-only" for="name"></label> <input type="text"
											class="form-control" id="search-exp" maxlength="20"
											placeholder="实验名称">
									</div>
									<div class="form-group">
										<a id="btn-query-exp" class="btn btn-primary">查詢</a>
									</div>
								</form>
							</div>
							<div class="panel-body"></div>
							<div class="table-responsive">
								<table class="table table-bordered table-hover"
									id="current-exp-id" data-id="">
									<thead>
										<tr>
											<th>名称</th>
											<th>所属学院</th>
											<th>录入时间</th>
											<th>选择</th>
										</tr>
									</thead>
									<tbody id="experiment-content">
									</tbody>
								</table>
							</div>
							<div class="panel-footer">
								<nav>
									<ul id="experiment-page" class="pagination">
									</ul>
								</nav>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 选择弹窗结束 -->

	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.form.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/JsonPage.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/tcommon.js"></script>
	<script src="/js/tcexpact.js"></script>
</body>
</html>