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
import dlnu.workload.module.practice.model.CdesignAccount;
import dlnu.workload.module.practice.model.CdesignActTeacher;
import dlnu.workload.module.practice.service.CdesignAccountService;
import dlnu.workload.module.practice.service.CdesignActTeacherService;

@Controller
@RequestMapping("/api")
public class CdesignActTeacherController extends BaseController {

	private static Logger logger = Logger
			.getLogger(CdesignActTeacherController.class);

	@Autowired
	private CdesignActTeacherService cdesignActTeacherService;
	@Autowired
	private CdesignAccountService cdesignAccountService;

	// @ModelAttribute("grdtdesignActTeachers") @Valid
	@RequestMapping(value = "/user/cdesignAccount/{cdesignAccountId}/cdesignActTeachers", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserCdesignActTeachers(
			@PathVariable(value = "cdesignAccountId") String cdesignAccountId,
			@RequestBody List<CdesignActTeacher> cdesignActTeachers) {

		logger.debug("cdesignActTeachers:" + cdesignActTeachers);

		// 判断工作量
		if (!ParameterCheck.checkObjectId(cdesignAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		CdesignAccount cdesignAccount = cdesignAccountService
				.get(new CdesignAccount(cdesignAccountId));
		if (cdesignAccount == null) {
			return new JsonMessage(2001, "工作量不存在!", "");
		}
		double workload = cdesignAccount.getWorkload();
		double period = cdesignAccount.getPeriod();

		double t_workload = 0.0;
		double t_period = 0.0;
		Iterator<CdesignActTeacher> it = cdesignActTeachers.iterator();
		while (it.hasNext()) {
			CdesignActTeacher cdesignActTeacher = it.next();
			t_workload += cdesignActTeacher.getWorkload();
			t_period += cdesignActTeacher.getPeriod();
		}
		if (t_workload > workload || t_period > period) {
			return new JsonMessage(2001, "工作量或计划课时不能大于总的工作量或总的计划学时!", "");
		}

		cdesignActTeacherService.batchSave(cdesignAccount, cdesignActTeachers);

		return new JsonMessage(10000, "录入信息成功!", "ok");
	}

	/**
	 * 查询用户创建的课设设计工作量分配
	 * 
	 * @param grdtdesignAccountId
	 * @param type
	 * @param semester
	 * @return
	 */
	@RequestMapping(value = "/user/cdesignAccount/{cdesignAccountId}/cdesignActTeachers", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserCdesignActTeacher(
			@PathVariable(value = "cdesignAccountId") String cdesignAccountId) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("cdesignAccounts",
				cdesignActTeacherService.findByCdesignAccount(cdesignAccountId));

		return result;
	}

	/**
	 * 查询用户分配到的工作量
	 * 
	 * @param type
	 * @param semester
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/cdesignActTeachers", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserCdesignActTeacherPage(
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		CdesignActTeacher cdesignActTeacher = new CdesignActTeacher();
		cdesignActTeacher.setTeacher(this.getUser());
		CdesignAccount cdesignAccount = new CdesignAccount();
		cdesignActTeacher.setCdesignAccount(cdesignAccount);

		if (type.equals("current")) {
			cdesignAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			cdesignAccount.setSemester(semester);
		} else {
			return null;
		}

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("cdesignActTeachers",
				new JsonPage(this.queryPage(cdesignActTeacher, page, limit)));

		return result;

	}

	private List<CdesignActTeacher> queryPage(
			CdesignActTeacher cdesignActTeacher, int page, int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		return cdesignActTeacherService.findByConditionPage(cdesignActTeacher,
				pageBound);
	}

}
