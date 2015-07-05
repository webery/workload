package dlnu.workload.module.common.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dlnu.workload.framework.controller.BaseController;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.module.common.model.User;
import dlnu.workload.module.common.service.UserService;

@Controller
@RequestMapping("/api")
public class SettingController extends BaseController {

	private static Logger logger = Logger.getLogger(SettingController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/{userId}/password", method = RequestMethod.POST)
	public @ResponseBody JsonMessage updatePassword(
			@RequestParam(value = "oldPassword", required = true) String oldPassword,
			@RequestParam(value = "newPassword", required = true) String newPassword,
			@RequestParam(value = "repPassword", required = true) String repPassword) {

		Pattern pattern = Pattern.compile("[A-Za-z0-9]{5,10}");

		Matcher matcher1 = pattern.matcher(newPassword);
		Matcher matcher2 = pattern.matcher(repPassword);

		if (!matcher1.matches() || !matcher2.matches()) {
			return new JsonMessage(4444, "密码格式不正确!", "error password");
		}

		if (!newPassword.equals(repPassword)) {
			return new JsonMessage(4444, "两次密码不一致!", "error password");
		}

		User user = userService.get(this.getUser());

		return new JsonMessage(10000, "修改成功", "");
	}

}
