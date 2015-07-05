$(function() {

	//
	loadCdesignAccounts();
	//
	$('#cdesignAccount-form').submit(function(e) {
		var accountId = $('#current-cdesignAct-id').attr('data-id');
		$("#cdesignAccount-submit").attr("disabled", true);
		$('#cdesignAccount-form').ajaxSubmit({
			url : '/api/user/cdesignAccount/' + accountId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#cdesignAccount-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#cdesignAccount-submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
	//
	//
	$('#btn-q-user').on(
			'click',
			function() {
				var page = new AjaxPageParser(new UserTargetObject(
						'#user-page', '#user-content', '/api/college/users'),
						'page');
				page.pageCallBack('1');
			});
	$('#btn-save-cdesignActTeachers').on(
			'click',
			function() {
				var vals = new Array();
				var index = 0;
				var acountId = $('#current-cdesignAct-id').attr('data-id');
				if (typeof (acountId) == 'undefined' || acountId.length != 24) {
					return false;
				}

				$('#cdesignActTea-content').find('tr').each(
						function(index, item) {

							var val = {
								teacher : {}
							};
							val.teacher.id = $(this).find("td").eq(1).text();
							val.workload = $(this).find("td").eq(3).text();
							val.period = $(this).find("td").eq(4).text();
							val.remark = $(this).find("td").eq(5).text();

							vals[index++] = val;
						});
				//
				$.ajax({
					type : 'POST',
					url : '/api/user/cdesignAccount/' + acountId
							+ '/cdesignActTeachers',
					dataType : "json",
					contentType : "application/json",
					data : JSON.stringify(vals),
					success : function(msg) {
						alert(msg.message)
					}
				});
				/*
				 * ajaxAction({ url : '/api/user/cdesignAccount/' + acountId +
				 * '/cdesignActTeachers', method : 'POST', }, {
				 * cdesignActTeachers : JSON.stringify(vals), }, {
				 * callbackHandler : function(msg) { alert(msg.message); },
				 * exceptionHandler : function(XMLHttpRequest, textStatus,
				 * errorThrown) { alert('服务器忙!'); } });
				 */
			});
	$('#btn-calculate-workload').on('click', function() {

		var cdesignActId = $('#current-cdesignAct-id').attr('data-id');
		if (cdesignActId == '') {
			alert('请选择毕业设计工作量!');
			return false;
		}

		// 1.获取总工作量和总计划学时
		var grdtdesignActId = $('#current-cdesignAct-id').attr('data-id');
		var workload = 0.0;
		var period = 0.0;
		$('#cdesignAccount-content').find('tr').each(function(index, item) {
			if (grdtdesignActId == $(this).attr('data-id')) {
				var tds = $(this).find("td");
				workload = tds.eq(5).text();
				period = tds.eq(6).text();
				return false;
			}
		});
		var trs = $('#cdesignActTea-content').find('tr');
		var total = trs.length;
		var avg_workload = workload / total;
		var avg_period = period / total;
		trs.each(function(index, item) {
			var tds = $(this).find("td")
			tds.eq(3).text(avg_workload);
			tds.eq(4).text(avg_period);
		});

	});

});

function loadCdesignAccounts() {

	var page = new AjaxPageParser(new CdesignAccountTargetObject(
			'#cdesignAccount-page', '#cdesignAccount-content',
			'/api/user/cdesignAccounts'), 'page');
	page.pageCallBack('1');

}

//
function bindEditTable() {
	$('#cdesignActTea-content tr td:nth-child(4)').editable({
		validate : function(value) {
			if ($.trim(value) == '') {
				return '不能为空!';
			}
			if (/^(\d|[1-9]\d+)(\.\d+)?$/.test(value)) {
			} else {
				return '请输入大于0的数!';
			}

		}
	});

	$('#cdesignActTea-content tr td:nth-child(5)').editable({
		validate : function(value) {
			if ($.trim(value) == '') {
				return '不能为空!';
			}
			if (/^(\d|[1-9]\d+)(\.\d+)?$/.test(value)) {
			} else {
				return '请输入大于0的数!';
			}

		}
	});

	$('#cdesignActTea-content tr td:nth-child(6)').editable({
		validate : function(value) {

			if (value.length > 50) {
				return '0-50个字';
			}
		}
	});
}
function bindDeleteEditTable() {
	$('#cdesignActTea-content td .glyphicon-trash').on('click', function() {
		$(this).closest('tr').remove();
		// index从新计算
		$('#cdesignActTea-content').find('tr').each(function(index, item) {
			var tds = $(this).find("td");
			tds.eq(0).text(index + 1);
		});

	});
}

var CdesignAccountTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.cdesignAccounts.pageList,
						function(index, account) {

							var html = '<tr data-id="'
									+ account.id
									+ '">'
									+ '<td>'
									+ getSemesterName(account.semester)
									+ '</td>'
									+ '<td>'
									+ (account.major.name + account.grade)
									+ '</td>'
									+ '<td>'
									+ account.classNum
									+ '</td>'
									+ '<td>'
									+ account.weekNum
									+ '</td>'
									+ '<td>'
									+ account.preday
									+ '</td>'
									+ '<td>'
									+ account.workload
									+ '</td>'
									+ '<td>'
									+ account.period
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-fullscreen"></span></td>'
									+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
									+ '<td><span class="glyphicon glyphicon-eye-open" aria-hidden="true" data-toggle="modal" data-target="#cdesignAccountModal"></span></td>'
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

CdesignAccountTargetObject.prototype.bind = function() {

	$('#cdesignAccount-content tr').on(
			'click',
			function() {
				$('#current-cdesignAct-id').attr('data-id',
						$(this).closest('tr').attr('data-id'));
			});

	$('#cdesignAccount-content td .glyphicon-eye-open')
			.on(
					'click',
					function() {
						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						ajaxAction({
							url : '/api/user/cdesignAccount/' + accountId,
							method : 'GET',
						}, {},
								{
									callbackHandler : function(msg) {
										var account = msg.data;
										$('#cdesignAct-id').val(account.id);
										$('#cdesignAct-courseDesign-name').val(
												account.courseDesign.name);
										$('#cdesignAct-major-name').val(
												account.major.name);
										$('#cdesignAct-grade').val(
												account.grade);
										$('#cdesignAct-classNum').val(
												account.classNum);
										$('#cdesignAct-weekNum').val(
												account.weekNum);
										$('#cdesignAct-preday').val(
												account.preday);
										$('#cdesignAct-workload').val(
												account.workload);
										$('#cdesignAct-period').val(
												account.period);
										$('#cdesignAct-campus').val(
												account.campus);
										$('#cdesignAct-remark').val(
												account.remark);
									},
									exceptionHandler : function(XMLHttpRequest,
											textStatus, errorThrown) {
										alert('服务器忙!');
									}
								});
					});
	//
	$('#cdesignAccount-content td .glyphicon-fullscreen')
			.on(
					'click',
					function() {
						$('#cdesignActTea-content').empty();
						var accountId = $(this).closest('tr').attr('data-id');

						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						ajaxAction(
								{
									url : '/api/user/cdesignAccount/'
											+ accountId + '/cdesignActTeachers',
									method : 'GET',
								},
								{},
								{
									callbackHandler : function(data) {

										$
												.each(
														data.cdesignAccounts,
														function(index, account) {
															var html = '<tr>'
																	+ '<td>'
																	+ (index + 1)
																	+ '</td>'
																	+ '<td>'
																	+ account.teacher.id
																	+ '</td>'
																	+ '<td>'
																	+ account.teacher.name
																	+ '</td>'
																	+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="工作量">'
																	+ account.workload
																	+ '</a></td>'
																	+ '<td><a href="#" data-type="text" data-placement="right" data-placeholder="Required" data-title="计划学时">'
																	+ account.period
																	+ '</a></td>'
																	+ '<td><a href="#" data-type="text" data-placement="right" data-placeholder="Required" data-title="备注">'
																	+ account.remark
																	+ '</a></td>'
																	+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
																	+ '</tr>';
															$(
																	'#cdesignActTea-content')
																	.append(
																			html);
														});
										bindEditTable();
										bindDeleteEditTable();
									},
									exceptionHandler : function(XMLHttpRequest,
											textStatus, errorThrown) {
										alert('操作!');
									}
								});
					});
};

var UserTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$.each(jsonPageInfo.users.pageList, function(index, user) {

			var html = '<tr data-id="' + user.id + '">' + '<td>' + user.id
					+ '</td>' + '<td>' + user.name + '</td>' + '<td>'
					+ user.college.name + '</td>' + '<td>' + user.major.name
					+ '</td>'
					+ '<td><span class="glyphicon glyphicon-copy"></span></td>'
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

};

UserTargetObject.prototype.bind = function() {

	$('#user-content td .glyphicon-copy')
			.on(
					'click',
					function() {

						var cdesignActId = $('#current-cdesignAct-id').attr(
								'data-id');
						if (cdesignActId == '') {
							alert('请选择课程设计工作量!');
							return false;
						}

						var userId = $(this).closest('tr').attr('data-id');
						var userName = $(this).closest('tr').children('td').eq(
								1).html();

						// 过来重选
						var isIn = false;
						$('#cdesignActTea-content').find('tr').each(
								function(index, item) {
									var tds = $(this).find("td")
									if (userId == tds.eq(1).text()) {
										alert(userId + ' 已经选择!');
										isIn = true;
										return false;
									}
								});
						if (isIn) {
							return false;
						}

						var index = $("#cdesignActTea-content tr").length;
						var html = '<tr>'
								+ '<td>'
								+ (index + 1)
								+ '</td>'
								+ '<td>'
								+ userId
								+ '</td>'
								+ '<td>'
								+ userName
								+ '</td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="工作量">0</a></td>'
								+ '<td><a href="#" data-type="text" data-placement="right" data-placeholder="Required" data-title="计划学时">0</a></td>'
								+ '<td><a href="#" data-type="text" data-placement="right" data-placeholder="Required" data-title="备注"></a></td>'
								+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
								+ '</tr>';
						$('#cdesignActTea-content').append(html);
						bindEditTable();
						bindDeleteEditTable();
						$('#userModal').modal('hide')
					});

};
