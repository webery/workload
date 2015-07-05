$(function() {

	//
	loadCollege();
	loadTeacherTitle();

	//
	$('#user-form').submit(function(e) {
		$("#user-submit").attr("disabled", true);
		$('#user-form').ajaxSubmit({
			url : '/api/user',
			type : 'POST',
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
