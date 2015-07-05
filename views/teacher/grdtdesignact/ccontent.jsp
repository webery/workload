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
		<h3 class="panel-title">录入毕业设计工作量信息</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-11">
				<form id="grdtdesignAccount-form" class="form-horizontal">
					<div class="form-group">
						<label for="grdtdesignAct-major" class="col-sm-2 control-label">专业</label>
						<div class="col-sm-3">
							<input type="text" style="display: none;" name="major.id"
								id="grdtdesignAct-major-id"> <input type="text"
								class="form-control" name="major.name"
								id="grdtdesignAct-major-name" required readonly>
						</div>
						<label for="grdtdesignAct-grade" class="col-sm-2 control-label">年级</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="grade"
								id="grdtdesignAct-grade" maxlength="4"
								pattern="^((?:19|20)\d\d)" placeholder="xxxx" required>
						</div>
					</div>
					<div class="form-group">
						<label for="grdtdesignAct-classNum" class="col-sm-2 control-label">班级数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="classNum"
								id="grdtdesignAct-classNum" maxlength="5" pattern="^[1-9]\d*|0$"
								placeholder="0-10000" required>
						</div>
						<label for="grdtdesignAct-stuNum" class="col-sm-2 control-label">学生人数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="stuNum"
								id="grdtdesignAct-stuNum" maxlength="5" pattern="^[1-9]\d*|0$"
								placeholder="0-10000" required>
						</div>
					</div>
					<div class="form-group">
						<label for="grdtdesignAct-weekNum" class="col-sm-2 control-label">计划周数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="weekNum"
								id="grdtdesignAct-weekNum" maxlength="3" pattern="^[1-9]\d*|0$"
								placeholder="0-100" required>
						</div>
						<label for="grdtdesignAct-factor" class="col-sm-2 control-label">系数</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="factor"
								id="grdtdesignAct-factor" maxlength="3"
								pattern="^(\d|[1-9]\d+)(\.\d+)?$" placeholder="0.1-9.9" required>
						</div>
					</div>
					<div class="form-group">
						<label for="grdtdesignAct-workload" class="col-sm-2 control-label">标准工作量</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="workload"
								id="grdtdesignAct-workload" maxlength="5"
								pattern="^(\d|[1-9]\d+)(\.\d+)?$" required>
						</div>
						<label for="grdtdesignAct-campus" class="col-sm-2 control-label">校区</label>
						<div class="col-sm-2">
							<select class="form-control" name="campus"
								id="grdtdesignAct-campus">
								<option value="金石滩" selected>金石滩</option>
								<option value="开发区">开发区</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="grdtdesignAct-remark" class="col-sm-2 control-label">备注</label>
						<div class="col-sm-offset-2 col-sm-10">
							<textarea class="form-control" name="remark"
								id="grdtdesignAct-remark" placeholder="0-100个字" maxlength="100"
								rows="3" style="resize: none"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" id="grdtdesignAccount-submit"
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