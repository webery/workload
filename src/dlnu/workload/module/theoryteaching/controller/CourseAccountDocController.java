package dlnu.workload.module.theoryteaching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dlnu.workload.framework.controller.BaseController;
import dlnu.workload.module.theoryteaching.doc.CollegeCourseAccountExcelView;
import dlnu.workload.module.theoryteaching.doc.MajorCourseAccountExcelView;
import dlnu.workload.module.theoryteaching.service.CourseAccountService;

@Controller
public class CourseAccountDocController extends BaseController {

	@Autowired
	private CourseAccountService courseAccountService;

	@RequestMapping(value = "/user/{userId}/college/courseAccounts/excel", method = RequestMethod.GET)
	public ModelAndView printCollegeCourseAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			ModelMap model) {

		String collegeId = this.getCollege().getId();

		// model.put("courseAccounts", courseAccountService
		// .findByCollegeAndSemester(collegeId, semester));
		model.put("semester", semester);

		CollegeCourseAccountExcelView excelView = new CollegeCourseAccountExcelView();

		return new ModelAndView(excelView, model);

	}

	@RequestMapping(value = "/user/{userId}/major/courseAccounts/excel", method = RequestMethod.GET)
	public ModelAndView printMajorCourseAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			ModelMap model) {

		String majorId = this.getMajor().getId();

		// model.put("courseAccounts",
		// courseAccountService.findByMajorAndSemester(majorId, semester));
		model.put("semester", semester);
		model.put("major", this.getMajor());
		model.put("courseAccounts",
				courseAccountService.findMajorCourseAccount(semester, majorId));

		MajorCourseAccountExcelView excelView = new MajorCourseAccountExcelView();

		return new ModelAndView(excelView, model);

	}

}
