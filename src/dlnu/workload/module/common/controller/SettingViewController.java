package dlnu.workload.module.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/setting")
public class SettingViewController {

	@RequestMapping(value = "/passowrd")
	public String updatePassword() {

		return "setting/password";
	}

}
