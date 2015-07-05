package dlnu.workload.module.clgadmin.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clgadmin")
public class ClgAdminHomeViewController {

	private static Logger logger = Logger
			.getLogger(ClgAdminHomeViewController.class);

	@RequestMapping("/home")
	public String home() {

		logger.debug("home ");

		return "clgadmin/home";
	}
}
