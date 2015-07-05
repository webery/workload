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
					data-toggle="collapse" data-parent="#accordion" href="#user">教师管理</a>
			</h4>
		</div>
		<div id="user" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/admin/user/query" class="list-group-item">查询教师信息</a> <a
						href="/admin/user/create" class="list-group-item">录入教师信息</a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-book" aria-hidden="true"></span> <a
					data-toggle="collapse" data-parent="#accordion" href="#userRole">角色管理</a>
			</h4>
		</div>
		<div id="userRole" class="panel-collapse collapse">
			<div class="panel-body">
				<div class="list-group">
					<a href="/admin/userRole/query" class="list-group-item">查询用户角色信息</a>
					<a href="/admin/userRole/create" class="list-group-item">录入用户角色</a>
				</div>
			</div>
		</div>
	</div>
</div>
