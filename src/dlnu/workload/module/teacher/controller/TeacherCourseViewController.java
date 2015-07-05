package dlnu.workload.module.teacher.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/course")
public class TeacherCourseViewController {

	private static Logger logger = Logger
			.getLogger(TeacherCourseViewController.class);

	@RequestMapping("/create")
	public String create() {

		logger.debug("create");

		return "teacher/course/create";
	}

	@RequestMapping("/query")
	public String query() {

		logger.debug("query");

		return "teacher/course/query";
	}
}
