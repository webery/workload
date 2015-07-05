$(function() {

	$('#query').on(
			'click',
			function() {
				var page = new AjaxPageParser(new TargetObject('#page',
						'#course-content',
						'/api/user/1234567890/college/courses'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#course-form').submit(function(e) {

		var courseId = $("#id-value").attr('data-id');

		if (typeof (courseId) == 'undefined' || courseId.length != 24) {
			return false;
		}

		$("#submit").attr("disabled", true);
		$('#course-form').ajaxSubmit({
			url : '/api/user/1234567890/course/' + courseId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#submit").attr("disabled", false);
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
						jsonPageInfo.courses.pageList,
						function(index, course) {
							var createDate = course.createDate ? dateFormat
									.format(new Date(course.createDate)) : '';
							var operation = course.status == 0 ? '<a data-oprt="unlock" class="btn btn-info btn-xs">解锁</a>'
									: '<a data-oprt="lock" class="btn btn-info btn-xs">锁住</a>';
							var html = '<tr data-id="'
									+ course.id
									+ '">'
									+ '<td>'
									+ course.name
									+ '</td>'
									+ '<td>'
									+ course.college.name
									+ '</td>'
									+ '<td>'
									+ operation
									+ '</td>'
									+ '<td>'
									+ createDate
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#courseModal"></span></td>'
									+ '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {
		var searchKeyWord = $('#search-course').val();

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

	$('td .glyphicon-eye-open').on(
			'click',
			function() {
				var courseId = $(this).closest('tr').attr('data-id');
				if (typeof (courseId) == 'undefined' || courseId.length != 24) {
					return false;
				}
				ajaxAction({
					url : '/api/course/' + courseId,
					method : 'GET',
				}, {

				}, {
					callbackHandler : function(data) {
						if (typeof (data.message) != 'undefined') {
							alert(data.message);
							return false;
						}
						$('#id-value').attr('data-id', data.id);
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
	$('td a.btn-info').on(
			'click',
			function() {

				var courseId = $(this).closest('tr').attr('data-id');
				if (typeof (courseId) == 'undefined' || courseId.length != 24) {
					return false;
				}

				var btnoprt = $(this);
				var operation = btnoprt.attr('data-oprt');

				ajaxAction({
					url : '/api/course/' + courseId + '/status',
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
						operation = operation == 'lock' ? 'unlock' : 'lock';
						btnoprt.attr('data-oprt', operation);
						btnoprt.html(show);
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('操作!');
					}
				});
			});
}
//
