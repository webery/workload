<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!-- 登陆弹窗 -->
<div id="login-detail">
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">用户登陆</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-8">
							<form id="login-form" class="form-horizontal">
								<div class="form-group col-sm-10">
									<label for="account">账号</label>
									<div class="input-group">
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-user"></span></span> <input
											class="form-control " placeholder="账号" name="account"
											value="">
									</div>
									<span id="account_error"
										class="form_error_info help-block text-danger"></span>
								</div>
								<div class="form-group col-sm-10">
									<label for="password">密码:</label>
									<div class="input-group">
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-lock"></span></span> <input
											type="password" class="form-control " placeholder="登录密码"
											name="password">
									</div>
									<span id="password_error"
										class="form_error_info help-block text-danger"></span>
								</div>
								<div class="form-group col-sm-10">
									<button type="submit" id="login-submit" class="btn btn-default">登陆</button>
								</div>
							</form>
						</div>
						<div class="col-sm-2"></div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>