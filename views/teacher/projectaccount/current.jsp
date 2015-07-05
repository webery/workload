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
<title>本学期大创信息</title>
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
										id="current-projectAct-id" data-id="">
										<thead>
											<tr>
												<th>学期</th>
												<th>年份</th>
												<th>单项目编号</th>
												<th>单工作量</th>
												<th>多项目编号</th>
												<th>多项目工作量</th>
												<th>实得工作量</th>
												<th>删除</th>
												<th>详情</th>
											</tr>
										</thead>
										<tbody id="projectAct-content">
										</tbody>
									</table>
								</div>
								<div class="panel-footer" style="display: none;">
									<nav>
										<ul id="projectAct-page" class="pagination">
										</ul>
									</nav>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 弹窗 -->
	<div id="projectAct-detail">
		<div class="modal fade" id="projectActModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">大创工作量详情</h4>
					</div>
					<div class="modal-body">
						<form id="projectAct-form" class="form-horizontal">
							<div class="form-group">
								<label for="projectAct-year" class="col-sm-2 control-label">年度</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="year"
										id="projectAct-year" maxlength="4" pattern="^((?:19|20)\d\d)"
										placeholder="xxxx" required>
								</div>
							</div>
							<div class="form-group">
								<label for="projectAct-spcode" class="col-sm-2 control-label">单项目编号</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" name="spcode"
										id="projectAct-spcode" pattern="[A-Za-z0-9]{0,10}"
										maxlength="10" placeholder="0-10个数字或字母" required>
								</div>
								<label for="projectAct-sworkload" class="col-sm-2 control-label">标准工作量</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" name="sworkload"
										id="projectAct-sworkload" maxlength="5"
										pattern="^(\d|[1-9]\d+)(\.\d+)?$" required>
								</div>
							</div>
							<div class="form-group">
								<label for="projectAct-mpcode" class="col-sm-2 control-label">多项目编号</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" name="mpcode"
										id="projectAct-mpcode" pattern="[A-Za-z0-9]{0,10}"
										maxlength="10" placeholder="0-10个数字或字母" required>
								</div>
								<label for="projectAct-mworkload" class="col-sm-2 control-label">标准工作量</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" name="mworkload"
										id="projectAct-mworkload" maxlength="5"
										pattern="^(\d|[1-9]\d+)(\.\d+)?$" required>
								</div>
								<label for="projectAct-mrworkload"
									class="col-sm-2 control-label">实际分到工作量</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" name="mrworkload"
										id="projectAct-mrworkload" maxlength="5"
										pattern="^(\d|[1-9]\d+)(\.\d+)?$" required>
								</div>
							</div>
							<div class="form-group">
								<label for="projectAct-remark" class="col-sm-2 control-label">备注</label>
								<div class="col-sm-offset-2 col-sm-10">
									<textarea class="form-control" name="remark"
										id="projectAct-remark" placeholder="0-100个字" maxlength="100"
										rows="3" style="resize: none"></textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" id="projectAct-submit"
										class="btn btn-primary">保存</button>
								</div>
							</div>
						</form>
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
	<script src="/js/bootstrap-editable.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/ajaxAction.js"></script>
	<script src="/js/JsonPage.js"></script>
	<script src="/js/tcommon.js"></script>
	<script src="/js/tcuprojectact.js"></script>
</body>
</html>