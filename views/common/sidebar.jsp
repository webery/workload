<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-book" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#course">理论课程</a>
			</h4>
		</div>
		<div id="course" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/teacher/course/query" class="list-group-item">课程信息</a> <a
						href="/teacher/course/create" class="list-group-item">录入课程</a> <a
						href="/teacher/courseact/query" class="list-group-item">课程工作量记录</a>
					<a href="/teacher/courseact/current" class="list-group-item">本学期课程工作量</a>
					<a href="/teacher/courseact/create" class="list-group-item">录入课程工作量</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class=" glyphicon glyphicon-scale" aria-hidden="true"></span>
				<a data-toggle="collapse" data-parent="#accordion"
					href="#experiment">实验管理</a>
			</h4>
		</div>
		<div id="experiment" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/teacher/experiment/query" class="list-group-item">实验信息</a>
					<a href="/teacher/experiment/create" class="list-group-item">录入实验</a>
					<a href="/teacher/experimentact/query" class="list-group-item">实验工作量记录</a>
					<a href="/teacher/experimentact/current" class="list-group-item">本学期实验工作量</a>
					<a href="/teacher/experimentact/create" class="list-group-item">录入实验工作量</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
				<a data-toggle="collapse" data-parent="#accordion" href="#cdesign">课设工作量</a>
			</h4>
		</div>
		<div id="cdesign" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/teacher/cdesign/query" class="list-group-item">课程设计信息</a>
					<a href="/teacher/cdesign/create" class="list-group-item">录入课设</a>
					<a href="/teacher/cdesignact/query" class="list-group-item">课设工作量记录</a>
					<a href="/teacher/cdesignact/current" class="list-group-item">本学期课设工作量</a>
					<a href="/teacher/cdesignact/create" class="list-group-item">录入课设工作量</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-education" aria-hidden="true"></span>
				<a data-toggle="collapse" data-parent="#accordion"
					href="#grdtdesign">毕设工作量</a>
			</h4>
		</div>
		<div id="grdtdesign" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/teacher/grdtdesignact/query" class="list-group-item">毕设工作量记录</a>
					<a href="/teacher/grdtdesignact/current" class="list-group-item">本学期毕设工作量</a>
					<a href="/teacher/grdtdesignact/create" class="list-group-item">录入毕设工作量</a>
				</div>
			</div>
		</div>
	</div>
</div>
