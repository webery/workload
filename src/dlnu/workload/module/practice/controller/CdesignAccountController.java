package dlnu.workload.module.practice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dlnu.workload.framework.controller.BaseController;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.framework.page.domain.JsonPage;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.id.ObjectId;
import dlnu.workload.framework.util.parameter.ParameterCheck;
import dlnu.workload.module.common.model.SemesterUtil;
import dlnu.workload.module.practice.model.CdesignAccount;
import dlnu.workload.module.practice.service.CdesignAccountService;

@Controller
@RequestMapping("/api")
public class CdesignAccountController extends BaseController {

	private static Logger logger = Logger
			.getLogger(CdesignAccountController.class);

	@Autowired
	private CdesignAccountService cdesignAccountService;

	/**
	 * 用户例如课程设计工作量
	 * 
	 * @param cdesignAccount
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/user/cdesignAccount", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserCdesignAccount(
			@ModelAttribute("cdesignAccount") @Valid CdesignAccount cdesignAccount,
			BindingResult result) {

		logger.debug("cdesignAccount:" + cdesignAccount);
		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		if (!ParameterCheck.checkObjectId(cdesignAccount.getCourseDesign()
				.getId())) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		cdesignAccount.setId(new ObjectId().toString());
		cdesignAccount.setSemester(SemesterUtil.getSemester());
		cdesignAccount.setMajor(this.getMajor());
		cdesignAccount.setCreator(this.getUser());
		cdesignAccount.setCreateDate(new Date());

		cdesignAccountService.save(cdesignAccount);

		return new JsonMessage(10000, "录入信息成功!", cdesignAccount);

	}

	/**
	 * 删除用户创建的课程设计工作量
	 * 
	 * @param cdesignAccountId
	 * @return
	 */
	@RequestMapping(value = "/user/cdesignAccount/{cdesignAccountId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage deleteUserCdesignAccount(
			@PathVariable(value = "cdesignAccountId") String cdesignAccountId) {

		if (!ParameterCheck.checkObjectId(cdesignAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", cdesignAccountId);
		}

		return new JsonMessage(10000, "删除成功!", cdesignAccountId);
	}

	@RequestMapping(value = "/user/cdesignAccount/{cdesignAccountId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage updateUserCdesignAccount(
			@PathVariable(value = "cdesignAccountId") String cdesignAccountId,
			@ModelAttribute("cdesignAccount") @Valid CdesignAccount cdesignAccount,
			BindingResult result) {

		logger.debug("cdesignAccount:" + cdesignAccount);
		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		if (!ParameterCheck.checkObjectId(cdesignAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		cdesignAccount.setId(cdesignAccountId);

		cdesignAccountService.update(cdesignAccount);

		return new JsonMessage(10000, "修改成功!", cdesignAccount);

	}

	@RequestMapping(value = "/user/cdesignAccount/{cdesignAccountId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getUserCdesignAccount(
			@PathVariable(value = "cdesignAccountId") String cdesignAccountId) {

		if (!ParameterCheck.checkObjectId(cdesignAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		CdesignAccount cdesignAccount = cdesignAccountService
				.get(new CdesignAccount(cdesignAccountId));

		return new JsonMessage(10000, "修改成功!", cdesignAccount);
	}

	@RequestMapping(value = "/user/cdesignAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserCdesignAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = false) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		limit = 20;
		CdesignAccount cdesignAccount = new CdesignAccount();
		cdesignAccount.setCreator(this.getUser());

		if (type.equals("current")) {
			cdesignAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			cdesignAccount.setSemester(semester);
		} else {
			return null;
		}
		Map<String, Object> result = new HashMap<String, Object>();

		result.put(
				"cdesignAccounts",
				new JsonPage(this.queryCdesignAccountPage(cdesignAccount, page,
						limit)));

		return result;
	}

	/**
	 * 获取用户所属学院的课设工作量
	 * 
	 * @param type
	 * @param semester
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/college/cdesignAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryCollegeCdesignAccountPage(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = false) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		CdesignAccount cdesignAccount = new CdesignAccount();
		if (type.equals("current")) {
			cdesignAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			cdesignAccount.setSemester(semester);
		} else {
			return null;
		}

		String collegeId = this.getCollege().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> result = new HashMap<String, Object>();

		result.put(
				"cdesignAccounts",
				new JsonPage(cdesignAccountService.findByCollegePage(collegeId,
						cdesignAccount, pageBound)));

		return result;
	}

	/**
	 * 获取用户所属专业的课设工作量
	 * 
	 * @param type
	 * @param semester
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/major/cdesignAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryMajorCdesignAccountPage(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		CdesignAccount cdesignAccount = new CdesignAccount();

		if (type.equals("current")) {
			cdesignAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			cdesignAccount.setSemester(semester);
		} else {
			return null;
		}

		String majorId = this.getMajor().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> result = new HashMap<String, Object>();

		result.put(
				"cdesignAccounts",
				new JsonPage(cdesignAccountService.findByMajorPage(majorId,
						cdesignAccount, pageBound)));

		return result;
	}

	private List<CdesignAccount> queryCdesignAccountPage(
			CdesignAccount cdesignAccount, int page, int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		return cdesignAccountService.findByConditionPage(cdesignAccount,
				pageBound);
	}
}
