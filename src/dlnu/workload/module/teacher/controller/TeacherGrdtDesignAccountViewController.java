package dlnu.workload.module.teacher.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherGrdtDesignAccountViewController {

	private static Logger logger = Logger
			.getLogger(TeacherGrdtDesignAccountViewController.class);

	@RequestMapping("/grdtdesignact/create")
	public String create() {

		logger.debug("create");

		return "teacher/grdtdesignact/create";
	}

	@RequestMapping("/grdtdesignact/current")
	public String current() {

		logger.debug("current");

		return "teacher/grdtdesignact/current";
	}

	@RequestMapping("/grdtdesignact/query")
	public String query() {

		logger.debug("query");

		return "teacher/grdtdesignact/query";
	}

	@RequestMapping("/mgrdtdesignact/current")
	public String currentMajor() {

		logger.debug("current");

		return "teacher/grdtdesignact/mcurrent";
	}

	@RequestMapping("/mgrdtdesignact/query")
	public String queryMajor() {

		logger.debug("query");

		return "teacher/grdtdesignact/mquery";
	}

}
