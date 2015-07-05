package dlnu.workload.module.teacher.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher/cdesignactteach")
public class TeacherCdesignActTeachViewController {

	private static Logger logger = Logger
			.getLogger(TeacherCdesignActTeachViewController.class);

	@RequestMapping("/query")
	public String query() {

		logger.debug("query");

		return "teacher/cdesignactteach/query";
	}
}
