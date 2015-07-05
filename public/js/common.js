//日期格式化工具
function Format() {
	this.jsjava_class = "jsjava.text.Format";
}

function DateFormat() {
	this.jsjava_class = "jsjava.text.DateFormat";
}

DateFormat.prototype = new Format();
DateFormat.prototype.constructor = DateFormat;

DateFormat.zh_cn_month2 = [ "01", "02", "03", "04", "05", "06", "07", "08",
		"09", "10", "11", "12" ];
DateFormat.zh_cn_month3 = [ "\u4e00\u6708", "\u4e8c\u6708", "\u4e09\u6708",
		"\u56db\u6708", "\u4e94\u6708", "\u516d\u6708", "\u4e03\u6708",
		"\u516b\u6708", "\u4e5d\u6708", "\u5341\u6708", "\u5341\u4e00\u6708",
		"\u5341\u4e8c\u6708", ];
DateFormat.zh_cn_month4 = [ "\u4e00\u6708", "\u4e8c\u6708", "\u4e09\u6708",
		"\u56db\u6708", "\u4e94\u6708", "\u516d\u6708", "\u4e03\u6708",
		"\u516b\u6708", "\u4e5d\u6708", "\u5341\u6708", "\u5341\u4e00\u6708",
		"\u5341\u4e8c\u6708", ];
DateFormat.us_en_month4 = [ "Janu", "Febr", "Marc", "Apri", "May", "Juhn",
		"July", "Augu", "Sept", "Octo", "Nove", "Dece" ];
DateFormat.us_en_month3 = [ "Jan", "Feb", "Mar", "Apr", "May", "Juh", "Jul",
		"Aug", "Sep", "Oct", "Nov", "Dec" ];
DateFormat.us_en_month2 = [ "01", "02", "03", "04", "05", "06", "07", "08",
		"09", "10", "11", "12" ];
DateFormat.zh_cn_week = [ "\u661f\u671f\u65e5", "\u661f\u671f\u4e00",
		"\u661f\u671f\u4e8c", "\u661f\u671f\u4e09", "\u661f\u671f\u56db",
		"\u661f\u671f\u4e94", "\u661f\u671f\u516d" ];
DateFormat.zh_cn_am = "\u4e0b\u5348";
DateFormat.zh_cn_pm = "\u4e0a\u5348";
DateFormat.language = (navigator.userLanguage == undefined ? navigator.language
		: navigator.userLanguage).replace("-", "_").toLowerCase();
DateFormat.prototype.format = function(date) {
	var year4 = date.getFullYear();
	var year2 = year4.toString().substring(2);
	var pattern = this.pattern;
	pattern = pattern.replace(/yyyy/, year4);
	pattern = pattern.replace(/yy/, year2);
	var month = date.getMonth();
	pattern = pattern.replace(/MMMM/, eval("DateFormat." + DateFormat.language
			+ "_month4[month]"));
	pattern = pattern.replace(/MMM/, eval("DateFormat." + DateFormat.language
			+ "_month3[month]"));
	pattern = pattern.replace(/MM/, eval("DateFormat." + DateFormat.language
			+ "_month2[month]"));
	var dayOfMonth = date.getDate();
	var dayOfMonth2 = dayOfMonth;
	var dayOfMonthLength = dayOfMonth.toString().length;
	if (dayOfMonthLength == 1) {
		dayOfMonth2 = "0" + dayOfMonth;
	}
	pattern = pattern.replace(/dd/, dayOfMonth2);
	pattern = pattern.replace(/d/, dayOfMonth);
	var hours = date.getHours();
	var hours2 = hours;
	var hoursLength = hours.toString().length;
	if (hoursLength == 1) {
		hours2 = "0" + hours;
	}
	pattern = pattern.replace(/HH/, hours2);
	pattern = pattern.replace(/H/, hours);
	var minutes = date.getMinutes();
	var minutes2 = minutes;
	var minutesLength = minutes.toString().length;
	if (minutesLength == 1) {
		minutes2 = "0" + minutes;
	}
	pattern = pattern.replace(/mm/, minutes2);
	pattern = pattern.replace(/m/, minutes);
	var seconds = date.getSeconds();
	var seconds2 = seconds;
	var secondsLength = seconds.toString().length;
	if (secondsLength == 1) {
		seconds2 = "0" + seconds;
	}
	pattern = pattern.replace(/ss/, seconds2);
	pattern = pattern.replace(/s/, seconds);
	var milliSeconds = date.getMilliseconds();
	pattern = pattern.replace(/S+/, milliSeconds);
	var day = date.getDay();
	pattern = pattern.replace(/E+/, eval("DateFormat." + DateFormat.language
			+ "_week[day]"));
	if (hours > 12) {
		pattern = pattern.replace(/a+/, eval("DateFormat."
				+ DateFormat.language + "_am"));
	} else {
		pattern = pattern.replace(/a+/, eval("DateFormat."
				+ DateFormat.language + "_pm"));
	}
	var kHours = hours;
	if (kHours == 0) {
		kHours = 24;
	}
	var kHours2 = kHours;
	var kHoursLength = kHours.toString().length;
	if (kHoursLength == 1) {
		kHours2 = "0" + kHours;
	}
	pattern = pattern.replace(/kk/, kHours2);
	pattern = pattern.replace(/k/, kHours);
	var KHours = hours;
	if (hours > 11) {
		KHours = hours - 12;
	}
	var KHours2 = KHours;
	var KHoursLength = KHours.toString().length;
	if (KHoursLength == 1) {
		KHours2 = "0" + KHours;
	}
	pattern = pattern.replace(/KK/, KHours2);
	pattern = pattern.replace(/K/, KHours);
	var hHours = KHours;
	if (hHours == 0) {
		hHours = 12;
	}
	var hHours2 = hHours;
	var hHoursLength = hHours.toString().length;
	if (KHoursLength == 1) {
		hHours2 = "0" + hHours;
	}
	pattern = pattern.replace(/hh/, hHours2);
	pattern = pattern.replace(/h/, hHours);
	return pattern;
};
function SimpleDateFormat() {
	this.jsjava_class = "jsjava.text.SimpleDateFormat";
}
SimpleDateFormat.prototype = new DateFormat();
SimpleDateFormat.prototype.constructor = SimpleDateFormat;
SimpleDateFormat.prototype.applyPattern = function(pattern) {
	this.pattern = pattern;
};
var dateFormat = new SimpleDateFormat();
dateFormat.applyPattern("yyyy-MM-dd");
// 日期格式化工具end

// semester
function getSemester() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();

	if (month > 1 && month < 8) {
		return year + "春季期";
	} else {
		return year + "秋季期";
	}
}
//
function getSemesterList() {

	var semesters = {};
	var now = new Date();
	var year = now.getFullYear();

	var start = year - 5;
	var end = year;

	for (var index = start; index < end; index++) {
		semesters.index = index + "春季期";
	}

	return semesters;
}
function getSemesterName(semester) {
	var year = semester.substring(0, 4);
	if (semester.indexOf('02-01') != -1) {
		year += "春季期";
	} else {
		year += "秋季期";
	}

	return year;
}

function generateSemestersByYear(year) {
	var first = year + '-02-01';
	var second = year + '-08-01';
	var semesters = new Array(2);

	semesters[0] = {
		'id' : first,
		'name' : year + "春季期"
	};
	semesters[1] = {
		'id' : second,
		'name' : year += "秋季期"
	};

	return semesters;
}