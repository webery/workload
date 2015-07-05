<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>403</title>
<link rel="shortcut icon" href="/images/icon.jpg"
	type="image/x-icon" />
<style type="text/css">
.aligncenter {
	clear: both;
	display: block;
	margin: auto;
}
</style>
</head>
<body>
	<div>
		<img src="<%=basePath%>/images/403.jpg" class="aligncenter"></img>
	</div>
</body>
</html>