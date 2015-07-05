package dlnu.workload.module.experiment.dao;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.experiment.model.ExperimentAccount;

public interface ExperimentAccountDao extends BaseDao<ExperimentAccount> {

	int updateWithItems(ExperimentAccount expAccount);
}
