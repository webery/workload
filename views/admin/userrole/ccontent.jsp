<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">添加用户角色</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-11">
				<div class="panel panel-default">
					<div class="panel-heading">
						<form class="form-inline">
							<div class="form-group">
								<label class="sr-only" for="name"></label> <input type="text"
									class="form-control" name="search-user" id="search-user"
									maxlength="20" placeholder="用户名称">
							</div>
							<div class="form-group">
								<a id="btn-q-user" class="btn btn-primary">查詢</a>
							</div>
						</form>
					</div>
					<div class="panel-body"></div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover"
							id="current-user-id" data-id="">
							<!-- current-user-id -->
							<thead>
								<tr>
									<th>工号</th>
									<th>名字</th>
									<th>学院</th>
									<th>专业</th>
									<th>角色信息</th>
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
			<!--<div class="col-md-11">  -->
			<div class="col-md-1"></div>
			<!--<div class="col-md-4">  -->
		</div>
		<!--rw2  -->
	</div>
	<div class="panel-footer"></div>
</div>
<!-- 角色选择弹窗 -->
<div id="userRole-detail">
	<div class="modal fade" id="userRoleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">用户角色</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<ul id="userRole-tab" class="nav nav-tabs" role="tablist">
								<li class="active" id="btn-query-userRole"><a
									href="#ur-query-tab" role="tab" data-toggle="tab">用户线详情</a></li>
								<li id="btn-create-userRole"><a href="#ur-create-tab"
									role="tab" data-toggle="tab">添加角色</a></li>
							</ul>
						</div>
						<div class="panel-body">
							<div id="userRole-tab" class="tab-content">
								<div class="tab-pane active" id="ur-query-tab">
									<div class="table-responsive">
										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>编号</th>
													<th>角色</th>
													<th>删除</th>
												</tr>
											</thead>
											<tbody id="userRole-content">
											</tbody>
										</table>
									</div>
								</div>
								<div class="tab-pane" id="ur-create-tab">
									<form class="form-inline">
										<div class="form-group">
											<label class="sr-only" for="name"></label> <input type="text"
												class="form-control" name="search-role" id="search-role"
												maxlength="20" placeholder="角色名称">
										</div>
										<div class="form-group">
											<a id="btn-q-role" class="btn btn-primary">查詢</a>
										</div>
									</form>
									<div class="table-responsive">
										<table class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>编号</th>
													<th>角色</th>
													<th>描述</th>
													<th>选择</th>
												</tr>
											</thead>
											<tbody id="role-content">
											</tbody>

										</table>
										<div>
											<nav>
												<ul id="role-page" class="pagination">
												</ul>
											</nav>
										</div>
									</div>
								</div>
							</div>
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

