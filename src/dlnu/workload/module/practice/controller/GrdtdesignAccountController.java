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
import dlnu.workload.framework.util.parameter.ParameterCheck;
import dlnu.workload.module.common.model.Major;
import dlnu.workload.module.common.model.SemesterUtil;
import dlnu.workload.module.practice.model.GrdtdesignAccount;
import dlnu.workload.module.practice.service.GrdtdesignAccountService;

@Controller
@RequestMapping("/api")
public class GrdtdesignAccountController extends BaseController {

	private static Logger logger = Logger
			.getLogger(GrdtdesignAccountController.class);

	@Autowired
	private GrdtdesignAccountService grdtdesignAccountService;

	@RequestMapping(value = "/user/grdtdesignAccount", method = RequestMethod.POST)
	public @ResponseBody JsonMessage createUserGrdtdesignAccount(
			@ModelAttribute("grdtdesignAccount") @Valid GrdtdesignAccount grdtdesignAccount,
			BindingResult result) {

		logger.debug(" grdtdesignAccount:" + grdtdesignAccount);

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		grdtdesignAccount.setId(new ObjectId().toString());
		grdtdesignAccount.setSemester(SemesterUtil.getSemester());
		grdtdesignAccount.setMajor(new Major(this.getMajorId()));
		grdtdesignAccount.setCreator(this.getUser());
		grdtdesignAccount.setCreateDate(new Date());

		grdtdesignAccountService.save(grdtdesignAccount);

		return new JsonMessage(10000, "录入信息成功!", grdtdesignAccount);
	}

	@RequestMapping(value = "/user/grdtdesignAccount/{grdtdesignAccountId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage updateUserGrdtdesignAccount(
			@ModelAttribute("grdtdesignAccount") @Valid GrdtdesignAccount grdtdesignAccount,
			BindingResult result,
			@PathVariable(value = "grdtdesignAccountId") String grdtdesignAccountId) {

		logger.debug("grdtdesignAccount:" + grdtdesignAccount);

		if (result.hasErrors()) {
			return new JsonMessage(2001, "输入参数格式错误!", result);
		}

		if (!ParameterCheck.checkObjectId(grdtdesignAccountId)) {
			throw new ParameterException("非法请求参数!");
		}

		grdtdesignAccount.setId(grdtdesignAccountId);
		grdtdesignAccountService.update(grdtdesignAccount);

		return new JsonMessage(10000, "修改成功!", grdtdesignAccount);
	}

	@RequestMapping(value = "/user/grdtdesignAccount/{grdtdesignAccountId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage deleteUserGrdtdesignAccount(
			@PathVariable(value = "grdtdesignAccountId") String grdtdesignAccountId) {

		if (!ParameterCheck.checkObjectId(grdtdesignAccountId)) {
			throw new ParameterException("非法请求参数!");
		}

		grdtdesignAccountService.delete(new GrdtdesignAccount(
				grdtdesignAccountId));

		return new JsonMessage(10000, "删除成功!", grdtdesignAccountId);
	}

	@RequestMapping(value = "/user/grdtdesignAccount/{grdtdesignAccountId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getUserGrdtdesignAccount(
			@PathVariable(value = "grdtdesignAccountId") String grdtdesignAccountId) {

		if (!ParameterCheck.checkObjectId(grdtdesignAccountId)) {
			throw new ParameterException("非法请求参数!");
		}

		GrdtdesignAccount result = grdtdesignAccountService
				.get(new GrdtdesignAccount(grdtdesignAccountId));

		if (!result.getCreator().getId().equals(this.getUserId())) {
			return new JsonMessage(4444, "无权限操作", null);
		}

		return new JsonMessage(10000, "查询成功", result);
	}

	/**
	 * 获取用户创建的毕业设计
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/user/grdtdesignAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserGrdtdesignAccountPage(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = false) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		GrdtdesignAccount grdtdesignAccount = new GrdtdesignAccount();
		grdtdesignAccount.setCreator(this.getUser());

		if (type.equals("current")) {
			grdtdesignAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			grdtdesignAccount.setSemester(semester);
		} else {
			return null;
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("grdtdesignAccounts",
				new JsonPage(this.queryPage(grdtdesignAccount, page, limit)));

		return result;
	}

	/**
	 * 获取学院毕设工作量
	 * 
	 * @param type
	 * @param semester
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/college/grdtdesignAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserCollegeGrdtdesignAccountPage(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = false) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		GrdtdesignAccount grdtdesignAccount = new GrdtdesignAccount();

		if (type.equals("current")) {
			grdtdesignAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			grdtdesignAccount.setSemester(semester);
		} else {
			return null;
		}

		String collegeId = this.getCollege().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"grdtdesignAccounts",
				new JsonPage(grdtdesignAccountService.findByCollegePage(
						collegeId, grdtdesignAccount, pageBound)));

		return result;
	}

	@RequestMapping(value = "/user/{userId}/major/grdtdesignAccounts", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserMajorGrdtdesignAccountPage(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "semester", required = true) String semester,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		GrdtdesignAccount grdtdesignAccount = new GrdtdesignAccount();

		if (type.equals("current")) {
			grdtdesignAccount.setSemester(SemesterUtil.getSemester());
		} else if (type.equals("previous")) {
			grdtdesignAccount.setSemester(semester);
		} else {
			return null;
		}

		String majorId = this.getMajor().getId();
		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(
				"grdtdesignAccounts",
				new JsonPage(grdtdesignAccountService.findByMajorPage(majorId,
						grdtdesignAccount, pageBound)));

		return result;
	}

	private List<GrdtdesignAccount> queryPage(
			GrdtdesignAccount grdtdesignAccount, int page, int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		return grdtdesignAccountService.findByConditionPage(grdtdesignAccount,
				pageBound);
	}
}
