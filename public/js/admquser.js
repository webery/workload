$(function() {

	//
	loadCollege();
	loadTeacherTitle();

	// btn-q-user
	$('#btn-q-user').on(
			'click',
			function() {
				var page = new AjaxPageParser(new UserTargetObject(
						'#user-page', '#user-content', '/api/users'), 'page');
				page.pageCallBack('1');
			});
	//
	$('#user-form').submit(function(e) {

		var userId = $('#user-id').val();

		$("#user-submit").attr("disabled", true);
		$('#user-form').ajaxSubmit({
			url : '/api/user/' + userId,
			type : 'PUT',
			dataType : 'json',
			success : function(data) {
				$("#user-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#user-submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
});

var UserTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.users.pageList,
						function(index, user) {

							var html = '<tr data-id="'
									+ user.id
									+ '">'
									+ '<td>'
									+ user.id
									+ '</td>'
									+ '<td>'
									+ user.name
									+ '</td>'
									+ '<td>'
									+ user.teacherTitle.name
									+ '</td>'
									+ '<td>'
									+ user.college.name
									+ '</td>'
									+ '<td>'
									+ user.major.name
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-eye-open"aria-hidden="true" data-toggle="modal" data-target="#userModal"></span></td>'
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

}

UserTargetObject.prototype.bind = function() {

	$('td .glyphicon-eye-open').on(
			'click',
			function() {
				var userId = $(this).closest('tr').attr('data-id');
				ajaxAction({
					url : '/api/user/' + userId,
					method : 'GET',
				}, {

				}, {
					callbackHandler : function(msg) {
						var user = msg.data;
						$('#user-id').val(user.id);
						$('#user-name').val(user.name);
						$('#user-college').val(user.college.id);
						$('#user-teacherTitle').val(user.teacherTitle.id);
						$('#user-workload').val(user.workload);
						$('#user-price').val(user.price);
						$('#user-remark').val(user.remark);
						loadMajorByClgAndSetMajor(user.college.id,
								user.major.id);
					},
					exceptionHandler : function(XMLHttpRequest, textStatus,
							errorThrown) {
						alert('服务器忙!');
					}
				});
			});

};
//
function loadCollege() {

	ajaxAction({
		url : '/api/colleges',
		method : 'GET'
	}, {}, {
		callbackHandler : function(msg) {
			var colleges = msg.data;
			$('#user-college').empty();
			$.each(colleges, function(index, college) {
				var html = '<option value="' + college.id + '">' + college.name
						+ '</option>';
				$('#user-college').append(html);
			});
			$("#user-college").change(function() {
				loadMajorByClg($(this).val());
			});
			loadMajorByClg($("#user-college").val());
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}

function loadMajorByClg(collegeId) {

	ajaxAction({
		url : '/api/college/' + collegeId + '/majors',
		method : 'GET'
	}, {}, {
		callbackHandler : function(msg) {
			var majors = msg.data;
			$('#user-major').empty();
			$.each(majors, function(index, major) {
				var html = '<option value="' + major.id + '">' + major.name
						+ '</option>';
				$('#user-major').append(html);
			});
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}

function loadMajorByClgAndSetMajor(collegeId, majorId) {

	ajaxAction({
		url : '/api/college/' + collegeId + '/majors',
		method : 'GET'
	}, {}, {
		callbackHandler : function(msg) {
			var majors = msg.data;
			$('#user-major').empty();
			$.each(majors, function(index, major) {
				var html = '<option value="' + major.id + '">' + major.name
						+ '</option>';
				$('#user-major').append(html);
			});
			$('#user-major').val(majorId);
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}

function loadTeacherTitle() {
	ajaxAction({
		url : '/api/teacherTitles',
		method : 'GET'
	}, {}, {
		callbackHandler : function(msg) {
			var teacherTitles = msg.data;
			$('#user-teacherTitle').empty();
			$.each(teacherTitles, function(index, teacherTitle) {
				var html = '<option value="' + teacherTitle.id + '">'
						+ teacherTitle.name + '</option>';
				$('#user-teacherTitle').append(html);
			});
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}
