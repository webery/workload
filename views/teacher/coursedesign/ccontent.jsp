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
		<h3 class="panel-title">录入课程设计信息</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-10">
				<form id="courseDesign-form" class="form-horizontal">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">课程设计名称</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="name" id="name"
								maxlength="20" placeholder="课程设计名称(1-20)" required>
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
