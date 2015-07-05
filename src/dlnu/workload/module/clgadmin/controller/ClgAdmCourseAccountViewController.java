package dlnu.workload.module.clgadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clgadmin/courseact")
public class ClgAdmCourseAccountViewController {

	@RequestMapping("/query")
	public String query() {

		return "clgadmin/courseact/query";
	}

}
