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
import dlnu.workload.module.common.model.Major;
import dlnu.workload.module.common.service.MajorService;
import dlnu.workload.module.practice.doc.MajorCdesignAccountExcelView;
import dlnu.workload.module.practice.doc.MajorGrdtdesignAccountExcelView;
import dlnu.workload.module.practice.service.GrdtdesignAccountService;

@Controller
public class GrdtdesignAccountDocController extends BaseController {

	@Autowired
	private GrdtdesignAccountService grdtdesignAccountService;
	@Autowired
	private MajorService majorService;

	@RequestMapping(value = "/user/{userId}/major/grdtdesignAccounts/excel", method = RequestMethod.GET)
	public ModelAndView printMajorCourseAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			ModelMap model) {

		String majorId = this.getMajor().getId();

		model.put("semester", semester);
		model.put("grdtdesignAccounts", grdtdesignAccountService
				.findMajorCourseAccount(semester, majorId));
		Major major = majorService.get(this.getMajor());
		model.put("major", major.getName());

		MajorGrdtdesignAccountExcelView excelView = new MajorGrdtdesignAccountExcelView();

		return new ModelAndView(excelView, model);

	}

}
