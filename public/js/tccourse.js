$(function() {
	//
	$('#course-form').submit(function(e) {
		$("#submit").attr("disabled", true);
		$('#course-form').ajaxSubmit({
			url : '/api/user/1234567890/course',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});

});