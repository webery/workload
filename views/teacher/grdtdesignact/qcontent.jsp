<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<div class="container-fluid" id="main-content">
	<!--  -->
	<div class="panel panel-default">
		<div class="panel-heading">
			<form class="form-inline">
				<div class="form-group">
					<label class="sr-only" for="name"></label> <input type="text"
						class="form-control" name="searchWord"
						id="search-grdtdesignAccount" maxlength="20" placeholder="课程设计名称">
				</div>
				<div class="form-group">
					<a id="query" class="btn btn-primary">查詢</a>
				</div>
			</form>
		</div>
		<div class="panel-body"></div>
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
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
					</tr>
				</thead>
				<tbody id="grdtdesignAccount-content">
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
						<h4 class="modal-title">课程设计详情</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-10">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">录入毕业设计信息</h3>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-10">
												<form id="grdtdesignAccount-form" class="form-horizontal">
													<div class="form-group">
														<label for="name" class="col-sm-2 control-label">毕业设计名称</label>
														<div style="display: none;">
															<input type="text" id="id-value" data-id="">
														</div>

														<div class="col-sm-6">
															<input type="text" class="form-control" name="name"
																id="name" maxlength="20" placeholder="课程设计名称(1-20)"
																required>
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
</div>

