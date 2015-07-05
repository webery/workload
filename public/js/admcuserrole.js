$(function() {

	$('#btn-q-user').on(
			'click',
			function() {
				var page = new AjaxPageParser(new UserTargetObject(
						'#user-page', '#user-content', '/api/users'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#btn-q-role').on(
			'click',
			function() {
				var page = new AjaxPageParser(new RoleTargetObject(
						'#role-page', '#role-content', '/api/roles'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#btn-query-userRole').on('click', function() {
		var userId = $('#current-user-id').attr('data-id');
		loadUserRoles(userId);
	});
});

var UserTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.users.pageList,
						function(index, user) {

							var html = '<tr data-id="'
									+ user.id
									+ '">'
									+ '<td>'
									+ user.id
									+ '</td>'
									+ '<td>'
									+ user.name
									+ '</td>'
									+ '<td>'
									+ user.college.name
									+ '</td>'
									+ '<td>'
									+ user.major.name
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#userRoleModal"></span></td>'
									+ '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {
		var userName = $('#search-user').val();
		var paramData = {
			userName : userName,
		};

		return paramData;
	}

	this.paramData = this.getParamData();

}

UserTargetObject.prototype.bind = function() {

	//
	$('#user-content td .glyphicon').on(
			'click',
			function() {
				$('#current-user-id').attr('data-id',
						$(this).closest('tr').attr('data-id'));
			});
	//
	$('#user-content td .glyphicon-eye-open').on('click', function() {
		var userId = $(this).closest('tr').attr('data-id');
		loadUserRoles(userId);
	});

};
//
var RoleTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$.each(jsonPageInfo.roles.pageList, function(index, role) {

			var html = '<tr data-id="' + role.id + '">' + '<td>' + (index + 1)
					+ '</td>' + '<td>' + role.roles + '</td>' + '<td>'
					+ role.description + '</td>'
					+ '<td><span class=" glyphicon glyphicon-ok"></span></td>'
					+ '</tr>';
			$(pageContainer).append(html);
		});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {
		var userName = $('#search-user').val();
		var paramData = {
			userName : userName,
		};

		return paramData;
	}

	this.paramData = this.getParamData();

}

RoleTargetObject.prototype.bind = function() {

	$('#role-content td .glyphicon-ok').on('click', function() {
		var userId = $('#current-user-id').attr('data-id');
		var roleId = $(this).closest('tr').attr('data-id');
		submitUserRole(userId, roleId);
	});
};

function submitUserRole(userId, roleId) {
	ajaxAction({
		url : '/api/user/' + userId + '/role',
		method : 'POST',
	}, {
		'user.id' : userId,
		'role.id' : roleId,
	}, {
		callbackHandler : function(msg) {
			alert(msg.message);
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}

function loadUserRoles(userId) {
	ajaxAction(
			{
				url : '/api/user/' + userId + '/roles',
				method : 'GET',
			},
			{

			},
			{
				callbackHandler : function(msg) {
					$('#userRole-content').empty();
					$
							.each(
									msg.roles,
									function(index, userRole) {
										var html = '<tr data-id="'
												+ userRole.role.id
												+ '">'
												+ '<td>'
												+ (index + 1)
												+ '</td>'
												+ '<td>'
												+ userRole.role.roles
												+ '</td>'
												+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
												+ '</tr>';
										$('#userRole-content').append(html);
									});
					// bind
					$('#userRole-content td .glyphicon-trash').on(
							'click',
							function() {
								var userId = $('#current-user-id').attr(
										'data-id');
								var roleId = $(this).closest('tr').attr(
										'data-id');
								var target = $(this).closest('tr');
								ajaxAction({
									url : '/api/user/' + userId + '/role/'
											+ roleId,
									method : 'DELETE',
								}, {}, {
									callbackHandler : function(msg) {
										alert(msg.message);
										if (msg.code == 10000) {
											target.remove();
										}
									},
									exceptionHandler : function(XMLHttpRequest,
											textStatus, errorThrown) {
										alert('服务器忙!');
									}
								});
							});
				},
				exceptionHandler : function(XMLHttpRequest, textStatus,
						errorThrown) {
					alert('服务器忙!');
				}
			});
}