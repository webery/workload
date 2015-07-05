package dlnu.workload.module.experiment.controller;

import java.util.HashMap;
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
import dlnu.workload.framework.exception.ParameterException;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.framework.page.domain.JsonPage;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.parameter.ParameterCheck;
import dlnu.workload.module.experiment.model.ExpActTeacher;
import dlnu.workload.module.experiment.model.ExperimentAccount;
import dlnu.workload.module.experiment.model.ExperimentItem;
import dlnu.workload.module.experiment.service.ExpActTeacherService;
import dlnu.workload.module.experiment.service.ExperimentAccountService;
import dlnu.workload.module.experiment.service.ExperimentService;

@Controller
@RequestMapping("/api")
public class ExpActTeacherController extends BaseController {

	private static Logger logger = Logger
			.getLogger(ExpActTeacherController.class);

	@Autowired
	private ExperimentService experimentService;
	@Autowired
	private ExperimentAccountService experimentAccountService;
	@Autowired
	private ExpActTeacherService expActTeacherService;

	@RequestMapping(value = "/user/{userId}/expAccount/{expAccountId}/expActTeachers", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserExpActTeas(
			@PathVariable(value = "expAccountId") String expAccountId,
			@RequestBody List<ExpActTeacher> expActTeachers) {

		logger.debug("expActTeachers:" + expActTeachers);

		if (!ParameterCheck.checkObjectId(expAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		expActTeacherService.batchSave(new ExperimentAccount(expAccountId),
				expActTeachers);

		return new JsonMessage(10000, "录入信息成功!", "ok");
	}

	@RequestMapping(value = "/expAccount/{expAccountId}/expActTeachers", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryExpActTeaPage(
			@PathVariable(value = "expAccountId") String expAccountId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		if (!ParameterCheck.checkObjectId(expAccountId)) {
			throw new ParameterException("请求错误参数!");
		}

		page = 1;
		limit = 20;
		PageBounds pageBound = new PageBounds(page, limit);
		ExpActTeacher expActTeacher = new ExpActTeacher();
		expActTeacher.setExpAccount(new ExperimentAccount(expAccountId));

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"expActTeachers",
				new JsonPage(expActTeacherService.findByConditionPage(
						expActTeacher, pageBound)));

		return result;
	}

}
