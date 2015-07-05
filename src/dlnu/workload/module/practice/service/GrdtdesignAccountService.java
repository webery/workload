package dlnu.workload.module.practice.service;

import java.util.List;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.practice.model.GrdtdesignAccount;

public interface GrdtdesignAccountService extends
		BaseService<GrdtdesignAccount> {

	List<GrdtdesignAccount> findByCollege(String collegeId,
			GrdtdesignAccount grdtdesignAccount);

	List<GrdtdesignAccount> findByCollegePage(String collegeId,
			GrdtdesignAccount grdtdesignAccount, PageBounds pageBound);

	List<GrdtdesignAccount> findByMajor(String majorId,
			GrdtdesignAccount grdtdesignAccount);

	List<GrdtdesignAccount> findByMajorPage(String majorId,
			GrdtdesignAccount grdtdesignAccount, PageBounds pageBound);

	List<GrdtdesignAccount> findMajorCourseAccount(String semester,
			String majorId);
}
