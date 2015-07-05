package dlnu.workload.module.practice.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dlnu.workload.framework.controller.BaseController;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.framework.page.domain.JsonPage;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.parameter.ParameterCheck;
import dlnu.workload.module.common.model.SemesterUtil;
import dlnu.workload.module.practice.model.GrdtdesignAccount;
import dlnu.workload.module.practice.model.GrdtdesignActTeacher;
import dlnu.workload.module.practice.service.GrdtdesignAccountService;
import dlnu.workload.module.practice.service.GrdtdesignActTeacherService;

@Controller
@RequestMapping("/api")
public class GrdtdesignActTeacherController extends BaseController {

	private static Logger logger = Logger
			.getLogger(GrdtdesignActTeacherController.class);

	@Autowired
	private GrdtdesignActTeacherService grdtdesignActTeacherService;
	@Autowired
	private GrdtdesignAccountService grdtdesignAccountService;

	// @ModelAttribute("grdtdesignActTeachers") @Valid
	@RequestMapping(value = "/user/grdtdesignAccount/{grdtdesignAccountId}/grdtdesignActTeachers", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserGrdtdesignActTeachers(
			@PathVariable(value = "grdtdesignAccountId") String grdtdesignAccountId,
			@RequestBody List<GrdtdesignActTeacher> grdtdesignActTeachers) {

		logger.debug(grdtdesignActTeachers);

		// 判断工作量
		if (!ParameterCheck.checkObjectId(grdtdesignAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		GrdtdesignAccount grdtdesignAccount = grdtdesignAccountService
				.get(new GrdtdesignAccount(grdtdesignAccountId));

		if (grdtdesignAccount == null) {
			return new JsonMessage(2001, "工作量不存在!", "");
		}

		double workload = grdtdesignAccount.getWorkload();
		double period = grdtdesignAccount.getPeriod();

		double t_workload = 0.0;
		double t_period = 0.0;

		Iterator<GrdtdesignActTeacher> it = grdtdesignActTeachers.iterator();
		while (it.hasNext()) {
			GrdtdesignActTeacher grdtdesignActTeacher = it.next();
			t_workload += grdtdesignActTeacher.getWorkload();
			t_period += grdtdesignActTeacher.getPeriod();
		}

		if (t_workload > workload || t_period > period) {
			return new JsonMessage(2001, "工作量或计划课时不能大于总的工作量或总的计划学时!", "");
		}

		grdtdesignActTeacherService.batchSave(new GrdtdesignAccount(
				grdtdesignAccountId), grdtdesignActTeachers);

		return new JsonMessage(10000, "录入信息成功!", "ok");
	}

	/**
	 * 查询用户创建的毕业设计工作量分配 {grdtdesignAccountId} 之间不要有空格
	 * 
	 * @param grdtdesignAccountId
	 * @param type
	 * @param semester
	 * @return
	 */
	@RequestMapping(value = "/user/grdtdesignAccount/{grdtdesignAccountId}/grdtdesignActTeachers", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserGrdtdesignActTeacher(
			@PathVariable(value = "grdtdesignAccountId") String grdtdesignAccountId) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("grdtdesignActTeachers", grdtdesignActTeacherService
				.findByGrdtdesignAccount(grdtdesignAccountId));

		return result;
	}

	@RequestMapping(value = "/user/{userId}/grdtdesignActTeachers", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserGrdtdesignActTeacherPage(
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		GrdtdesignActTeacher grdtdesignActTeacher = new GrdtdesignActTeacher();
		grdtdesignActTeacher.setTeacher(this.getUser());
		GrdtdesignAccount grdtdesignAccount = new GrdtdesignAccount();

		if (type.equals("current")) {
			grdtdesignAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			grdtdesignAccount.setSemester(semester);
		} else {
			return null;
		}

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("grdtdesignActTeachers",
				new JsonPage(this.queryPage(grdtdesignActTeacher, page, limit)));

		return result;

	}

	private List<GrdtdesignActTeacher> queryPage(
			GrdtdesignActTeacher grdtdesignActTeacher, int page, int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		return grdtdesignActTeacherService.findByConditionPage(
				grdtdesignActTeacher, pageBound);
	}

}
