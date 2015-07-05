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
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">录入课程信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<form id="courseact-form" class="form-horizontal">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">课程名称</label>
						<div class="col-sm-3">
							<input type="text" style="display: none;" name="course.id"
								id="course-id"> <input type="text" class="form-control"
								name="course-name" id="course-name" maxlength="20"
								placeholder="课程名称(1-20)" readonly required>
						</div>
						<div class="col-sm-2">
							<a class="btn btn-info" data-toggle="modal"
								data-target="#courseModal">选择</a>
						</div>
						<label for="courseact-student" class="col-sm-2 control-label">授课对象</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="student"
								id="courseact-student" maxlength="15" placeholder="1-15个字"
								required>
						</div>
					</div>
					<div class="form-group">
						<label for="courseact-courseNum" class="col-sm-2 control-label">正常选课人数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="courseNum"
								id="courseact-courseNum" maxlength="3" pattern="^[1-9]\d*|0$"
								placeholder="0-10000" required>
						</div>
						<label for="courseact-courseRepnum" class="col-sm-2 control-label">重修人数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="courseRepnum"
								id="courseact-courseRepnum" maxlength="3" pattern="^[1-9]\d*|0$"
								placeholder="0-10000" required>
						</div>
						<label for="courseact-peoples" class="col-sm-2 control-label">总人数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="peoples"
								id="courseact-peoples" maxlength="3" pattern="^[1-9]\d*|0$"
								placeholder="0" readonly required>
						</div>
					</div>
					<div class="form-group">
						<label for="courseact-classHour" class="col-sm-2 control-label">计划学时</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="classHour"
								id="courseact-classHour" pattern="^[1-9]\d*|0$" maxlength="3"
								placeholder="0-10000" required>
						</div>
						<label for="courseact-campus" class="col-sm-2 control-label">计划学时校区</label>
						<div class="col-sm-2">
							<select class="form-control" name="campus" id="courseact-campus">
								<option value="金石滩" selected>金石滩</option>
								<option value="开发区">开发区</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="courseact-typeFactor" class="col-sm-2 control-label">课程类型系数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="typeFactor"
								id="courseact-typeFactor" pattern="^(\d|[1-9]\d+)(\.\d+)?$"
								maxlength="3" placeholder="0.0-9.9" required>
						</div>
						<label for="courseact-repFactor" class="col-sm-2 control-label">重复课系数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="repFactor"
								id="courseact-repFactor" pattern="^(\d|[1-9]\d+)(\.\d+)?$"
								maxlength="3" placeholder="0.0-9.9" required>
						</div>
						<label for="courseact-workload" class="col-sm-2 control-label">工作量</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="courseact-workload"
								id="courseact-workload" maxlength="5" placeholder="0" readonly
								required>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-4">
							<button type="submit" id="courseact-submit"
								class="btn btn-primary">保存</button>
						</div>
						<div class="col-sm-offset-2 col-sm-4">
							<div class="col-sm-2">
								<a class="btn btn-info" data-toggle="modal"
									data-target="#courseAccountModal">历史导入</a>
							</div>
						</div>
					</div>
				</form>
			</div>
			<!--rw2  -->
		</div>
		<div class="panel-footer"></div>
	</div>
</div>