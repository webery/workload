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

function loadProjectActs() {

	var page = new AjaxPageParser(new ProjectAccountTargetObject(
			'#projectAct-page', '#projectAct-content',
			'/api/user/1234567890/major/projectAccounts'), 'page');
	page.pageCallBack('1');

}

//
function initSemester() {
	var thisYear = new Date().getFullYear();
	$('#search-semester').val(thisYear);
	generateSemester(thisYear);
}

function generateSemester(year) {
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
								+ '<td><a class="btn btn-info glyphicon glyphicon-download-alt" href="/user/1234567890/major/cdesignAccounts/excel?type=previous&semester='
								+ semester.id + '"></a></td>' + '</tr>';
						$('#semester-content').append(html);
					});
	// bind

	$('#semester-content td .glyphicon-eye-open').on(
			'click',
			function() {
				$('#grdtdesignActTea-content').empty();
				var semester = $(this).closest('tr').attr('data-id');
				var target = new ProjectAccountTargetObject('#projectAct-page',
						'#projectAct-content',
						'/api/user/1234567890/major/projectAccounts');
				var page = new AjaxPageParser(target, 'page');
				target.paramData.semester = semester;
				page.pageCallBack('1');
			});
}

var ProjectAccountTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$.each(jsonPageInfo.projectAccounts.pageList, function(index, account) {

			var html = '<tr data-id="' + account.id + '">' + '<td>'
					+ getSemesterName(account.semester) + '</td>' + '<td>'
					+ account.year + '</td>' + '<td>' + account.creator.name
					+ '</td>' + '<td>' + account.spcode + '</td>' + '<td>'
					+ account.sworkload + '</td>' + '<td>' + account.mpcode
					+ '</td>' + '<td>' + account.mworkload + '</td>' + '<td>'
					+ account.mrworkload + '</td>' + '<td>' + account.remark
					+ '</td>'
					// + '<td><span class="glyphicon glyphicon-eye-open"
					// aria-hidden="true" data-toggle="modal"
					// data-target="#projectActModal"></span></td>'
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

ProjectAccountTargetObject.prototype.bind = function() {

	$('#projectAct-content td .glyphicon').on(
			'click',
			function() {
				$('#current-projectAct-id').attr('data-id',
						$(this).closest('tr').attr('data-id'));
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
