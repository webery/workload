$(function() {
	//
	loadExpActs();
	//
	$('#expAct-form').submit(function(e) {

		var accountId = $('#current-expAct-id').attr('data-id');

		$("#expAct-submit").attr("disabled", true);
		$('#expAct-form').ajaxSubmit({
			url : '/api/user/1234567890/expAccount/' + accountId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#expAct-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#expAct-submit").attr("disabled", false);
				alert('修改失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
	//
	$('#btn-save-expItems').on('click', function() {
		var accountId = $('#current-expAct-id').attr('data-id');
		if (accountId.length != 24) {
			alert('请选择实验工作量!');
			return false;
		}

		var vals = new Array();
		var index = 0;

		$('#expItem-content').find('tr').each(function(index, item) {

			var val = {};
			val.student = $(this).find("td").eq(0).text();
			val.period = $(this).find("td").eq(1).text();
			val.operiod = $(this).find("td").eq(2).text();
			val.courseNum = $(this).find("td").eq(3).text();
			val.courseRepnum = $(this).find("td").eq(4).text();
			val.classStunum = $(this).find("td").eq(5).text();
			val.classTime = $(this).find("td").eq(6).text();
			val.factor = $(this).find("td").eq(7).text();
			val.workload = $(this).find("td").eq(8).text();
			vals[index++] = val;
		});
		//
		$.ajax({
			type : 'POST',
			url : '/api/user/1234567890/expAccount/' + accountId + '/expItems',
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(vals),
			success : function(msg) {
				alert(msg.message)
			}
		});

	});
	//
	$('#btn-save-expActTeas').on(
			'click',
			function() {
				var accountId = $('#current-expAct-id').attr('data-id');
				if (accountId.length != 24) {
					alert('请选择实验工作量!');
					return false;
				}

				var vals = new Array();
				var index = 0;

				$('#expActTea-content').find('tr').each(function(index, item) {

					var val = {
						teacher : {},
					};
					val.teacher.id = $(this).find("td").eq(1).text();
					val.pWorkload = $(this).find("td").eq(3).text();
					val.wWorkload = $(this).find("td").eq(4).text();
					val.cWorkload = $(this).find("td").eq(5).text();
					val.period = $(this).find("td").eq(6).text();

					vals[index++] = val;
				});
				//
				$.ajax({
					type : 'POST',
					url : '/api/user/1234567890/expAccount/' + accountId
							+ '/expActTeachers',
					dataType : "json",
					contentType : "application/json",
					data : JSON.stringify(vals),
					success : function(msg) {
						alert(msg.message)
					}
				});

			});
	$('#btn-create-expItem')
			.on(
					'click',
					function() {

						var accountId = $('#current-expAct-id').attr('data-id');
						if (accountId.length != 24) {
							alert('请选择实验工作量!');
							return false;
						}

						var html = '<tr>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="授课年级">软件</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="计划学时">0</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="金石滩计划学时">0</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="选课人数">0</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="重修人数">0</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="每班人数">0</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="系数"></a>0</td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="教学班次数">0</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="工作量">0</a></td>'
								+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
								+ '</tr>'
						$('#expItem-content').append(html);
						bindEditTable();
						bindDeleteEditTable();
					});
	$('#btn-create-expActTea').on('click', function() {

	});
	//
	$('#btn-q-user').on(
			'click',
			function() {
				var page = new AjaxPageParser(new UserTargetObject(
						'#user-page', '#user-content', '/api/college/users'),
						'page');
				page.pageCallBack('1');
			});
});

function loadExpActs() {

	var page = new AjaxPageParser(new ExpAccountTargetObject('#expAct-page',
			'#expAct-content', '/api/user/1234567890/expAccounts'), 'page');
	page.pageCallBack('1');

}

function bindEditTable() {
	$('#expItem-content tr td:nth-child(1)').editable({
		validate : function(value) {
			if ($.trim(value) == '') {
				return '不能为空!';
			}
			if (value.length > 10) {
				return '10个字!';
			}

		}
	});

	$('#expItem-content tr td:nth-child(2)').editable({
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

	$('#expItem-content tr td:nth-child(3)').editable({
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

	$('#expItem-content tr td:nth-child(4)').editable({
		validate : function(value) {
			if ($.trim(value) == '') {
				return '不能为空!';
			}
			if (/^[1-9]\d*|0$/.test(value)) {
			} else {
				return '请输入整数';
			}

		}
	});
	$('#expItem-content tr td:nth-child(5)').editable({
		validate : function(value) {
			if ($.trim(value) == '') {
				return '不能为空!';
			}
			if (/^[1-9]\d*|0$/.test(value)) {
			} else {
				return '请输入整数';
			}

		}
	});
	$('#expItem-content tr td:nth-child(6)').editable({
		validate : function(value) {
			if ($.trim(value) == '') {
				return '不能为空!';
			}
			if (/^[1-9]\d*|0$/.test(value)) {
			} else {
				return '请输入整数';
			}

		}
	});
	$('#expItem-content tr td:nth-child(7)').editable({
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
	$('#expItem-content tr td:nth-child(8)').editable({
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
	$('#expItem-content tr td:nth-child(9)').editable({
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
}
function bindDeleteEditTable() {
	$('#expItem-content td .glyphicon-trash').on('click', function() {
		$(this).closest('tr').remove();
	});
}

function bindEditTableTea() {
	$('#expActTea-content tr td:nth-child(4)').editable({
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
	$('#expActTea-content tr td:nth-child(5)').editable({
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
	$('#expActTea-content tr td:nth-child(6)').editable({
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
	$('#expActTea-content tr td:nth-child(7)').editable({
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
}
function bindDeleteEditTableTea() {
	$('#expActTea-content td .glyphicon-trash').on('click', function() {
		$(this).closest('tr').remove();
		$('#expActTea-content').find('tr').each(function(index, item) {
			var tds = $(this).find("td");
			tds.eq(0).text(index + 1);
		});
	});
}

var ExpAccountTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.expAccounts.pageList,
						function(index, account) {
							var createDate = account.createDate ? dateFormat
									.format(new Date(account.createDate)) : '';
							var html = '<tr data-id="'
									+ account.id
									+ '">'
									+ '<td>'
									+ getSemesterName(account.semester)
									+ '</td>'
									+ '<td>'
									+ account.experiment.name
									+ '</td>'
									+ '<td>'
									+ account.campus
									+ '</td>'
									+ '<td>'
									+ createDate
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-th-list"></span></td>'
									+ '<td><span class="glyphicon glyphicon-fullscreen"></span></td>'
									+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
									+ '<td><span class="glyphicon glyphicon-eye-open" aria-hidden="true" data-toggle="modal" data-target="#expActModal"></span></td>'
									+ '</tr>';

							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {
			type : 'current',
			semester : '2011-02-01',
		};

		return paramData;
	}

	this.paramData = this.getParamData();

}

ExpAccountTargetObject.prototype.bind = function() {

	$('#expAct-content tr').on(
			'click',
			function() {
				$('#current-expAct-id').attr('data-id',
						$(this).closest('tr').attr('data-id'));
			});
	$('#expAct-content td .glyphicon-th-list')
			.on(
					'click',
					function() {
						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						var page = new AjaxPageParser(new ExpItemTargetObject(
								'#expItem-page', '#expItem-content',
								'/api/expAccount/' + accountId + '/expItems'),
								'page');
						page.pageCallBack('1');
					});
	$('#expAct-content td .glyphicon-fullscreen')
			.on(
					'click',
					function() {
						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						var page = new AjaxPageParser(
								new ExpActTeaTargetObject('#expActTea-page',
										'#expActTea-content',
										'/api/expAccount/' + accountId
												+ '/expActTeachers'), 'page');
						page.pageCallBack('1');
					});
	$('#expAct-content td .glyphicon-trash')
			.on(
					'click',
					function() {
						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						var wait = confirm("删除后工作量全部信息将无法恢复!")
						if (wait == false) {
							return false;
						}
						var target = $(this).closest('tr');
						ajaxAction({
							url : '/api/user/1234567890/expAccount/'
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

	$('#expAct-content td .glyphicon-eye-open')
			.on(
					'click',
					function() {
						var accountId = $(this).closest('tr').attr('data-id');
						if (typeof (accountId) == 'undefined'
								|| accountId.length != 24) {
							return false;
						}
						ajaxAction({
							url : '/api/user/1234567890/expAccount/'
									+ accountId,
							method : 'GET',
						}, {}, {
							callbackHandler : function(msg) {
								var account = msg.data;
								$('#expAct-exp-id').val(account.experiment.id);
								$('#expAct-exp-name').val(
										account.experiment.name);
								$('#expAct-campus').val(account.campus);
								//
								$('#expAct-period').val(account.period);
								$('#expAct-operiod').val(account.operiod);
								$('#expAct-courseNum').val(account.courseNum);
								$('#expAct-courseRepnum').val(
										account.courseRepnum);
								$('#expAct-classStunum').val(
										account.classStunum);
								$('#expAct-workload').val(account.workload);
							},
							exceptionHandler : function(XMLHttpRequest,
									textStatus, errorThrown) {
								alert('服务器忙!');
							}
						});
					});
};
//
var ExpItemTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.expItems.pageList,
						function(index, item) {

							var html = '<tr>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="授课年级">'
									+ item.student
									+ '</a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="计划学时">'
									+ item.period
									+ '</a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="金石滩计划学时">'
									+ item.operiod
									+ '</a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="选课人数">'
									+ item.courseNum
									+ '</a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="重修人数"> '
									+ item.courseRepnum
									+ '</a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="每班人数">'
									+ item.classStunum
									+ '</a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="系数"></a> '
									+ item.classTime
									+ '</td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="教学班次数"> '
									+ item.factor
									+ ' </a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="工作量"> '
									+ item.workload
									+ ' </a></td>'
									+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
									+ '</tr>'

							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {};

		return paramData;
	}

	this.paramData = this.getParamData();

}

ExpItemTargetObject.prototype.bind = function() {
	bindEditTable();
	bindDeleteEditTable();
};
//
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

						var accountId = $('#current-expAct-id').attr('data-id');
						if (accountId == '') {
							alert('请选择实验工作量!');
							return false;
						}

						var userId = $(this).closest('tr').attr('data-id');
						var userName = $(this).closest('tr').children('td').eq(
								1).html();
						// 过来重选
						var isIn = false;
						$('#expActTea-content').find('tr').each(
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
						var index = $("#expActTea-content tr").length;
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
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="承担教学工作量">0</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="工作时间内教学工作量">0</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="核发酬金工作量">0</a></td>'
								+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="金石滩校区计划学时">0</a></td>'
								+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
								+ '</tr>'
						$('#expActTea-content').append(html);
						bindEditTableTea();
						bindDeleteEditTableTea();
						$('#userModal').modal('hide')
					});

};
//
var ExpActTeaTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.expActTeachers.pageList,
						function(index, item) {
							var html = '<tr>' + '<td>'
									+ (index + 1)
									+ '</td>'
									+ '<td>'
									+ item.teacher.id
									+ '</td>'
									+ '<td>'
									+ item.teacher.name
									+ '</td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="承担教学工作量">'
									+ item.pWorkload
									+ '</a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="工作时间内教学工作量">'
									+ item.wWorkload
									+ '</a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="核发酬金工作量">'
									+ item.cWorkload
									+ '</a></td>'
									+ '<td><a href="#" id="test" data-type="text" data-placement="right" data-placeholder="Required" data-title="金石滩校区计划学时">'
									+ item.period
									+ '</a></td>'
									+ '<td><span class="glyphicon glyphicon-trash"></span></td>'
									+ '</tr>'
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {};

		return paramData;
	}

	this.paramData = this.getParamData();

}

ExpActTeaTargetObject.prototype.bind = function() {
	bindEditTableTea();
	bindDeleteEditTableTea();
};