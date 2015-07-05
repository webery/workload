package dlnu.workload.module.experiment.service;

import java.util.List;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.experiment.model.ExperimentAccount;

public interface ExperimentAccountService extends
		BaseService<ExperimentAccount> {

	List<ExperimentAccount> findByCollege(String collegeId,
			ExperimentAccount experimentAccount);

	List<ExperimentAccount> findByCollegePage(String collegeId,
			ExperimentAccount experimentAccount, PageBounds pageBound);

	List<ExperimentAccount> findByMajor(String majorId,
			ExperimentAccount experimentAccount);

	List<ExperimentAccount> findByMajorPage(String majorId,
			ExperimentAccount experimentAccount, PageBounds pageBound);

	List<ExperimentAccount> findMajorExperimentAccount(String semester,
			String majorId);
}
