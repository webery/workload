package dlnu.workload.module.common.model;

import java.util.Calendar;

public class SemesterUtil {

	/**
	 * 生成当前学期字符串工具(日期格式的字符串)
	 * 
	 * @return
	 */
	public static synchronized String getSemester() {

		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		if (month > 1 && month < 8) {
			return year + "-02-01";
		} else {
			return year + "-08-01";
		}
	}

	public static void main(String[] args) {

		System.out.println(SemesterUtil.getSemester());

	}
}
