package dlnu.workload.module.practice.service;

import java.util.List;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.practice.model.ProjectAccount;

public interface ProjectAccountService extends BaseService<ProjectAccount> {

	List<ProjectAccount> findByCollege(String collegeId,
			ProjectAccount projectAccount);

	List<ProjectAccount> findByCollegePage(String collegeId,
			ProjectAccount projectAccount, PageBounds pageBound);

	List<ProjectAccount> findByMajor(String majorId,
			ProjectAccount projectAccount);

	List<ProjectAccount> findByMajorPage(String majorId,
			ProjectAccount projectAccount, PageBounds pageBound);

	List<ProjectAccount> findMajorProjectAccount(String semester, String majorId);
}
