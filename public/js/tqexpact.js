$(function() {
	//
	// loadExpActs();
	initSemester();
	//
	$('#btn-query-semester').on('click', function() {
		var year = $('#search-semester').val();
		var thisYear = new Date().getFullYear();
		if (/^((?:19|20)\d\d)/.test(year)) {
		} else {
			alert('请输入正确的年格式,例如2015');
			return false;
		}
		if (year > thisYear) {
			alert('查询年不能大于当前年!');
			return false;
		}
		generateSemester(year);

	});
});
//
function initSemester() {
	var thisYear = new Date().getFullYear();
	$('#search-semester').val(thisYear);
	generateSemester(thisYear);
}
function generateSemester(year) {
	$('#semester-content').empty();
	$.each(generateSemestersByYear(year), function(index, semester) {
		var html = '<tr data-id="' + semester.id + '">' + '<td>'
				+ semester.name + '</td>'
				+ '<td><span class="glyphicon glyphicon-eye-open"></span></td>'
				+ '</tr>';
		$('#semester-content').append(html);
	});
	// bind

	$('#semester-content td .glyphicon-eye-open').on(
			'click',
			function() {
				$('#expActTea-content').empty();
				var semester = $(this).closest('tr').attr('data-id');
				var target = new ExpAccountTargetObject('#expAct-page',
						'#expAct-content', '/api/user/1234567890/expAccounts');
				var page = new AjaxPageParser(target, 'page');
				target.paramData.semester = semester;
				page.pageCallBack('1');
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