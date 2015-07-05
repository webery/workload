$(function() {
	//
	loadMajor();
	//
	$('#cdesignAccount-form').submit(function(e) {
		$("#cdesignAccount-submit").attr("disabled", true);
		$('#cdesignAccount-form').ajaxSubmit({
			url : '/api/user/cdesignAccount',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				$("#cdesignAccount-submit").attr("disabled", false);
				alert(data.message)
			},
			error : function() {
				$("#cdesignAccount-submit").attr("disabled", false);
				alert('添加失败！服务器异常!');
			},
		});
		e.preventDefault();
		return false;
	});
	//
	$('#query-cdesign').on(
			'click',
			function() {
				var page = new AjaxPageParser(new CourseDesignTargetObject(
						'#courseDesign-page', '#courseDesign-content',
						'/api/user/1234567890/college/courseDesigns'), 'page');
				page.pageCallBack('1');
			});

});

var CourseDesignTargetObject = function(dom, pageContainer, url) {
	this.dom = dom;
	this.pageContainer = pageContainer;
	this.url = url;
	this.paramData = null;

	this.pageInfoParser = function(jsonPageInfo) {

		var pageContainer = this.pageContainer;

		$
				.each(
						jsonPageInfo.courseDesigns.pageList,
						function(index, courseDesign) {
							var html = '<tr data-id="'
									+ courseDesign.id
									+ '">'
									+ '<td>'
									+ courseDesign.name
									+ '</td>'
									+ '<td>'
									+ courseDesign.college.name
									+ '</td>'
									+ '<td><span class="glyphicon glyphicon-copy" aria-hidden="true" data-toggle="modal" data-target="#courseDesignModal"></span></td>'
									+ '</tr>';
							$(pageContainer).append(html);
						});// $.each
		// 绑定事件
		this.bind();
	}

	this.getParamData = function() {
		var searchKeyWord = $('#search-cdesign').val();

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

CourseDesignTargetObject.prototype.bind = function() {

	$('td .glyphicon-copy').on('click', function() {
		var courseDesignId = $(this).closest('tr').attr('data-id');
		var courseName = $(this).closest('tr').find('td').first().html();
		$('#cdesignAct-courseDesign-id').val(courseDesignId);
		$('#cdesignAct-courseDesign-name').val(courseName);

		$('#courseDesignModal').modal('hide');

	});
};
//
function loadMajor() {
	ajaxAction({
		url : '/api/user/major',
		method : 'GET',
	}, {

	}, {
		callbackHandler : function(msg) {
			//$('#major-id').val(data.id);
			var data = msg.data;
			$('#cdesignAct-major-name').val(data.name);
		},
		exceptionHandler : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('服务器忙!');
		}
	});
}

