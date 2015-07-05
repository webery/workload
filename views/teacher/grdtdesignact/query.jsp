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
<title>查询毕业设计信息</title>
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
					<!--  -->
					<div class="row">
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
									<table class="table table-bordered table-hover"
										id="current-grdtdesignAct-id" data-id="">
										<thead>
											<tr>
												<th>学期</th>
												<th>专业年级</th>
												<th>班级数</th>
												<th>学生人数</th>
												<th>计划周数</th>
												<th>系数</th>
												<th>标准工作量</th>
												<th>计划学时</th>
												<th>工作量分配</th>
												<th>详情</th>
											</tr>
										</thead>
										<tbody id="grdtdesignAccount-content">
										</tbody>
									</table>
								</div>
								<div class="panel-footer" style="display: none;">
									<nav>
										<ul id="grdtdesignAccount-page" class="pagination">
										</ul>
									</nav>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- row1 end -->
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading"></div>
						<div class="panel-body"></div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>工号</th>
									<th>姓名</th>
									<th>工作量</th>
									<th>计划学时</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody id="grdtdesignActTea-content">
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- row2 end -->
		</div>
	</div>
	<!--rw1  -->
	<!--<div class="container-fluid">  -->
	<!-- 弹窗 -->
	<div id="grdtdesignAccount-detail">
		<div class="modal fade" id="grdtdesignAccountModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">理论课程工作量详情</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-10">
								<form id="grdtdesignAccount-form" class="form-horizontal">
									<div class="form-group">
										<label for="grdtdesignAct-major"
											class="col-sm-2 control-label">专业</label>
										<div class="col-sm-3">
											<input type="text" style="display: none;"
												name="rdtdesignAct.id" id="rdtdesignAct-id" value="">
											<input type="text" style="display: none;" name="user.id"
												value="404"> <input type="text"
												style="display: none;" name="user.id" value="404"> <input
												type="text" style="display: none;" name="major.id"
												id="grdtdesignAct-major-id"> <input type="text"
												class="form-control" name="major.name"
												id="grdtdesignAct-major-name" required readonly>
										</div>
										<label for="grdtdesignAct-grade"
											class="col-sm-2 control-label">年级</label>
										<div class="col-sm-3">
											<input type="text" class="form-control" name="grade"
												id="grdtdesignAct-grade" maxlength="4"
												pattern="^((?:19|20)\d\d)" placeholder="xxxx" required>
										</div>
									</div>
									<div class="form-group">
										<label for="grdtdesignAct-classNum"
											class="col-sm-2 control-label">班级数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="classNum"
												id="grdtdesignAct-classNum" maxlength="5"
												pattern="^[1-9]\d*|0$" placeholder="0-10000" required>
										</div>
										<label for="grdtdesignAct-stuNum"
											class="col-sm-2 control-label">学生人数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="stuNum"
												id="grdtdesignAct-stuNum" maxlength="5"
												pattern="^[1-9]\d*|0$" placeholder="0-10000" required>
										</div>
									</div>
									<div class="form-group">
										<label for="grdtdesignAct-weekNum"
											class="col-sm-2 control-label">计划周数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="weekNum"
												id="grdtdesignAct-weekNum" maxlength="3"
												pattern="^[1-9]\d*|0$" placeholder="0-100" required>
										</div>
										<label for="grdtdesignAct-factor"
											class="col-sm-2 control-label">系数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="factor"
												id="grdtdesignAct-factor" maxlength="3"
												pattern="^(\d|[1-9]\d+)(\.\d+)?$" placeholder="0.1-9.9"
												required>
										</div>
									</div>
									<div class="form-group">
										<label for="grdtdesignAct-workload"
											class="col-sm-2 control-label">标准工作量</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="workload"
												id="grdtdesignAct-workload" maxlength="5"
												pattern="^(\d|[1-9]\d+)(\.\d+)?$" required>
										</div>
										<label for="grdtdesignAct-period"
											class="col-sm-2 control-label">计划学时</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="period"
												id="grdtdesignAct-period" maxlength="5"
												pattern="^[1-9]\d*|0$" placeholder="0-10000" required>
										</div>
										<label for="grdtdesignAct-campus"
											class="col-sm-2 control-label">校区</label>
										<div class="col-sm-2">
											<select class="form-control" name="campus"
												id="grdtdesignAct-campus">
												<option value="金石滩" selected>金石滩</option>
												<option value="开发区">开发区</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="grdtdesignAct-remark"
											class="col-sm-2 control-label">备注</label>
										<div class="col-sm-offset-2 col-sm-10">
											<textarea class="form-control" name="remark"
												id="grdtdesignAct-remark" placeholder="0-100个字"
												maxlength="100" rows="3" style="resize: none"></textarea>
										</div>
									</div>
								</form>
							</div>
							<!--<div class="col-md-10.2">  -->
							<div class="col-md-1"></div>
							<!--<div class="col-md-4">  -->
						</div>
						<!--rw2  -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 弹窗结束 -->
	<!-- 教师选择弹窗 -->
	<div id="user-detail">
		<div class="modal fade" id="userModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">教师选择</h4>
					</div>
					<div class="modal-body">
						<div class="panel panel-default">
							<div class="panel-heading">
								<form class="form-inline">
									<div class="form-group">
										<label class="sr-only" for="name"></label> <input type="text"
											class="form-control" name="search-user" id="search-user"
											maxlength="20" placeholder="课程名称">
									</div>
									<div class="form-group">
										<a id="btn-q-user" class="btn btn-primary">查詢</a>
									</div>
								</form>
							</div>
							<div class="panel-body"></div>
							<div class="table-responsive">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>工号</th>
											<th>名字</th>
											<th>学院</th>
											<th>专业</th>
											<th>选择</th>
										</tr>
									</thead>
									<tbody id="user-content">
									</tbody>
								</table>
							</div>
							<div class="panel-footer">
								<nav>
									<ul id="user-page" class="pagination">
									</ul>
								</nav>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!--rw1  -->
	</div>
	<!--<div class="container-fluid">  -->

	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.form.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/JsonPage.js"></script>
	<script src="/js/tcommon.js"></script>
	<script src="/js/tqgrdtdesignact.js"></script>
</body>
</html>