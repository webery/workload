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
		<h3 class="panel-title">录入课程设计工作量信息</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-11">
				<form id="cdesignAccount-form" class="form-horizontal">
					<div class="form-group">
						<label for="cdesignAct-courseDesign-name"
							class="col-sm-2 control-label">课程设计名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" style="display: none;"
								name="courseDesign.id" id="cdesignAct-courseDesign-id"
								maxlength="24"> <input type="text" class="form-control"
								name="courseDesign.name" id="cdesignAct-courseDesign-name"
								maxlength="20" placeholder="课程设计名称(1-20)" required readonly>
						</div>
						<div class="col-sm-1">
							<a class="btn btn-info" data-toggle="modal"
								data-target="#courseDesignModal">选择</a>
						</div>
					</div>
					<div class="form-group">
						<label for="cdesignAct-major-name" class="col-sm-2 control-label">专业</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="major.name"
								id="cdesignAct-major-name" required readonly>
						</div>
						<label for="cdesignAct-grade" class="col-sm-2 control-label">年级</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="grade"
								id="cdesignAct-grade" maxlength="4" pattern="^((?:19|20)\d\d)"
								placeholder="xxxx" required>
						</div>
					</div>
					<div class="form-group">
						<label for="cdesignAct-classNum" class="col-sm-2 control-label">班级数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="classNum"
								id="cdesignAct-classNum" maxlength="5" pattern="^[1-9]\d*|0$"
								placeholder="0-10000" required>
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
						<label for="cdesignAct-workload" class="col-sm-2 control-label">标准工作量</label>
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
							<select class="form-control" name="campus" id="cdesignAct-campus">
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
			<div class="col-md-2"></div>
			<!--<div class="col-md-4">  -->
		</div>
		<!--rw2  -->
	</div>
	<div class="panel-footer"></div>
</div>
<!-- 弹窗 -->
<!-- 选择课程弹窗 -->
<div id="courseDesign-detail">
	<div class="modal fade" id="courseDesignModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">课程设计选择</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form class="form-inline">
								<div class="form-group">
									<label class="sr-only" for="name"></label> <input type="text"
										class="form-control" name="search-cdesign" id="search-cdesign"
										maxlength="20" placeholder="课程名称">
								</div>
								<div class="form-group">
									<a id="query-cdesign" class="btn btn-primary">查詢</a>
								</div>
							</form>
						</div>
						<div class="panel-body"></div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>名称</th>
										<th>所属学院</th>
										<th>选择</th>
									</tr>
								</thead>
								<tbody id="courseDesign-content">
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<nav>
								<ul id="courseDesign-page" class="pagination">
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
<!-- 选择弹窗结束 -->