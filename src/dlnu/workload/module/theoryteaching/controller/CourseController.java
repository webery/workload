package dlnu.workload.module.theoryteaching.controller;

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

import dlnu.workload.framework.page.domain.JsonPage;
import dlnu.workload.framework.controller.BaseController;
import dlnu.workload.framework.exception.ParameterException;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.id.ObjectId;
import dlnu.workload.framework.util.id.ObjectIdGenerator;
import dlnu.workload.framework.util.parameter.ParameterCheck;
import dlnu.workload.module.common.model.College;
import dlnu.workload.module.theoryteaching.model.Course;
import dlnu.workload.module.theoryteaching.service.CourseService;

@Controller
@RequestMapping("/api")
public class CourseController extends BaseController {

	private static Logger logger = Logger.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/user/{userId}/course", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserCourse(
			@ModelAttribute("course") @Valid Course course, BindingResult result) {

		logger.debug(" course:" + course);

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		ObjectId objectId = (ObjectId) new ObjectIdGenerator().generate();
		course.setId(objectId.toString());
		course.setCollege(new College(this.getCollegeId()));
		course.setCreateDate(new Date());
		courseService.save(course);

		return new JsonMessage(10000, "录入课程成功!", course);
	}

	@RequestMapping(value = "/user/{userId}/course/{courseId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage updateUserCourse(
			@PathVariable(value = "courseId") String courseId,
			@ModelAttribute("course") @Valid Course course, BindingResult result) {

		if (!ParameterCheck.checkObjectId(courseId)) {
			throw new ParameterException("非法请求参数!");
		}

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		course.setId(courseId);
		course.setCollege(new College(this.getCollegeId()));
		courseService.update(course);

		return new JsonMessage(10000, "修改成功!", course);
	}

	@RequestMapping(value = "/course/{courseId}", method = RequestMethod.GET)
	public @ResponseBody Course get(
			@PathVariable(value = "courseId") String courseId) {

		if (!ParameterCheck.checkObjectId(courseId)) {
			throw new ParameterException("非法请求参数!");
		}

		Course course = new Course();
		course.setId(courseId);
		return courseService.get(course);
	}

	@RequestMapping("/courses")
	public @ResponseBody Map<String, Object> queryByCondictionPage(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug(" name:" + name + " page:" + page + " limit:" + limit);

		page = page > 0 ? page : 1;
		limit = limit > 0 ? limit : 10;
		name = (name != null && !"".endsWith(name)) ? name : null;

		PageBounds pageBound = new PageBounds(page, limit);
		Course course = new Course();
		course.setName(name);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"courses",
				new JsonPage(courseService.findByConditionPage(course,
						pageBound)));

		return result;
	}

	@RequestMapping("/user/{userId}/college/courses")
	public @ResponseBody Map<String, Object> queryCollegeCoursePage(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug(" name:" + name + " page:" + page + " limit:" + limit);

		Course course = new Course();
		course.setName(name);
		course.setCollege(this.getCollege());

		page = page > 0 ? page : 1;
		limit = limit > 0 ? limit : 10;
		name = (name != null && !name.equals("")) ? name : null;

		PageBounds pageBound = new PageBounds(page, limit);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"courses",
				new JsonPage(courseService.findByConditionPage(course,
						pageBound)));

		return result;
	}

	@RequestMapping(value = "/course/{courseId}/status", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage updateStatus(
			@PathVariable(value = "courseId") String courseId,
			@RequestParam(value = "operation", required = true) String operation) {

		if (!ParameterCheck.checkObjectId(courseId)) {
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

		Course course = new Course();
		course.setId(courseId);
		course.setStatus(status);
		courseService.updateStatus(course);

		return new JsonMessage(10000, "锁定成功!", course);
	}
}
