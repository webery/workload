package dlnu.workload.module.admin.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/userRole")
public class AdmUserRoleViewController {

	private static Logger logger = Logger
			.getLogger(AdmUserRoleViewController.class);

	@RequestMapping("/create")
	public String create() {

		logger.debug("create");

		return "admin/userrole/create";
	}

	@RequestMapping("/query")
	public String query() {

		logger.debug("query");

		return "admin/userrole/query";
	}
}
