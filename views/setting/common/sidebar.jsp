<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<nav class="navbar navbar-default navbar-static-left">
	<ul class="list-group">
		<a href="#" class="list-group-item"><span
			class="glyphicon glyphicon-cog" aria-hidden="true"></span>帐号设置</a>
		<a href="/teacher/1111111/setting/password"
			class="list-group-item active"><span
			class="glyphicon glyphicon-lock" aria-hidden="true"></span>密码设置</a>
		<a href="#" class="list-group-item"><span
			class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>登陆记录</a>
		<a href="#" class="list-group-item"><span
			class="glyphicon glyphicon-lock" aria-hidden="true"></span>密码设置</a>
	</ul>
</nav>
