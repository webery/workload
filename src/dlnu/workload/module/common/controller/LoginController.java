package dlnu.workload.module.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.framework.mvc.model.JsonResponse;
import dlnu.workload.framework.util.encryption.MdSaltUtil;
import dlnu.workload.module.common.model.User;
import dlnu.workload.module.common.service.UserService;

@Controller
@RequestMapping("/api/user")
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody JsonMessage login(
			@RequestParam(value = "account", required = true) String account,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request) {

		logger.info("login " + account + " " + password);

		User user = new User(account);

		User result = userService.get(user);

		if (result == null) {
			user.setPassword("");
			return new JsonMessage(1001, "账号或密码错误", user);
		} else if (!MdSaltUtil.verify(user.getPassword(), result.getPassword())) {
			return new JsonMessage(1001, "账号或密码错误", user);
		}

		// 将用户放入session，并返回前端。切记过滤掉用户密码
		request.getSession().setAttribute("user", result);

		return JsonResponse.getJsonMessage(100, "登陆成功");
	}

	@RequestMapping("/logout")
	public String logout() {

		logger.info("logout");

		return "";
	}
}
