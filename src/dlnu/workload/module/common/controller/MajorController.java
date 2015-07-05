package dlnu.workload.module.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dlnu.workload.framework.controller.BaseController;
import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.module.common.service.MajorService;

@Controller
@RequestMapping("/api")
public class MajorController extends BaseController {

	@Autowired
	private MajorService majorService;

	/**
	 * 获取用户的专业
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/major", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getUserMajor() {

		return new JsonMessage(10000, "查询成功", this.getMajor());
	}
}
