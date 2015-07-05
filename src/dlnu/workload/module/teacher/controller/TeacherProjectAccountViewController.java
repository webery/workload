package dlnu.workload.module.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherProjectAccountViewController {

	@RequestMapping("/projectAct/create")
	public String create() {

		return "teacher/projectaccount/create";
	}

	@RequestMapping("/projectAct/current")
	public String current() {

		return "teacher/projectaccount/current";
	}

	@RequestMapping("/projectAct/query")
	public String query() {

		return "teacher/projectaccount/query";
	}

	@RequestMapping("/mprojectAct/current")
	public String currentMajor() {

		return "teacher/projectaccount/mcurrent";
	}

	@RequestMapping("/mprojectAct/query")
	public String queryMajor() {

		return "teacher/projectaccount/mquery";
	}

}
