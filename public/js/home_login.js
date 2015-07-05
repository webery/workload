$(function() {

	// 登陆
	$('#login-form').submit(function(e) {

		// $("#login-submit").attr("disabled", true);
		$('#login-form').ajaxSubmit({
			url : '/api/user/login',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				// $("#login-submit").attr("disabled", false);
				window.location.href = "/teacher/1111";
			},
			error : function() {
				// $("#login-submit").attr("disabled", false);
				alert('登陆失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});

});
