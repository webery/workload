$(function() {

	$('#btn-query-exp').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TargetObject('#page',
						'#experiment-content',
						'/api/user/1234567890/college/experiments'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#experiment-form').submit(function(e) {

		var experimentId = $('#current-exp-id').attr('data-id');

		if (typeof (experimentId) == 'undefined' || experimentId.length != 24) {
			return false;
		}

		$("#experiment-submit").attr("disabled", true);
		$('#experiment-form').ajaxSubmit({
			url : '/api/user/1234567890/experiment/' + experimentId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#experiment-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#experiment-submit").attr("disabled", false);
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
									+ '<td>'
									+ experiment.creator.name
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#experimentModal"></span></td>'
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

	$('#experiment-content td').on('click', function() {
		var experimentId = $(this).closest('tr').attr('data-id');
		$('#current-exp-id').attr('data-id', experimentId);
	});

	$('#experiment-content td .glyphicon-eye-open').on(
			'click',
			function() {
				var experimentId = $(this).closest('tr').attr('data-id');
				if (typeof (experimentId) == 'undefined'
						|| experimentId.length != 24) {
					return false;
				}
				ajaxAction({
					url : '/api/experiment/' + experimentId,
					method : 'GET',
				}, {

				}, {
					callbackHandler : function(msg) {
						var data = msg.data;
						$('#name').val(data.name);
						$('#remark').val(data.remark);
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});
			});
	//
	$('#experiment-content td a.btn-info').on('click', function() {
	});
}
//
