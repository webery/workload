package dlnu.workload.module.experiment.controller;

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
import dlnu.workload.framework.exception.ParameterException;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.framework.page.domain.JsonPage;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.id.ObjectId;
import dlnu.workload.framework.util.parameter.ParameterCheck;
import dlnu.workload.module.experiment.model.Experiment;
import dlnu.workload.module.experiment.service.ExperimentService;

@Controller
@RequestMapping("/api")
public class ExperimentController extends BaseController {

	private static Logger logger = Logger.getLogger(ExperimentController.class);

	@Autowired
	private ExperimentService experimentService;

	/**
	 * 用户创建实验信息
	 * 
	 * @param experiment
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/experiment", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserExperiment(
			@ModelAttribute("experiment") @Valid Experiment experiment,
			BindingResult result) {

		logger.debug(" experiment:" + experiment);

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		experiment.setId(new ObjectId().toString());
		experiment.setCollege(this.getCollege());
		experiment.setCreator(this.getUser());
		experiment.setCreateDate(new Date());

		experimentService.save(experiment);

		return new JsonMessage(10000, "录入实验成功!", experiment);
	}

	/**
	 * 修改实验信息
	 * 
	 * @param experimentId
	 * @param experiment
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/experiment/{experimentId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@PathVariable(value = "experimentId") String experimentId,
			@ModelAttribute("experiment") @Valid Experiment experiment,
			BindingResult result) {

		if (!ParameterCheck.checkObjectId(experimentId)) {
			throw new ParameterException("非法请求参数!");
		}

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		experiment.setId(experimentId);
		experiment.setCollege(this.getCollege());
		experimentService.update(experiment);

		return new JsonMessage(10000, "修改成功!", experiment);
	}

	@RequestMapping(value = "/experiment/{experimentId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "experimentId") String experimentId) {

		if (!ParameterCheck.checkObjectId(experimentId)) {
			throw new ParameterException("非法请求参数!");
		}

		Experiment experiment = new Experiment(experimentId);

		Experiment result = experimentService.get(experiment);

		return new JsonMessage(10000, "查询成功!", result);
	}

	@RequestMapping("/experiments")
	public @ResponseBody Map<String, Object> queryByCondictionPage(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug("queryByCondictionPage " + name + " page:" + page
				+ " limit:" + limit);

		page = page > 0 ? page : 1;
		limit = limit > 0 ? limit : 10;
		name = (name != null && !"".endsWith(name)) ? name : null;
		PageBounds pageBound = new PageBounds(page, limit);
		Experiment experiment = new Experiment();
		experiment.setName(name);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"experiments",
				new JsonPage(experimentService.findByConditionPage(experiment,
						pageBound)));

		return result;
	}

	/**
	 * 查询用户所属学院的实验
	 * 
	 * @param name
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/user/{userId}/college/experiments")
	public @ResponseBody Map<String, Object> queryCollegeExperimentPage(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug("queryByCondictionPage " + name + " page:" + page
				+ " limit:" + limit);

		name = (name != null && !"".equals(name)) ? name : null;
		Experiment experiment = new Experiment();
		experiment.setName(name);
		experiment.setCollege(this.getCollege());

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("experiments",
				new JsonPage(this.queryPage(experiment, page, limit)));

		return result;
	}

	private List<Experiment> queryPage(Experiment experiment, int page,
			int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		return experimentService.findByConditionPage(experiment, pageBound);
	}
}
