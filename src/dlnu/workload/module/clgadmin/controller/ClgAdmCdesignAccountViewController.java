package dlnu.workload.module.clgadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clgadmin/cdesignact")
public class ClgAdmCdesignAccountViewController {

	@RequestMapping("/query")
	public String query() {

		return "clgadmin/cdesignact/query";
	}

}
