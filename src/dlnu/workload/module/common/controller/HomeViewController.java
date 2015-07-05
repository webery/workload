package dlnu.workload.module.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeViewController {

	/**
	 * 网站主页
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String home() {

		return "home/home";
	}
}
