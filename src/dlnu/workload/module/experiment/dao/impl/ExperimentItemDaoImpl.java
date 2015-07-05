package dlnu.workload.module.experiment.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.experiment.dao.ExperimentItemDao;
import dlnu.workload.module.experiment.model.ExperimentAccount;
import dlnu.workload.module.experiment.model.ExperimentItem;

public class ExperimentItemDaoImpl extends BaseDaoImpl<ExperimentItem>
		implements ExperimentItemDao {

	protected String mapper = "dlnu.workload.module.theoryteaching.dao.ExperimentItemDao.";

	@Override
	public int deleteByExpAccount(ExperimentAccount experimentAccount) {
		return this.getSqlSession().delete(mapper + "deleteByExpAccount",
				experimentAccount);
	}
}
