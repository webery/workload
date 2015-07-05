package dlnu.workload.module.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.module.common.service.TeacherTitleService;

@Controller
@RequestMapping("/api")
public class TeacherTitleController {

	@Autowired
	private TeacherTitleService teacherTitleService;

	@RequestMapping("/teacherTitles")
	public @ResponseBody JsonMessage queryAll() {

		return new JsonMessage(10000, "查询成功", teacherTitleService.findAll());
	}

}
