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
import dlnu.workload.module.practice.model.ProjectAccount;
import dlnu.workload.module.practice.service.ProjectAccountService;

@Controller
@RequestMapping("/api")
public class ProjectAccountController extends BaseController {

	private static Logger logger = Logger
			.getLogger(ProjectAccountController.class);

	@Autowired
	private ProjectAccountService projectAccountService;

	/**
	 * 用户录入大创工作量
	 * 
	 * @param projectAccount
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/projectAccount", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserProjectAccount(
			@ModelAttribute("projectAccount") @Valid ProjectAccount projectAccount,
			BindingResult result) {

		logger.debug("projectAccount:" + projectAccount);
		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		if (projectAccount.getSpcode().equals("")
				&& projectAccount.getMpcode().equals("")) {
			return new JsonMessage(2001, "单人指导项目和多人指导项目不能同时不填写!", result);
		}

		if (projectAccount.getSpcode().equals("")) {
			projectAccount.setSworkload(0.0);
		}

		if (projectAccount.getMpcode().equals("")) {
			projectAccount.setMrworkload(0.0);
			projectAccount.setMrworkload(0.0);
		}

		projectAccount.setId(new ObjectId().toString());
		projectAccount.setSemester(SemesterUtil.getSemester());
		projectAccount.setCreator(this.getUser());
		projectAccount.setCreateDate(new Date());

		projectAccountService.save(projectAccount);

		return new JsonMessage(10000, "录入信息成功!", projectAccount);

	}

	@RequestMapping(value = "/user/{userId}/projectAccount/{projectAccountId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage deleteUserProjectAccount(
			@PathVariable(value = "projectAccountId") String projectAccountId) {

		if (!ParameterCheck.checkObjectId(projectAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		ProjectAccount projectAccount = new ProjectAccount();
		projectAccount.setId(projectAccountId);

		projectAccountService.delete(projectAccount);

		return new JsonMessage(10000, "删除成功!", projectAccount);
	}

	@RequestMapping(value = "/user/{userId}/projectAccount/{projectAccountId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage updateUserProjectAccount(
			@ModelAttribute("projectAccount") @Valid ProjectAccount projectAccount,
			BindingResult result,
			@PathVariable(value = "projectAccountId") String projectAccountId) {

		logger.debug("projectAccount:" + projectAccount);
		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		if (!ParameterCheck.checkObjectId(projectAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		if (projectAccount.getSpcode().equals("")
				&& projectAccount.getMpcode().equals("")) {
			return new JsonMessage(2001, "单人指导项目和多人指导项目不能同时不填写!", result);
		}

		if (projectAccount.getSpcode().equals("")) {
			projectAccount.setSworkload(0.0);
		}

		if (projectAccount.getMpcode().equals("")) {
			projectAccount.setMrworkload(0.0);
			projectAccount.setMrworkload(0.0);
		}

		projectAccount.setId(projectAccountId);
		projectAccountService.update(projectAccount);

		return new JsonMessage(10000, "修改成功!", projectAccount);
	}

	@RequestMapping(value = "/user/{userId}/projectAccount/{projectAccountId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getUserProjectAccount(
			@PathVariable(value = "projectAccountId") String projectAccountId) {

		if (!ParameterCheck.checkObjectId(projectAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		ProjectAccount projectAccount = projectAccountService
				.get(new ProjectAccount(projectAccountId));

		return new JsonMessage(10000, "查询成功!", projectAccount);
	}

	@RequestMapping(value = "/user/{userId}/projectAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserProjectAccountPage(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = false) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		ProjectAccount projectAccount = new ProjectAccount();
		projectAccount.setCreator(this.getUser());

		if (type.equals("current")) {
			projectAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			projectAccount.setSemester(semester);
		} else {
			return null;
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("projectAccounts",
				new JsonPage(this.queryPage(projectAccount, page, limit)));

		return result;

	}

	@RequestMapping(value = "/user/{userId}/college/projectAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryCollegeProjectAccountPage(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		ProjectAccount projectAccount = new ProjectAccount();

		if (type.equals("current")) {
			projectAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			projectAccount.setSemester(semester);
		} else {
			return null;
		}

		String collegeId = this.getCollege().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"projectAccounts",
				new JsonPage(projectAccountService.findByCollegePage(collegeId,
						projectAccount, pageBound)));

		return result;

	}

	@RequestMapping(value = "/user/{userId}/major/projectAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryMajorProjectAccountPage(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		ProjectAccount projectAccount = new ProjectAccount();

		if (type.equals("current")) {
			projectAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			projectAccount.setSemester(semester);
		} else {
			return null;
		}

		String majorId = this.getMajor().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"projectAccounts",
				new JsonPage(projectAccountService.findByMajorPage(majorId,
						projectAccount, pageBound)));

		return result;

	}

	private List<ProjectAccount> queryPage(ProjectAccount projectAccount,
			int page, int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		return projectAccountService.findByConditionPage(projectAccount,
				pageBound);

	}
}
