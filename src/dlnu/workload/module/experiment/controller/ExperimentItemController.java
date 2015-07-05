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
import dlnu.workload.module.experiment.model.ExperimentAccount;
import dlnu.workload.module.experiment.model.ExperimentItem;
import dlnu.workload.module.experiment.service.ExperimentAccountService;
import dlnu.workload.module.experiment.service.ExperimentItemService;
import dlnu.workload.module.experiment.service.ExperimentService;

@Controller
@RequestMapping("/api")
public class ExperimentItemController extends BaseController {

	private static Logger logger = Logger
			.getLogger(ExperimentItemController.class);

	@Autowired
	private ExperimentService experimentService;
	@Autowired
	private ExperimentAccountService experimentAccountService;
	@Autowired
	private ExperimentItemService experimentItemService;

	@RequestMapping(value = "/user/{userId}/expAccount/{expAccountId}/expItems", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserExperimentItems(
			@PathVariable(value = "expAccountId") String expAccountId,
			@RequestBody List<ExperimentItem> experimentItems) {

		logger.debug("experimentItems:" + experimentItems);

		if (!ParameterCheck.checkObjectId(expAccountId)) {
			return new JsonMessage(2001, "请求错误参数!", "");
		}

		experimentItemService.batchSave(new ExperimentAccount(expAccountId),
				experimentItems);

		return new JsonMessage(10000, "录入信息成功!", "ok");
	}

	@RequestMapping(value = "/expAccount/{expAccountId}/expItems", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryExpAccountExpItemPage(
			@PathVariable(value = "expAccountId") String expAccountId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		if (!ParameterCheck.checkObjectId(expAccountId)) {
			throw new ParameterException("请求错误参数!");
		}

		page = 1;
		limit = 20;
		PageBounds pageBound = new PageBounds(page, limit);
		ExperimentItem experimentItem = new ExperimentItem();
		experimentItem.setExpAccount(new ExperimentAccount(expAccountId));

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"expItems",
				new JsonPage(experimentItemService.findByConditionPage(
						experimentItem, pageBound)));

		return result;
	}

}
