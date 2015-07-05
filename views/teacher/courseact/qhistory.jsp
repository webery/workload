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
<title>查询当前课程工作量信息</title>
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
					<div class="panel panel-default">
						<div class="panel-heading"></div>
						<div class="panel-body"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>学期</th>
										<th>课程名称</th>
										<th>授课对象</th>
										<th>课时</th>
										<th>类型系数</th>
										<th>重复课系数</th>
										<th>工作量</th>
										<th>校区</th>
										<th>删除</th>
										<th>详情</th>
									</tr>
								</thead>
								<tbody id="courseAccount-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="page" class="pagination">
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<!-- main-content end -->
		</div>
		<!--rw1  -->
	</div>
	<!--<div class="container-fluid">  -->
	<!-- 弹窗 -->
	<div id="courseAccount-detail">
		<div class="modal fade" id="courseAccountModal" tabindex="-1"
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
								<form id="courseAccount-form" class="form-horizontal">
									<div class="form-group">
										<div style="display: none;">
											<input type="text" class="form-control" name="id"
												id="id-value">
										</div>
										<label for="name" class="col-sm-2 control-label">课程名称</label>
										<div class="col-sm-3">
											<input type="text" class="form-control" name="course.name" id="courseName"
												maxlength="20" placeholder="" readonly readonly>
										</div>
										<label for="student" class="col-sm-2 control-label">授课对象</label>
										<div class="col-sm-3">
											<input type="text" class="form-control" name="student"
												id="student" maxlength="15" placeholder="1-15个字" required>
										</div>
									</div>
									<div class="form-group">
										<label for="courseNum" class="col-sm-2 control-label">正常选课人数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="courseNum"
												id="courseNum" maxlength="3" pattern="^[1-9]\d*|0$"
												placeholder="0-10000" required>
										</div>
										<label for="courseRepnum" class="col-sm-2 control-label">重修人数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="courseRepnum"
												id="courseRepnum" maxlength="3" pattern="^[1-9]\d*|0$"
												placeholder="0-10000" required>
										</div>
										<label for="" class="col-sm-2 control-label">总人数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="" id="peoples"
												maxlength="3" pattern="^[1-9]\d*|0$" placeholder="0"
												readonly required>
										</div>
									</div>
									<div class="form-group">
										<label for="classHour" class="col-sm-2 control-label">计划学时</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="classHour"
												id="classHour" pattern="^[1-9]\d*|0$" maxlength="3"
												placeholder="0-10000" required>
										</div>
										<label for="classHour" class="col-sm-2 control-label">计划学时校区</label>
										<div class="col-sm-2">
											<select class="form-control" name="campus" id="campus">
												<option value="金石滩" selected>金石滩</option>
												<option value="开发区">开发区</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="typeFactor" class="col-sm-2 control-label">课程类型系数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="typeFactor"
												id="typeFactor" pattern="^\d+(.\d{1,2})?$" maxlength="3"
												placeholder="0.0-9.9" required>
										</div>
										<label for="repFactor" class="col-sm-2 control-label">重复课系数</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="repFactor"
												id="repFactor" pattern="^[0-9]+(.[0-9]{1})?$" maxlength="3"
												placeholder="0.0-9.9" required>
										</div>
										<label for="workload" class="col-sm-2 control-label">工作量</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="workload-1"
												id="workload" maxlength="3" placeholder="0" readonly
												required>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-4">
											<button type="submit" id="submit" class="btn btn-primary">保存修改</button>
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

	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jquery.form.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/JsonPage.js"></script>
	<script src="/js/tcommon.js"></script>
	<script src="/js/tqcourseact.js"></script>
</body>
</html>