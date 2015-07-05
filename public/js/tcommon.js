$(function() {
	urlRouter();
});

function urlRouter() {

	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： proj/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPath = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/proj
	var viewIndex = pathName.indexOf('/teacher');
	if (viewIndex == -1) {
		return false;
	}
	var path = pathName.substring(viewIndex + 9);
	var type = path.substring(0, path.indexOf('/'));
	if (path.indexOf('act') != -1) {
		type = type.substring(0, type.length - 3);
	}
	if(type[0] == 'm') {
		type = type.substring(1, type.length);
	}
	
	$('#' + type).addClass('in');
}
