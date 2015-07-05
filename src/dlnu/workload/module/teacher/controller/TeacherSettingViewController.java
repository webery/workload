package dlnu.workload.module.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherSettingViewController {

	/**
	 * 教师个人设置主页
	 * 
	 * @param teacherId
	 * @return
	 */
	@RequestMapping("/{teacherId}/setting")
	public String setting(@PathVariable(value = "teacherId") String teacherId) {

		return "teacher/setting/setting";
	}

	/**
	 * 教师个人设置密码
	 * 
	 * @param teacherId
	 * @return
	 */
	@RequestMapping("/{teacherId}/setting/password")
	public String setPassword(
			@PathVariable(value = "teacherId") String teacherId) {

		return "teacher/setting/password";
	}

}
