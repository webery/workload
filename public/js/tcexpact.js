$(function() {
	$('#btn-query-exp').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TargetObject(
						'#experiment-page', '#experiment-content',
						'/api/user/1234567890/college/experiments'), 'page');
				page.pageCallBack('1');
			});
	$('#expAct-form').submit(function(e) {

		var expId = $('#expAct-exp-id').val();
		if (expId.length != 24) {
			alert('请选择实验!');
			e.preventDefault();
			return false;
		}

		$("#expAct-submit").attr("disabled", true);
		$('#expAct-form').ajaxSubmit({
			url : '/api/user/1234567890/expAccount/',
			type : 'POST',
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
});

var TargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.experiments.pageList,
						function(index, experiment) {
							var createDate = experiment.createDate ? dateFormat
									.format(new Date(experiment.createDate))
									: '';
							var operation = experiment.status == 0 ? '<a data-oprt="unlock" class="btn btn-info btn-xs">解锁</a>'
									: '<a data-oprt="lock" class="btn btn-info btn-xs">锁住</a>';
							var html = '<tr data-id="'
									+ experiment.id
									+ '">'
									+ '<td>'
									+ experiment.name
									+ '</td>'
									+ '<td>'
									+ experiment.college.name
									+ '</td>'
									+ '<td>'
									+ createDate
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-copy" aria-hidden="true" data-toggle="modal" data-target="#experimentModal"></span></td>'
									+ '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {
		var searchKeyWord = $('#search-exp').val();

		if (searchKeyWord.length == 0) {
			searchKeyWord = null;
		}
		var paramData = {
			name : searchKeyWord,
		};

		return paramData;
	}

	this.paramData = this.getParamData();

}

TargetObject.prototype.bind = function() {

	$('#experiment-content td .glyphicon-copy').on('click', function() {
		var expId = $(this).closest('tr').attr('data-id');
		var expName = $(this).closest('tr').find('td').eq(0).text();
		$('#expAct-exp-id').val(expId);
		$('#expAct-exp-name').val(expName);
		
		$('#experimentModal').modal('hide');
	});
}
