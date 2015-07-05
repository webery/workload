package dlnu.workload.module.teacher.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/cdesign")
public class TeacherCourseDesignViewController {

	private static Logger logger = Logger
			.getLogger(TeacherCourseDesignViewController.class);

	@RequestMapping("/create")
	public String create() {

		logger.debug("create");

		return "teacher/coursedesign/create";
	}
	
	@RequestMapping("/query")
	public String query() {

		logger.debug("query");

		return "teacher/coursedesign/query";
	}
}
