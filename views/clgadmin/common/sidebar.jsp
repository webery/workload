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
				<div class="list-group"></div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-book" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#experiment">实验管理</a>
			</h4>
		</div>
		<div id="experiment" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group"></div>
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
					<a href="/clgadmin/cdesignact/query" class="list-group-item">课程设计信息</a>
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
					<a href="/clgadmin/grdtdesignact/query" class="list-group-item">毕设工作量</a>
				</div>
			</div>
		</div>
	</div>
</div>
