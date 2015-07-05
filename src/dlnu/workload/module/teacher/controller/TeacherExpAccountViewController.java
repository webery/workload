package dlnu.workload.module.teacher.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherExpAccountViewController {

	private static Logger logger = Logger
			.getLogger(TeacherExpAccountViewController.class);

	@RequestMapping("/experimentact/create")
	public String create() {

		logger.debug("create");

		return "teacher/experimentact/create";
	}

	@RequestMapping("/experimentact/current")
	public String currentMajor() {

		logger.debug("current");

		return "teacher/experimentact/current";
	}

	@RequestMapping("/experimentact/query")
	public String queryMajor() {

		logger.debug("query");

		return "teacher/experimentact/query";
	}

	@RequestMapping("mexperimentact/current")
	public String current() {

		logger.debug("query");

		return "teacher/experimentact/mcurrent";
	}

	@RequestMapping("/mexperimentact/query")
	public String query() {

		logger.debug("query");

		return "teacher/experimentact/mquery";
	}

}
