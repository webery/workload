<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="http://www.dlnu.edu.cn/new/index.htm">
				<img alt="Brand" src="/images/logo.jpg">
			</a>
		</div>
		<!---->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<shiro:hasRole name="teacher">
					<li class=""><a href="/teacher/home">教师中心 </a></li>
				</shiro:hasRole>
				<shiro:hasRole name="clgadmin">
					<li class=""><a href="/clgadmin/home">学院管理中心 </a></li>
				</shiro:hasRole>
				<shiro:hasRole name="admin">
					<li class=""><a href="/admin/home">管理员中心 </a></li>
				</shiro:hasRole>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">个人管理
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/logout" id="user-logout"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span>退出登陆</a></li>
						<li><a href="/user/setting" id="user-logout"><span
								class="glyphicon glyphicon-user" aria-hidden="true"></span>帐号管理</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
