package dlnu.workload.module.practice.service;

import java.util.List;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.practice.model.CdesignAccount;

public interface CdesignAccountService extends BaseService<CdesignAccount> {

	List<CdesignAccount> findByCollege(String collegeId,
			CdesignAccount cdesignAccount);

	List<CdesignAccount> findByCollegePage(String collegeId,
			CdesignAccount cdesignAccount, PageBounds pageBound);

	List<CdesignAccount> findByMajor(String majorId,
			CdesignAccount cdesignAccount);

	List<CdesignAccount> findByMajorPage(String majorId,
			CdesignAccount cdesignAccount, PageBounds pageBound);

	List<CdesignAccount> findMajorCourseAccount(String semester, String majorId);

}
