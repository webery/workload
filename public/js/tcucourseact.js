$(function() {

	//
	defaultLoad();
	//
	$('#courseAccount-form').submit(function(e) {
		var accountId = $('#id-value').val();
		if (typeof (accountId) == 'undefined' || accountId.length != 24) {
			e.preventDefault();
			return false;
		}

		$("#submit").attr("disabled", true);
		$('#courseAccount-form').ajaxSubmit({
			url : '/api/user/courseAccount/' + accountId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#submit").attr("disabled", false);
				alert('修改失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
});

function defaultLoad() {
	var page = new AjaxPageParser(new TargetObject('#page',
			'#courseAccount-content', '/api/user/courseAccounts'), 'page');
	page.pageCallBack('1');
}

var TargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.courseAccounts.pageList,
						function(index, account) {

							var html = '<tr data-id="'
									+ account.id
									+ '">'
									+ '<td>'
									+ getSemesterName(account.semester)
									+ '</td>'
									+ '<td>'
									+ account.course.name
									+ '</td>'
									+ '<td>'
									+ account.student
									+ '</td>'
									+ '<td>'
									+ account.classHour
									+ '</td>'
									+ '<td>'
									+ account.typeFactor
									+ '</td>'
									+ '<td>'
									+ account.repFactor
									+ '</td>'
									+ '<td>'
									+ account.workload
									+ '</td>'
									+ '<td>'
									+ account.campus
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#courseAccountModal"></span></td>'
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

TargetObject.prototype.bind = function() {

	$('td .glyphicon-trash').on(
			'click',
			function() {

				var accountId = $(this).closest('tr').attr('data-id');
				var target = $(this).closest('tr');

				var wait = confirm('确定删除?删除后将不能恢复!');
				if (wait == false) {
					return false;
				}

				ajaxAction({
					url : '/api/user/courseAccount/' + accountId,
					method : 'DELETE',
				}, {}, {
					callbackHandler : function(msg) {
						alert(msg.message);
						if (msg.code == 10000) {
							target.remove();
						}
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});
			});

	$('td .glyphicon-eye-open')
			.on(
					'click',
					function() {
						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						ajaxAction({
							url : '/api/user/courseAccount/' + accountId,
							method : 'GET',
						}, {

						}, {
							callbackHandler : function(data) {
								
								var account = data.data;
								$('#id-value').val(account.id);
								$('#courseName').val(account.course.name);
								$('#student').val(account.student);
								$('#courseNum').val(account.courseNum);
								$('#courseRepnum').val(account.courseRepnum);
								$('#peoples').val(
										account.courseNum
												+ account.courseRepnum);
								$('#classHour').val(account.classHour);
								$('#typeFactor').val(account.typeFactor);
								$('#repFactor').val(account.repFactor);
								$('#workload').val(account.workload);
								$('#campus').val(account.campus);

							},
							exceptionHandler : function(XMLHttpRequest,
									textStatus, errorThrown) {
								alert('服务器忙!');
							}
						});
					});
	//
	$('td a.btn-info')
			.on(
					'click',
					function() {

						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}

						var btnoprt = $(this);
						var operation = btnoprt.attr('data-oprt');

						ajaxAction({
							url : '/api/user/account/' + accountId + '/status',
							method : 'PUT',
						}, {
							operation : operation
						}, {
							callbackHandler : function(data) {
								if (data.code != 10000) {
									alert(data.message);
									return false;
								}
								show = operation == 'lock' ? '解锁' : '锁住';
								operation = operation == 'lock' ? 'unlock'
										: 'lock';
								btnoprt.attr('data-oprt', operation);
								btnoprt.html(show);
							},
							exceptionHandler : function(XMLHttpRequest,
									textStatus, errorThrown) {
								alert('操作!');
							}
						});
					});
}
//
