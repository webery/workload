/**
 * 返回网页前面插件 
 * weber 2014/12/25
 * 需要引用returnToTop.css样式
 */
;
$(function() {
	$.fn.returnToTop = function(options) {
		
		var defaults = {
			showHeight : 150,//显示的高度
			speed : 1000//返回顶端的速度
		};
		var options = $.extend(defaults, options);
		$("body").prepend("<div id='returnToTop'><a></a></div>");
		var $returnToTop = $(this);
		var $top = $("#returnToTop");
		var $top_a = $("#returnToTop a");
		$returnToTop.scroll(function() {
			var scrolltop = $(this).scrollTop();
			if (scrolltop >= options.showHeight) {
				$top.show();
			} else {
				$top.hide();
			}
		});
		$top_a.hover(function() {
			$(this).addClass("cur");
		}, function() {
			$(this).removeClass("cur");
		});
		$top.click(function() {
			$("html,body").animate({
				scrollTop : 0
			}, options.speed);
		});
	}

});