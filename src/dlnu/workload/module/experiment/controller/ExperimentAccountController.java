package dlnu.workload.module.experiment.controller;

import java.util.Date;
import java.util.HashMap;
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
import dlnu.workload.framework.exception.ParameterException;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.framework.page.domain.JsonPage;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.id.ObjectId;
import dlnu.workload.framework.util.parameter.ParameterCheck;
import dlnu.workload.module.common.model.SemesterUtil;
import dlnu.workload.module.experiment.model.ExperimentAccount;
import dlnu.workload.module.experiment.service.ExperimentAccountService;
import dlnu.workload.module.experiment.service.ExperimentService;

@Controller
@RequestMapping("/api")
public class ExperimentAccountController extends BaseController {

	private static Logger logger = Logger
			.getLogger(ExperimentAccountController.class);

	@Autowired
	private ExperimentService experimentService;
	@Autowired
	private ExperimentAccountService experimentAccountService;

	/**
	 * 用户录入实验工作量
	 * 
	 * @param experimentAccount
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/expAccount", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserExperimentAccount(
			@ModelAttribute("experimentAccount") @Valid ExperimentAccount experimentAccount,
			BindingResult result) {

		logger.debug("experimentAccount:" + experimentAccount);

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		if (!ParameterCheck.checkObjectId(experimentAccount.getExperiment()
				.getId())) {
			throw new ParameterException("非法请求参数!");
		}

		experimentAccount.setId(new ObjectId().toString());
		experimentAccount.setSemester(SemesterUtil.getSemester());
		experimentAccount.setCreator(this.getUser());
		experimentAccount.setCreateDate(new Date());
		// init
		experimentAccount.setPeriod(0.0);
		experimentAccount.setOperiod(0.0);
		experimentAccount.setCourseNum(0);
		experimentAccount.setCourseRepnum(0);
		experimentAccount.setClassStunum(0);
		experimentAccount.setClassTime(0.0);
		experimentAccount.setFactor(0.0);
		experimentAccount.setWorkload(0.0);

		experimentAccountService.save(experimentAccount);

		return new JsonMessage(10000, "录入实验成功!", experimentAccount);
	}

	@RequestMapping(value = "/user/{userId}/expAccount/{expAccountId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage deleteUserExperimentAccount(
			@PathVariable(value = "expAccountId") String expAccountId) {

		if (!ParameterCheck.checkObjectId(expAccountId)) {
			throw new ParameterException("非法请求参数!");
		}

		experimentAccountService.delete(new ExperimentAccount(expAccountId));

		return new JsonMessage(10000, "删除成功!", "");
	}

	@RequestMapping(value = "/user/{userId}/expAccount/{expAccountId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage updateUserExperimentAccount(
			@PathVariable(value = "expAccountId") String expAccountId,
			@ModelAttribute("experimentAccount") @Valid ExperimentAccount experimentAccount,
			BindingResult result) {

		logger.debug("experimentAccount:" + experimentAccount);

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		if (!ParameterCheck.checkObjectId(expAccountId)) {
			throw new ParameterException("非法请求参数!");
		}
		experimentAccount.setId(expAccountId);
		experimentAccountService.update(experimentAccount);

		return new JsonMessage(10000, "修改成功!", experimentAccount);
	}

	@RequestMapping(value = "/user/{userId}/expAccount/{expAccountId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getUserExperimentAccount(
			@PathVariable(value = "expAccountId") String expAccountId) {

		if (!ParameterCheck.checkObjectId(expAccountId)) {
			throw new ParameterException("非法请求参数!");
		}

		ExperimentAccount result = experimentAccountService
				.get(new ExperimentAccount(expAccountId));

		return new JsonMessage(10000, "查询成功!", result);
	}

	@RequestMapping(value = "/user/{userId}/expAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserExperimentAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		ExperimentAccount experimentAccount = new ExperimentAccount();
		if (type.equals("current")) {
			experimentAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			experimentAccount.setSemester(semester);
		} else {
			return null;
		}
		experimentAccount.setCreator(this.getUser());
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> result = new HashMap<String, Object>();

		result.put(
				"expAccounts",
				new JsonPage(experimentAccountService.findByConditionPage(
						experimentAccount, pageBound)));

		return result;
	}

	@RequestMapping(value = "/user/{userId}/college/expAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryCollegeExperimentAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		ExperimentAccount experimentAccount = new ExperimentAccount();
		if (type.equals("current")) {
			experimentAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			experimentAccount.setSemester(semester);
		} else {
			return null;
		}
		String collegeId = this.getCollege().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> result = new HashMap<String, Object>();

		result.put(
				"expAccounts",
				new JsonPage(experimentAccountService.findByCollegePage(
						collegeId, experimentAccount, pageBound)));

		return result;
	}

	@RequestMapping(value = "/user/{userId}/major/expAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryMajorExperimentAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		ExperimentAccount experimentAccount = new ExperimentAccount();
		if (type.equals("current")) {
			experimentAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			experimentAccount.setSemester(semester);
		} else {
			return null;
		}

		String majorId = this.getMajor().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> result = new HashMap<String, Object>();

		result.put(
				"expAccounts",
				new JsonPage(experimentAccountService.findByMajorPage(majorId,
						experimentAccount, pageBound)));

		return result;
	}
}
