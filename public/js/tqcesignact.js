$(function() {
	initSemester();
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
				$('#cdesignActTea-content').empty();
				var semester = $(this).closest('tr').attr('data-id');
				var target = new CdesignAccountTargetObject(
						'#cdesignAccount-page', '#cdesignAccount-content',
						'/api/user/cdesignAccounts');
				var page = new AjaxPageParser(target, 'page');
				target.paramData.semester = semester;
				page.pageCallBack('1');
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
									+ '<td><span class="glyphicon glyphicon-eye-open" aria-hidden="true" data-toggle="modal" data-target="#cdesignAccountModal"></span></td>'
									+ '</tr>';

							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {

		var paramData = {
			type : 'previous'
		};

		return paramData;
	}

	this.paramData = this.getParamData();

}

CdesignAccountTargetObject.prototype.bind = function() {

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
																	+ '</tr>';
															$(
																	'#cdesignActTea-content')
																	.append(
																			html);
														});
									},
									exceptionHandler : function(XMLHttpRequest,
											textStatus, errorThrown) {
										alert('操作!');
									}
								});
					});
}
//