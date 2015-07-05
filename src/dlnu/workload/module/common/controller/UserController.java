package dlnu.workload.module.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
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
import dlnu.workload.module.authority.model.Role;
import dlnu.workload.module.authority.model.UserRole;
import dlnu.workload.module.authority.service.UserRoleService;
import dlnu.workload.module.common.model.User;
import dlnu.workload.module.common.service.UserService;

@Controller
@RequestMapping("/api")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;

	private static Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody JsonMessage create(
			@ModelAttribute("user") @Valid User user, BindingResult result) {

		logger.debug("user:" + user);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		String algorithmName = "md5";
		String password = "000000";
		String randomNum = new SecureRandomNumberGenerator().nextBytes()
				.toHex();
		int hashIterations = 2;

		user.setSalt(user.getId() + randomNum);
		SimpleHash hash = new SimpleHash(algorithmName, password,
				user.getSalt(), hashIterations);
		user.setPassword(hash.toHex());

		userService.save(user);

		return new JsonMessage(10000, "添加成功", user);
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
	public @ResponseBody JsonMessage update(
			@ModelAttribute("User") @Valid User user, BindingResult result,
			@PathVariable(value = "userId") String userId) {

		logger.debug("User:" + user);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		user.setId(userId);
		userService.update(user);

		return new JsonMessage(10000, "添加成功", user);
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "userId") String userId) {

		User user = userService.get(new User(userId));

		return new JsonMessage(10000, "查询成功", user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionPage(
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		userName = (userName != null && !userName.equals("")) ? userName : null;

		User user = new User();
		user.setName(userName);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", new JsonPage(this.queryUserPage(user, page, limit)));

		return map;
	}

	/**
	 * 分页获取学院下的所有用户
	 * 
	 * @param userName
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/college/users", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryCollegeUserPage(
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		userName = (userName != null && !userName.equals("")) ? userName : null;

		User user = new User();
		user.setCollege(this.getCollege());
		user.setName(userName);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", new JsonPage(this.queryUserPage(user, page, limit)));

		return map;
	}

	@RequestMapping(value = "/major/users", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryMajorUserPage(
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		userName = (userName != null && !userName.equals("")) ? userName : null;

		User user = new User();
		user.setMajor(this.getMajor());
		user.setName(userName);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", new JsonPage(this.queryUserPage(user, page, limit)));

		return map;
	}

	private List<User> queryUserPage(User user, int page, int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);

		return userService.findByConditionPage(user, pageBound);
	}

	/**
	 * 用户权限
	 */

	/**
	 * 添加用户权限
	 * 
	 * @param userId
	 * @param userRole
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/role", method = RequestMethod.POST)
	public @ResponseBody JsonMessage addRole(
			@PathVariable(value = "userId") String userId,
			@ModelAttribute("userRole") @Valid UserRole userRole,
			BindingResult result) {

		logger.debug("userRole:" + userRole);

		if (result.hasErrors()) {
			return new JsonMessage(200, "输入信息格式错误", result);
		}

		userRoleService.save(userRole);

		return new JsonMessage(10000, "添加成功", userRole);
	}

	@RequestMapping(value = "/user/{userId}/role/{roleId}", method = RequestMethod.DELETE)
	public @ResponseBody JsonMessage delRole(
			@PathVariable("userId") String userId,
			@PathVariable("roleId") Integer roleId) {

		UserRole userRole = new UserRole();
		userRole.setUser(new User(userId));
		userRole.setRole(new Role(roleId));

		logger.debug("userRole:" + userRole);

		userRoleService.delete(userRole);

		return new JsonMessage(10000, "移除成功", userRole);
	}

	/**
	 * 根据用户查询所有角色
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}/roles", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryUserRolePage(
			@PathVariable(value = "userId") String userId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roles", userRoleService.findByUser(userId));

		return map;
	}

}
