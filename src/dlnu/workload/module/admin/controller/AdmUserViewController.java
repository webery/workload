package dlnu.workload.module.admin.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class AdmUserViewController {

	private static Logger logger = Logger
			.getLogger(AdmUserViewController.class);

	@RequestMapping("/create")
	public String create() {

		logger.debug("create");

		return "admin/user/create";
	}

	@RequestMapping("/query")
	public String query() {

		logger.debug("query");

		return "admin/user/query";
	}

}
