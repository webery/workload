package dlnu.workload.module.teacher.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherCdesignAccountController {

	private static Logger logger = Logger
			.getLogger(TeacherCdesignAccountController.class);

	@RequestMapping("/cdesignact/create")
	public String create() {

		logger.debug("create");

		return "teacher/cdesignact/create";
	}

	@RequestMapping("/cdesignact/current")
	public String current() {

		logger.debug("query");

		return "teacher/cdesignact/current";
	}

	@RequestMapping("/cdesignact/query")
	public String query() {

		logger.debug("query");

		return "teacher/cdesignact/query";
	}

	@RequestMapping("/mcdesignact/current")
	public String currentMajor() {

		logger.debug("query");

		return "teacher/cdesignact/mcurrent";
	}

	@RequestMapping("/mcdesignact/query")
	public String queryMajor() {

		logger.debug("query");

		return "teacher/cdesignact/mquery";
	}
}
