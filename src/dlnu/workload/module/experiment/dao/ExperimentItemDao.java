package dlnu.workload.module.experiment.dao;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.experiment.model.ExperimentAccount;
import dlnu.workload.module.experiment.model.ExperimentItem;

public interface ExperimentItemDao extends BaseDao<ExperimentItem> {

	int deleteByExpAccount(ExperimentAccount experimentAccount);
}
