$(function() {
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
				var semester = $(this).closest('tr').attr('data-id');
				var target = new CourseAccountTargetObject(
						'#courseAccount-page', '#courseAccount-content',
						'/api/user/courseAccounts');
				var page = new AjaxPageParser(target, 'page');
				target.paramData.semester = semester;
				page.pageCallBack('1');
			});
}

var CourseAccountTargetObject = function(dom, pageContainer, url) {
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
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#courseAccountModal"></span></td>'
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

CourseAccountTargetObject.prototype.bind = function() {

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