$(function() {
	//
	$('#experiment-form').submit(function(e) {
		$("#experiment-submit").attr("disabled", true);
		$('#experiment-form').ajaxSubmit({
			url : '/api/user/1234567890/experiment',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#experiment-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#experiment-submit").attr("disabled", false);
				alert('添加失败!网络忙!');
			},
		});
		e.preventDefault();
		return false;
	});

});