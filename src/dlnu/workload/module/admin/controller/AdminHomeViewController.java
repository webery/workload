package dlnu.workload.module.admin.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeViewController {

	private static Logger logger = Logger
			.getLogger(AdminHomeViewController.class);

	@RequestMapping("/home")
	public String home() {

		logger.debug("home ");

		return "admin/home";
	}

}
