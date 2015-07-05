$(function() {
	//
	$('#projectAct-form').submit(function(e) {
		$("#projectAct-submit").attr("disabled", true);
		$('#projectAct-form').ajaxSubmit({
			url : '/api/user/1234567890/projectAccount',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#projectAct-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#projectAct-submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});

});