package dlnu.workload.module.teacher.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/experiment")
public class TeacherExperimentViewController {

	private static Logger logger = Logger
			.getLogger(TeacherExperimentViewController.class);

	@RequestMapping("/create")
	public String create() {

		logger.debug("create");

		return "teacher/experiment/create";
	}

	@RequestMapping("/query")
	public String query() {

		logger.debug("query");

		return "teacher/experiment/query";
	}

}
