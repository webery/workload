package dlnu.workload.module.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dlnu.workload.module.common.model.Semester;

@Controller
@RequestMapping("/api")
public class SemesterController {

	@RequestMapping("/semester/{semesterId}")
	public @ResponseBody Semester get(
			@PathVariable(value = "semesterId") String semesterId) {

		return null;
	}
}
