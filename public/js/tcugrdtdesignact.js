$(function() {

	//
	loadCourseAccounts();
	//
	$('#grdtdesignAccount-form').submit(function(e) {
		var accountId = $('#rdtdesignAct-id').val();
		$("#grdtdesignAccount-submit").attr("disabled", true);
		$('#grdtdesignAccount-form').ajaxSubmit({
			url : '/api/user/grdtdesignAccount/' + accountId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#grdtdesignAccount-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#grdtdesignAccount-submit").attr("disabled", false);
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
	$('#btn-save-grdtTeachers').on(
			'click',
			function() {
				var vals = new Array();
				var index = 0;
				var acountId = $('#current-grdtdesignAct-id').attr('data-id');
				if (typeof (acountId) == 'undefined' || acountId.length != 24) {
					return false;
				}

				$('#grdtdesignActTea-content').find('tr').each(
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
					url : '/api/user/grdtdesignAccount/' + acountId
							+ '/grdtdesignActTeachers',
					dataType : "json",
					contentType : "application/json",
					data : JSON.stringify(vals),
					success : function(msg) {
						alert(msg.message)
					}
				});
				/*
				 * ajaxAction({ url : '/api/user/grdtdesignAccount/' + acountId +
				 * '/grdtdesignActTeachers', method : 'POST', }, {
				 * grdtdesignActTeachers : JSON.stringify(vals), }, {
				 * callbackHandler : function(msg) { alert(msg.message); },
				 * exceptionHandler : function(XMLHttpRequest, textStatus,
				 * errorThrown) { alert('服务器忙!'); } });
				 */
			});

	$('#btn-calculate-workload').on('click', function() {

		var grdtdesignActId = $('#current-grdtdesignAct-id').attr('data-id');
		if (grdtdesignActId == '') {
			alert('请选择毕业设计工作量!');
			return false;
		}

		// 1.获取总工作量和总计划学时
		var grdtdesignActId = $('#current-grdtdesignAct-id').attr('data-id');
		var workload = 0.0;
		var period = 0.0;
		$('#grdtdesignAccount-content').find('tr').each(function(index, item) {
			if (grdtdesignActId == $(this).attr('data-id')) {
				var tds = $(this).find("td");
				workload = tds.eq(6).text();
				period = tds.eq(7).text();
				return false;
			}
		});
		var trs = $('#grdtdesignActTea-content').find('tr');
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

function loadCourseAccounts() {

	var page = new AjaxPageParser(new GrdtdesignAccountTargetObject(
			'#grdtdesignAccount-page', '#grdtdesignAccount-content',
			'/api/user/grdtdesignAccounts'), 'page');
	page.pageCallBack('1');

}

//
function bindEditTable() {
	$('#grdtdesignActTea-content tr td:nth-child(4)').editable({
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

	$('#grdtdesignActTea-content tr td:nth-child(5)').editable({
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

	$('#grdtdesignActTea-content tr td:nth-child(6)').editable({
		validate : function(value) {

			if (value.length > 50) {
				return '0-50个字';
			}
		}
	});
}
function bindDeleteEditTable() {
	$('#grdtdesignActTea-content td .glyphicon-trash').on('click', function() {
		$(this).closest('tr').remove();
		// index从新计算
		$('#grdtdesignActTea-content').find('tr').each(function(index, item) {
			var tds = $(this).find("td");
			tds.eq(0).text(index + 1);
		});
	});
}

var GrdtdesignAccountTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.grdtdesignAccounts.pageList,
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
									+ account.stuNum
									+ '</td>'
									+ '<td>'
									+ account.weekNum
									+ '</td>'
									+ '<td>'
									+ account.factor
									+ '</td>'
									+ '<td>'
									+ account.workload
									+ '</td>'
									+ '<td>'
									+ account.period
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-fullscreen"></span></td>'
									+ '<td><span class="glyphicon glyphicon-eye-open" aria-hidden="true" data-toggle="modal" data-target="#grdtdesignAccountModal"></span></td>'
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

GrdtdesignAccountTargetObject.prototype.bind = function() {

	$('#grdtdesignAccount-content tr').on(
			'click',
			function() {
				$('#current-grdtdesignAct-id').attr('data-id',
						$(this).closest('tr').attr('data-id'));
			});

	$('#grdtdesignAccount-content td .glyphicon-eye-open')
			.on(
					'click',
					function() {
						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						ajaxAction({
							url : '/api/user/grdtdesignAccount/' + accountId,
							method : 'GET',
						}, {}, {
							callbackHandler : function(msg) {
								var account = msg.data;
								$('#rdtdesignAct-id').val(account.id);
								$('#grdtdesignAct-major-id').val(
										account.major.id);
								$('#grdtdesignAct-major-name').val(
										account.major.name);
								$('#grdtdesignAct-grade').val(account.grade);
								$('#grdtdesignAct-classNum').val(
										account.classNum);
								$('#grdtdesignAct-stuNum').val(account.stuNum);
								$('#grdtdesignAct-weekNum')
										.val(account.weekNum);
								$('#grdtdesignAct-factor').val(account.factor);
								$('#grdtdesignAct-workload').val(
										account.workload);
								$('#grdtdesignAct-period').val(account.period);
								$('#grdtdesignAct-campus').val(account.campus);
								$('#grdtdesignAct-remark').val(account.remark);
							},
							exceptionHandler : function(XMLHttpRequest,
									textStatus, errorThrown) {
								alert('服务器忙!');
							}
						});
					});
	//
	$('#grdtdesignAccount-content td .glyphicon-fullscreen')
			.on(
					'click',
					function() {
						$('#grdtdesignActTea-content').empty();
						var accountId = $(this).closest('tr').attr('data-id');

						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						ajaxAction(
								{
									url : '/api/user/grdtdesignAccount/'
											+ accountId
											+ '/grdtdesignActTeachers',
									method : 'GET',
								},
								{},
								{
									callbackHandler : function(data) {

										$
												.each(
														data.grdtdesignActTeachers,
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
																	'#grdtdesignActTea-content')
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

						var grdtdesignActId = $('#current-grdtdesignAct-id')
								.attr('data-id');
						if (grdtdesignActId == '') {
							alert('请选择毕业设计工作量!');
							return false;
						}

						var userId = $(this).closest('tr').attr('data-id');
						var userName = $(this).closest('tr').children('td').eq(
								1).html();
						// 过来重选
						var isIn = false;
						$('#grdtdesignActTea-content').find('tr').each(
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
						var index = $("#grdtdesignActTea-content tr").length;
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
						$('#grdtdesignActTea-content').append(html);
						bindEditTable();
						bindDeleteEditTable();
						$('#userModal').modal('hide')
					});

};
