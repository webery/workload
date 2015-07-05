package dlnu.workload.module.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dlnu.workload.framework.controller.BaseController;
import dlnu.workload.module.common.model.College;
import dlnu.workload.module.common.service.CollegeService;
import dlnu.workload.module.practice.doc.MajorCdesignAccountExcelView;
import dlnu.workload.module.practice.service.CdesignAccountService;

@Controller
public class CdesignAccountDocController extends BaseController {

	@Autowired
	private CdesignAccountService cdesignAccountService;
	@Autowired
	private CollegeService collegeService;

	@RequestMapping(value = "/user/{userId}/major/cdesignAccounts/excel", method = RequestMethod.GET)
	public ModelAndView printMajorCourseAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			ModelMap model) {

		String majorId = this.getMajor().getId();

		model.put("semester", semester);
		model.put("cdesignAccounts",
				cdesignAccountService.findMajorCourseAccount(semester, majorId));
		College college = collegeService.get(this.getCollege());
		model.put("college", college.getName());

		MajorCdesignAccountExcelView excelView = new MajorCdesignAccountExcelView();

		return new ModelAndView(excelView, model);

	}

}
