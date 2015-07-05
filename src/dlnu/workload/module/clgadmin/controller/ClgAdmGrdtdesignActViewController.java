package dlnu.workload.module.clgadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clgadmin/grdtdesignact")
public class ClgAdmGrdtdesignActViewController {
	
	@RequestMapping("/query")
	public String query() {

		return "clgadmin/grdtdesignact/query";
	}

}
