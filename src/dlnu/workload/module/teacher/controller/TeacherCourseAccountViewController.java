package dlnu.workload.module.teacher.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherCourseAccountViewController {

	private static Logger logger = Logger
			.getLogger(TeacherCourseAccountViewController.class);

	@RequestMapping("/courseact/create")
	public String create() {

		logger.debug("create");

		return "teacher/courseact/create";
	}

	@RequestMapping("/courseact/current")
	public String current() {

		logger.debug("query");

		return "teacher/courseact/current";
	}

	@RequestMapping("/courseact/query")
	public String query() {

		logger.debug("query");

		return "teacher/courseact/query";
	}

	@RequestMapping("/mcourseact/current")
	public String currentMajor() {

		logger.debug("query");

		return "teacher/courseact/mcurrent";
	}

	@RequestMapping("/mcourseact/query")
	public String queryMajor() {

		logger.debug("query");

		return "teacher/courseact/mquery";
	}

}
