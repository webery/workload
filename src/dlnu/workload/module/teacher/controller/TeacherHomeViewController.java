package dlnu.workload.module.teacher.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 教师主页页面控制器
 * 
 * @author weber
 *
 */
@Controller
@RequestMapping("/teacher")
public class TeacherHomeViewController {

	private static Logger logger = Logger
			.getLogger(TeacherHomeViewController.class);

	@RequestMapping("/home")
	public String home() {

		logger.debug("home ");

		return "teacher/home";
	}

}
