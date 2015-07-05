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
<title>当前学期课设工作量信息</title>
<link rel="shortcut icon" href="/images/icon.jpg" type="image/x-icon" />
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-editable.css">
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
							<div class="panel-heading"></div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover"
										id="current-cdesignAct-id" data-id="">
										<thead>
											<tr>
												<th>学期</th>
												<th>专业年级</th>
												<th>班级数</th>
												<th>课程设计周数</th>
												<th>准备天数</th>
												<th>总工作量</th>
												<th>计划学时</th>
												<th>工作量分配</th>
												<th>删除</th>
												<th>详情</th>
											</tr>
										</thead>
										<tbody id="cdesignAccount-content">
										</tbody>
									</table>
								</div>
								<div class="panel-footer" style="display: none;">
									<nav>
										<ul id="cdesignAccount-page" class="pagination">
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
						<div class="panel-heading">
							<a class="btn btn-info" aria-hidden="true" data-toggle="modal"
								data-target="#userModal">增加教师</a> <a class="btn btn-info"
								id="btn-calculate-workload">工作量平分</a> <a class="btn btn-info"
								id="btn-save-cdesignActTeachers">保存设置</a>
						</div>
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
									<th>删除</th>
								</tr>
							</thead>
							<tbody id="cdesignActTea-content">
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- row2 end -->
		</div>
		<!--rw1  -->
	</div>
	<!--<div class="container-fluid">  -->
	<!-- 弹窗 -->
	<div id="cdesignAccount-detail">
		<div class="modal fade" id="cdesignAccountModal" tabindex="-1"
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
								<form id="cdesignAccount-form" class="form-horizontal">
									<div class="form-group">
										<label for="cdesignAct-courseDesign-name"
											class="col-sm-2 control-label">课程设计名称</label>
										<div class="col-sm-4">
											<input type="text" class="form-control"
												style="display: none;" name="id" id="cdesignAct-id"
												maxlength="24"> <input type="text"
												class="form-control" style="display: none;"
												name="courseDesign.id" id="cdesignAct-courseDesign-id"
												maxlength="24"> <input type="text"
												class="form-control" name="courseDesign.name"
												id="cdesignAct-courseDesign-name" maxlength="20"
												placeholder="课程设计名称(1-20)" required readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="cdesignAct-major-name"
											class="col-sm-2 control-label">专业</label>
										<div class="col-sm-3">
											<input type="text" class="form-control" name="major.name"
												id="cdesignAct-major-name" required readonly>
										</div>
										<label for="cdesignAct-grade" class="col-sm-2 control-label">年级</label>
										<div class="col-sm-3">
											<input type="text" class="form-control" name="grade"
												id="cdesignAct-grade" maxlength="4"
												pattern="^((?:19|20)\d\d)" placeholder="xxxx" required>
										</div>
									</div>
									<div class="form-group">
										<label for="cdesignAct-classNum"
											class="col-sm-2 control-label">班级数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="classNum"
												id="cdesignAct-classNum" maxlength="5"
												pattern="^[1-9]\d*|0$" placeholder="0-10000" required>
										</div>

										<label for="cdesignAct-weekNum" class="col-sm-2 control-label">设计周数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="weekNum"
												id="cdesignAct-weekNum" maxlength="3" pattern="^[1-9]\d*|0$"
												placeholder="0-100" required>
										</div>
										<label for="cdesignAct-preday" class="col-sm-2 control-label">准备天数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="preday"
												id="cdesignAct-preday" maxlength="5" pattern="^[1-9]\d*|0$"
												placeholder="0-10000" required>
										</div>
									</div>
									<div class="form-group">
										<label for="cdesignAct-workload"
											class="col-sm-2 control-label">标准工作量</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="workload"
												id="cdesignAct-workload" maxlength="5"
												pattern="^(\d|[1-9]\d+)(\.\d+)?$" required>
										</div>
										<label for="cdesignAct-period" class="col-sm-2 control-label">计划学时</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="period"
												id="cdesignAct-period" maxlength="5"
												pattern="^(\d|[1-9]\d+)(\.\d+)?$" required>
										</div>
										<label for="cdesignAct-campus" class="col-sm-2 control-label">校区</label>
										<div class="col-sm-2">
											<select class="form-control" name="campus"
												id="cdesignAct-campus">
												<option value="金石滩" selected>金石滩</option>
												<option value="开发区">开发区</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="cdesignAct-remark" class="col-sm-2 control-label">备注</label>
										<div class="col-sm-offset-2 col-sm-10">
											<textarea class="form-control" name="remark"
												id="cdesignAct-remark" placeholder="0-100个字" maxlength="100"
												rows="3" style="resize: none"></textarea>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" id="cdesignAccount-submit"
												class="btn btn-primary">保存</button>
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
	</div>

	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.form.js"></script>
	<script src="/js/bootstrap-editable.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/JsonPage.js"></script>
	<script src="/js/tcommon.js"></script>
	<script src="/js/tcucdesignact.js"></script>
</body>
</html>