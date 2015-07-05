package dlnu.workload.module.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dlnu.workload.framework.controller.BaseController;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.module.common.service.CollegeService;
import dlnu.workload.module.common.service.MajorService;

@Controller
@RequestMapping("/api")
public class CollegeController extends BaseController {

	@Autowired
	private CollegeService collegeService;
	@Autowired
	private MajorService majorService;

	@RequestMapping("/colleges")
	public @ResponseBody JsonMessage queryAll() {

		return new JsonMessage(10000, "查询成功", collegeService.findAll());
	}

	@RequestMapping("/college/{collegeId}/majors")
	public @ResponseBody JsonMessage queryMajorByCollege(
			@PathVariable(value = "collegeId") String collegeId) {

		return new JsonMessage(10000, "查询成功",
				majorService.findByCollege(collegeId));
	}

	@RequestMapping(value = "/user/college", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getUserCollege() {

		return new JsonMessage(10000, "查询成功", this.getCollege());
	}

}
