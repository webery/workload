package dlnu.workload.module.authority.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dlnu.workload.framework.mvc.model.JsonMessage;
import dlnu.workload.framework.page.domain.JsonPage;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.authority.model.Role;
import dlnu.workload.module.authority.service.RoleService;

@Controller
@RequestMapping("/api")
public class RoleController {

	private static Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET)
	public @ResponseBody JsonMessage get(
			@PathVariable(value = "roleId") Integer roleId) {

		logger.debug(" roleId:" + roleId);

		Role role = roleService.get(new Role(roleId));

		return new JsonMessage(10000, "查询成功", role);
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> queryByConditionPage(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		page = page > 0 ? page : 1;
		limit = (limit > 0 && limit <= 20) ? limit : 10;
		PageBounds pageBound = new PageBounds(page, limit);
		Role role = new Role();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("roles",
				new JsonPage(roleService.findByConditionPage(role, pageBound)));

		return map;
	}
}
