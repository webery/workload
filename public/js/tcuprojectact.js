$(function() {
	//
	loadProjectActs();
	//
	$('#projectAct-form').submit(function(e) {
		$("#projectAct-submit").attr("disabled", true);
		var accountId = $('#current-projectAct-id').attr('data-id');
		$('#projectAct-form').ajaxSubmit({
			url : '/api/user/1234567890/projectAccount/' + accountId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#projectAct-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#projectAct-submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
});

function loadProjectActs() {

	var page = new AjaxPageParser(new ProjectAccountTargetObject(
			'#projectAct-page', '#projectAct-content',
			'/api/user/1234567890/projectAccounts'), 'page');
	page.pageCallBack('1');

}

var ProjectAccountTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.projectAccounts.pageList,
						function(index, account) {

							var html = '<tr data-id="'
									+ account.id
									+ '">'
									+ '<td>'
									+ getSemesterName(account.semester)
									+ '</td>'
									+ '<td>'
									+ account.year
									+ '</td>'
									+ '<td>'
									+ account.spcode
									+ '</td>'
									+ '<td>'
									+ account.sworkload
									+ '</td>'
									+ '<td>'
									+ account.mpcode
									+ '</td>'
									+ '<td>'
									+ account.mworkload
									+ '</td>'
									+ '<td>'
									+ account.mrworkload
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
									+ '<td><span class="glyphicon glyphicon-eye-open" aria-hidden="true" data-toggle="modal" data-target="#projectActModal"></span></td>'
									+ '</tr>';

							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {
			type : 'current'
		};

		return paramData;
	}

	this.paramData = this.getParamData();

}

ProjectAccountTargetObject.prototype.bind = function() {

	$('#projectAct-content td .glyphicon').on(
			'click',
			function() {
				$('#current-projectAct-id').attr('data-id',
						$(this).closest('tr').attr('data-id'));
			});

	$('#projectAct-content td .glyphicon-trash')
			.on(
					'click',
					function() {
						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						var target = $(this).closest('tr');
						ajaxAction({
							url : '/api/user/1234567890/projectAccount/'
									+ accountId,
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

	$('#projectAct-content td .glyphicon-eye-open')
			.on(
					'click',
					function() {
						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						ajaxAction({
							url : '/api/user/1234567890/projectAccount/'
									+ accountId,
							method : 'GET',
						}, {}, {
							callbackHandler : function(msg) {
								var account = msg.data;
								$('#projectAct-year').val(account.year);
								$('#projectAct-spcode').val(account.spcode);
								$('#projectAct-sworkload').val(
										account.sworkload);
								$('#projectAct-mpcode').val(account.mpcode);
								$('#projectAct-mworkload').val(
										account.mworkload);
								$('#projectAct-mrworkload').val(
										account.mrworkload);
								$('#projectAct-remark').val(account.remark);
							},
							exceptionHandler : function(XMLHttpRequest,
									textStatus, errorThrown) {
								alert('服务器忙!');
							}
						});
					});
};
