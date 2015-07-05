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
<title>当前实验工作量</title>
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
					<!--  -->
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-heading"></div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover"
										id="current-expAct-id" data-id="">
										<thead>
											<tr>
												<th>学期</th>
												<th>实验名称</th>
												<th>校区</th>
												<th>录入时间</th>
												<th>实验目录</th>
												<th>工作量分配</th>
												<th>删除</th>
												<th>详情</th>
											</tr>
										</thead>
										<tbody id="expAct-content">
										</tbody>
									</table>
								</div>
								<div class="table-responsive">
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th><a class="btn btn-info" id="btn-create-expItem">增加</a></th>
												<th><a class="btn btn-info" id="btn-save-expItems">保存</a></th>
											</tr>
											<tr>
												<th>授课年级</th>
												<th>计划学时</th>
												<th>金石滩计划学时</th>
												<th>选课人数</th>
												<th>重修人数</th>
												<th>每班人数</th>
												<th>系数</th>
												<th>教学班次数</th>
												<th>工作量</th>
												<th>删除</th>
											</tr>
										</thead>
										<tbody id="expItem-content">
										</tbody>
									</table>
								</div>
							</div>
							<div class="panel-footer" style="display: none;">
								<nav>
									<ul id="expAct-page" class="pagination">
									</ul>
								</nav>
							</div>
						</div>
					</div>
					<!-- row1 -->
					<div class="row">
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th><a class="btn btn-info" id="btn-create-expActTea"
											aria-hidden="true" data-toggle="modal"
											data-target="#userModal">增加</a></th>
										<th><a class="btn btn-info" id="btn-save-expActTeas">保存</a></th>
									</tr>
									<tr>
										<th>序号</th>
										<th>工号</th>
										<th>教师姓名</th>
										<th>承担教学工作量</th>
										<th>工作时间内教学工作量</th>
										<th>核发酬金工作量</th>
										<th>金石滩校区计划学时</th>
										<th>删除</th>
									</tr>
								</thead>
								<tbody id="expActTea-content">
								</tbody>
							</table>
						</div>
					</div>
					<!-- row2 -->
				</div>
			</div>
			<!-- main-content end -->
		</div>
		<!--rw1  -->
	</div>
	<!--<div class="container-fluid">  -->
	<!-- 弹窗 -->
	<div id="expAct-detail">
		<div class="modal fade" id="expActModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">实验详情</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-10">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">录入实验信息</h3>
									</div>
									<div class="panel-body">
										<form id="expAct-form" class="form-horizontal">
											<div class="form-group">
												<label for="expAct-name" class="col-sm-2 control-label">实验名称</label>
												<div class="col-sm-4">
													<input type="text" style="display: none;"
														class="form-control" name="experiment.id"
														id="expAct-exp-id"> <input type="text"
														class="form-control" name="experiment.name"
														id="expAct-exp-name" required readonly>
												</div>
											</div>
											<div class="form-group">
												<label for="expAct-campus" class="col-sm-2 control-label">校区</label>
												<div class="col-sm-2">
													<select class="form-control" name="campus"
														id="expAct-campus">
														<option value="金石滩" selected>金石滩</option>
														<option value="开发区">开发区</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label for="expAct-period" class="col-sm-2 control-label">计划学时</label>
												<div class="col-sm-2">
													<input type="text" class="form-control" name="period"
														id="expAct-period" required readonly>
												</div>
												<label for="expAct-operiod" class="col-sm-2 control-label">金石滩计划学时</label>
												<div class="col-sm-2">
													<input type="text" class="form-control" name="operiod"
														id="expAct-operiod" required readonly>
												</div>
												<label for="expAct-courseNum" class="col-sm-2 control-label">选课人数</label>
												<div class="col-sm-2">
													<input type="text" class="form-control" name="courseNum"
														id="expAct-courseNum" required readonly>
												</div>
											</div>
											<div class="form-group">
												<label for="expAct-courseRepnum"
													class="col-sm-2 control-label">重修人数</label>
												<div class="col-sm-2">
													<input type="text" class="form-control" name="courseRepnum"
														id="expAct-courseRepnum" required readonly>
												</div>
												<label for="expAct-workload" class="col-sm-2 control-label">工作量</label>
												<div class="col-sm-2">
													<input type="text" class="form-control" name="workload"
														id="expAct-workload" required readonly>
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
									<div class="panel-footer"></div>
								</div>
							</div>
							<!--<div class="col-md-10.2">  -->
							<div class="col-md-2"></div>
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
	</div>

	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.form.js"></script>
	<script src="/js/bootstrap-editable.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/JsonPage.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/tcommon.js"></script>
	<script src="/js/tcuexpact.js"></script>
</body>
</html>