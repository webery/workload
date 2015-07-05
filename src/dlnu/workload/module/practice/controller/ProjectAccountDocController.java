package dlnu.workload.module.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dlnu.workload.framework.controller.BaseController;
import dlnu.workload.module.practice.doc.MajorProjectAccountExcelView;
import dlnu.workload.module.practice.service.ProjectAccountService;

@Controller
public class ProjectAccountDocController extends BaseController {

	@Autowired
	private ProjectAccountService projectAccountSevice;

	@RequestMapping(value = "/user/{userId}/major/projectAccounts/excel", method = RequestMethod.GET)
	public ModelAndView printMajorCourseAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			ModelMap model) {

		String majorId = this.getMajor().getId();

		model.put("semester", semester);
		model.put("projectAccounts",
				projectAccountSevice.findMajorProjectAccount(semester, majorId));

		MajorProjectAccountExcelView excelView = new MajorProjectAccountExcelView();

		return new ModelAndView(excelView, model);

	}

}
