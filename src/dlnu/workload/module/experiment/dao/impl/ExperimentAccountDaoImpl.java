package dlnu.workload.module.experiment.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.experiment.dao.ExperimentAccountDao;
import dlnu.workload.module.experiment.model.ExperimentAccount;

public class ExperimentAccountDaoImpl extends BaseDaoImpl<ExperimentAccount>
		implements ExperimentAccountDao {

	protected String mapper = "dlnu.workload.module.theoryteaching.dao.ExperimentAccountDao.";

	@Override
	public int updateWithItems(ExperimentAccount expAccount) {
		return this.getSqlSession().update(mapper + "updateWithItems",
				expAccount);
	}
}
