$(function() {
	loadMajor();
	//
	$('#grdtdesignAccount-form').submit(function(e) {
		$("#grdtdesignAccount-submit").attr("disabled", true);
		$('#grdtdesignAccount-form').ajaxSubmit({
			url : '/api/user/grdtdesignAccount',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#grdtdesignAccount-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#grdtdesignAccount-submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});

});

function loadMajor() {
	ajaxAction({
		url : '/api/user/major',
		method : 'GET',
	}, {

	}, {
		callbackHandler : function(msg) {
			var data = msg.data;
			$('#grdtdesignAct-major-id').val(data.id);
			$('#grdtdesignAct-major-name').val(data.name);
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});

}