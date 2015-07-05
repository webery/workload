$(function() {

	$('#btn-query-semester')
			.on(
					'click',
					function() {
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

						$('#semester-content').empty();
						$
								.each(
										generateSemestersByYear(year),
										function(index, semester) {
											var html = '<tr data-id="'
													+ semester.id
													+ '">'
													+ '<td>'
													+ semester.name
													+ '</td>'
													+ '<td><span class="glyphicon glyphicon-eye-open"></span></td>'
													+ '</tr>';
											$('#semester-content').append(html);
										});
						// bind

						$('#semester-content td .glyphicon-eye-open')
								.on(
										'click',
										function() {
											var semester = $(this)
													.closest('tr').attr(
															'data-id');
											var target = new GrdtdesignAccountTargetObject(
													'#grdtdesignAccount-page',
													'#grdtdesignAccount-content',
													'/api/user/1234567890/college/grdtdesignAccounts');
											var page = new AjaxPageParser(
													target, 'page');
											target.paramData.semester = semester;
											page.pageCallBack('1');
										});

					});

});

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
			type : 'previous'
		};

		return paramData;
	}

	this.paramData = this.getParamData();

}

GrdtdesignAccountTargetObject.prototype.bind = function() {
	
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
															+ '</tr>';
													$(
															'#grdtdesignActTea-content')
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
};

//
