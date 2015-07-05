package dlnu.workload.module.theoryteaching.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
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
import dlnu.workload.module.common.model.SemesterUtil;
import dlnu.workload.module.common.model.User;
import dlnu.workload.module.theoryteaching.model.CourseAccount;
import dlnu.workload.module.theoryteaching.service.CourseAccountService;

@Controller
@RequestMapping("/api")
public class CourseAccountController extends BaseController {

	private static Logger logger = Logger
			.getLogger(CourseAccountController.class);

	@Autowired
	private CourseAccountService courseAccountService;

	/**
	 * 用户部分
	 */

	/**
	 * 增加用户理论课程
	 * 
	 * @param courseAccount
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/user/courseAccount", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("courseAccount") @Valid CourseAccount courseAccount,
			BindingResult result) {

		logger.debug(" courseAccount:" + courseAccount);

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		if (!ParameterCheck.checkObjectId(courseAccount.getCourse().getId())) {
			return new JsonMessage(2001, "非法数据!", courseAccount.getCourse()
					.getId());
		}

		ObjectId objectId = (ObjectId) new ObjectIdGenerator().generate();
		courseAccount.setId(objectId.toString());
		courseAccount.setSemester(SemesterUtil.getSemester());
		courseAccount.setTeacher(new User(this.getUserId()));
		courseAccount.setCourseName("test");
		courseAccount.setWorkload(99.9);

		courseAccountService.save(courseAccount);

		return new JsonMessage(10000, "录入成功!", courseAccount);
	}

	/**
	 * 删除用户自己的理论课程
	 * 
	 * @param courseAccountId
	 * @return
	 */
	@RequestMapping(value = "/user/courseAccount/{courseAccountId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage delete(
			@PathVariable(value = "courseAccountId") String courseAccountId) {

		if (!ParameterCheck.checkObjectId(courseAccountId)) {
			return new JsonMessage(2001, "非法数据!", courseAccountId);
		}

		courseAccountService.delete(new CourseAccount(courseAccountId));

		return new JsonMessage(10000, "删除成功!", "");
	}

	@RequestMapping(value = "/user/courseAccount/{courseAccountId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@PathVariable(value = "courseAccountId") String courseAccountId,
			@ModelAttribute("courseAccount") @Valid CourseAccount courseAccount,
			BindingResult result) {

		logger.debug("courseAccount:" + courseAccount);

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		courseAccount.setWorkload(99.9);
		courseAccountService.update(courseAccount);

		return new JsonMessage(10000, "修改成功!", courseAccount);
	}

	@RequestMapping(value = "/user/courseAccount/{courseAccountId}/status", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage updateStatus(
			@PathVariable(value = "courseAccountId") String courseAccountId,
			@RequestParam(value = "operation", required = true) String operation) {

		if (!ParameterCheck.checkObjectId(courseAccountId)) {
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

		CourseAccount courseAccount = new CourseAccount();
		courseAccount.setId(courseAccountId);
		courseAccount.setStatus(status);
		courseAccountService.updateStatus(courseAccount);

		return new JsonMessage(10000, "操作成功!", courseAccount);
	}

	/**
	 * 获取用户自己创建的课程工作量
	 * 
	 * @param courseAccountId
	 * @return
	 */
	@RequestMapping(value = "/user/courseAccount/{courseAccountId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "courseAccountId") String courseAccountId) {

		if (!ParameterCheck.checkObjectId(courseAccountId)) {
			throw new ParameterException("非法请求参数!");
		}

		CourseAccount courseAccount = new CourseAccount();
		courseAccount.setId(courseAccountId);
		CourseAccount result = courseAccountService.get(courseAccount);

		if (result != null
				&& !this.getUserId().equals(result.getTeacher().getId())) {
			throw new AuthenticationException("无权限访问");
		}

		return new JsonMessage(10000, "查询成功!", result);
	}

	/**
	 * 获取用户所属专业下的某个理论课程工作量
	 * 
	 * @param courseAccountId
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/major/courseAccount/{courseAccountId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getMajorCourseAccount(
			@PathVariable(value = "courseAccountId") String courseAccountId) {

		if (!ParameterCheck.checkObjectId(courseAccountId)) {
			throw new ParameterException("非法请求参数!");
		}

		CourseAccount courseAccount = new CourseAccount();
		courseAccount.setId(courseAccountId);
		CourseAccount result = courseAccountService.get(courseAccount);

		return new JsonMessage(10000, "查询成功!", result);
	}

	/**
	 * 获取用户所属学院下的工作量
	 * 
	 * @param courseAccountId
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/college/courseAccount/{courseAccountId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getCollegeCourseAccount(
			@PathVariable(value = "courseAccountId") String courseAccountId) {

		if (!ParameterCheck.checkObjectId(courseAccountId)) {
			throw new ParameterException("非法请求参数!");
		}

		CourseAccount courseAccount = new CourseAccount();
		courseAccount.setId(courseAccountId);
		CourseAccount result = courseAccountService.get(courseAccount);

		return new JsonMessage(10000, "查询成功!", result);
	}

	/**
	 * 查询用户理论课程
	 * 
	 * @param type
	 * @param semester
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/user/courseAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserCourseAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = false) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		CourseAccount courseAccount = new CourseAccount();
		courseAccount.setTeacher(this.getUser());

		if (type.equals("current")) {
			courseAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			courseAccount.setSemester(semester);
		} else {
			return null;
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("courseAccounts",
				new JsonPage(this.queryPage(courseAccount, page, limit)));

		return result;
	}

	/**
	 * 查询用户所属学院的工作量
	 * 
	 * @param type
	 * @param semester
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/college/courseAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryCollegeCourseAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		CourseAccount courseAccount = new CourseAccount();

		if (type.equals("current")) {
			courseAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			courseAccount.setSemester(semester);
		} else {
			return null;
		}

		String collegeId = this.getCollege().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"courseAccounts",
				new JsonPage(courseAccountService.findByCollegePage(collegeId,
						courseAccount, pageBound)));

		return result;

	}

	@RequestMapping(value = "/user/{userId}/major/courseAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryMajorCourseAccount(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		CourseAccount courseAccount = new CourseAccount();

		if (type.equals("current")) {
			courseAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			courseAccount.setSemester(semester);
		} else {
			return null;
		}

		String majorId = this.getMajor().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"courseAccounts",
				new JsonPage(courseAccountService.findByMajorPage(majorId,
						courseAccount, pageBound)));

		return result;
	}

	/**
	 * 
	 */

	@RequestMapping("/courseAccounts")
	public @ResponseBody Map<String, Object> queryByCondictionPage(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		logger.debug(" name:" + name + " page:" + page + " limit:" + limit);

		name = (name != null && !"".equals(name)) ? name : null;

		CourseAccount courseAccount = new CourseAccount();

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("courseAccounts",
				new JsonPage(this.queryPage(courseAccount, page, limit)));

		return result;
	}

	private List<CourseAccount> queryPage(CourseAccount courseAccount,
			int page, int limit) {
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;

		PageBounds pageBound = new PageBounds(page, limit);

		return courseAccountService.findByConditionPage(courseAccount,
				pageBound);

	}

}
