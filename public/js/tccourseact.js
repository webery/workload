$(function() {

	// course select
	$('#query-course').on(
			'click',
			function() {
				var page = new AjaxPageParser(new CourseTargetObject(
						'#course-page', '#course-content',
						'/api/user/1234567890/college/courses'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#courseactModal').on('show.bs.modal', function(e) {
		// loadsemester();
		if ($('#semester').children().length < 1) {
			return false;
		}
		loadSemester();
	})
	//
	$('#courseact-form').submit(function(e) {

		var courseId = $('#course-id').val();
		if (courseId == '') {
			alert('请选择课程!');
			e.preventDefault();
			return false;
		}

		$("#courseact-submit").attr("disabled", true);
		$('#courseact-form').ajaxSubmit({
			url : '/api/user/courseAccount',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#courseact-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#courseact-submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});

});

//
function loadSemester() {

	ajaxAction({
		url : '/api/user/semesters/',
		method : 'GET'
	}, {}, {
		callbackHandler : function(data) {
			alert(data)
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});

}

var CourseTargetObject = function(dom, pageContainer, url) {
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
									+ createDate
									+ '</td>'
									+ '<td>'
									+ '<span class="glyphicon glyphicon-copy" aria-hidden="true"></span>'
									+ '</td>' + '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {
		var searchKeyWord = $('#search-course').val();

		if (searchKeyWord.length == 0) {
			searchKeyWord = null;
		} else if (searchKeyWord.length > 10) {
			alert('输入查询关键字不能超过十个字!');
			return false;
		}
		var paramData = {
			name : searchKeyWord,
		};

		return paramData;
	}

	this.paramData = this.getParamData();

}

CourseTargetObject.prototype.bind = function() {
	$('#course-content td .glyphicon-copy').on('click', function() {

		var courseId = $(this).closest('tr').attr('data-id');
		var courseName = $(this).closest('tr').find('td').first().html();

		$('#course-id').val(courseId);
		$('#course-name').val(courseName);

		$('#courseModal').modal('hide')
	});
};
//
