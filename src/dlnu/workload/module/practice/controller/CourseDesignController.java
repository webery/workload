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
import dlnu.workload.framework.exception.ParameterException;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.framework.page.domain.JsonPage;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.id.ObjectId;
import dlnu.workload.framework.util.id.ObjectIdGenerator;
import dlnu.workload.framework.util.parameter.ParameterCheck;
import dlnu.workload.module.common.model.College;
import dlnu.workload.module.common.model.User;
import dlnu.workload.module.practice.model.CourseDesign;
import dlnu.workload.module.practice.service.CourseDesignService;

@Controller
@RequestMapping("/api")
public class CourseDesignController extends BaseController {

	private static Logger logger = Logger
			.getLogger(CourseDesignController.class);

	@Autowired
	private CourseDesignService courseDesignService;

	/**
	 * 用户录入课程设计
	 * 
	 * @param courseDesign
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/user/courseDesign", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("courseDesign") @Valid CourseDesign courseDesign,
			BindingResult result) {

		logger.debug(" courseDesign:" + courseDesign);

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		ObjectId objectId = (ObjectId) new ObjectIdGenerator().generate();
		courseDesign.setId(objectId.toString());
		courseDesign.setCollege(new College(this.getCollegeId()));
		courseDesign.setCreator(new User(this.getUserId()));
		courseDesign.setCreateDate(new Date());
		courseDesignService.save(courseDesign);

		return new JsonMessage(10000, "录入课程设计信息成功!", courseDesign);
	}

	@RequestMapping(value = "/courseDesign/{courseDesignId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@PathVariable(value = "courseDesignId") String courseDesignId,
			@ModelAttribute("courseDesign") @Valid CourseDesign courseDesign,
			BindingResult result) {

		if (!ParameterCheck.checkObjectId(courseDesignId)) {
			throw new ParameterException("非法请求参数!");
		}

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		courseDesign.setId(courseDesignId);
		courseDesign.setCollege(new College(this.getCollegeId()));
		courseDesignService.update(courseDesign);

		return new JsonMessage(10000, "修改成功!", courseDesign);
	}

	@RequestMapping(value = "/courseDesign/{courseDesignId}", method = RequestMethod.GET)
	public @ResponseBody CourseDesign get(
			@PathVariable(value = "courseDesignId") String courseDesignId) {

		if (!ParameterCheck.checkObjectId(courseDesignId)) {
			throw new ParameterException("非法请求参数!");
		}

		return courseDesignService.get(new CourseDesign(courseDesignId));
	}

	@RequestMapping("/courseDesigns")
	public @ResponseBody Map<String, Object> queryByCondictionPage(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug(" name:" + name + " page:" + page + " limit:" + limit);

		page = page > 0 ? page : 1;
		limit = limit > 0 ? limit : 10;
		name = (name != null && !"".endsWith(name)) ? name : null;

		PageBounds pageBound = new PageBounds(page, limit);
		CourseDesign courseDesign = new CourseDesign();
		courseDesign.setName(name);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"courseDesigns",
				new JsonPage(courseDesignService.findByConditionPage(
						courseDesign, pageBound)));

		return result;
	}

	@RequestMapping("/user/{userId}/college/courseDesigns")
	public @ResponseBody Map<String, Object> queryCollege(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug(" name:" + name + " page:" + page + " limit:" + limit);

		page = page > 0 ? page : 1;
		limit = limit > 0 ? limit : 10;
		name = (name != null && !"".equals(name)) ? name : null;

		PageBounds pageBound = new PageBounds(page, limit);
		CourseDesign courseDesign = new CourseDesign();
		courseDesign.setName(name);
		courseDesign.setCollege(this.getCollege());

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"courseDesigns",
				new JsonPage(courseDesignService.findByConditionPage(
						courseDesign, pageBound)));

		return result;
	}

	/**
	 * 查询用户所在学院的课程设计
	 * 
	 * @param name
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/college/courseDesigns")
	public @ResponseBody Map<String, Object> queryCollegeCourseDesignPage(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		CourseDesign courseDesign = new CourseDesign();
		courseDesign.setCollege(this.getCollege());
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("courseDesigns",
				new JsonPage(this.queryPage(courseDesign, page, limit)));

		return result;

	}

	@RequestMapping(value = "/courseDesign/{courseDesignId}/status", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage updateStatus(
			@PathVariable(value = "courseDesignId") String courseDesignId,
			@RequestParam(value = "operation", required = true) String operation) {

		if (!ParameterCheck.checkObjectId(courseDesignId)) {
			throw new ParameterException("非法请求参数!");
		}

		Integer status = 0;
		if (operation.equals("lock")) {
			status = 0;
		} else if (operation.equals("unlock")) {
			status = 1;
		} else {
			return new JsonMessage(1000, "未知操作!", "");
		}
		// 为校验学院管理员

		CourseDesign courseDesign = new CourseDesign(courseDesignId);
		courseDesign.setStatus(status);
		courseDesignService.updateStatus(courseDesign);

		return new JsonMessage(10000, "锁定成功!", courseDesign);
	}

	private List<CourseDesign> queryPage(CourseDesign courseDesign, int page,
			int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		return courseDesignService.findByConditionPage(courseDesign, pageBound);

	}

}
