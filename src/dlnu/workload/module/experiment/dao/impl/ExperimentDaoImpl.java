package dlnu.workload.module.experiment.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.experiment.dao.ExperimentDao;
import dlnu.workload.module.experiment.model.Experiment;

public class ExperimentDaoImpl extends BaseDaoImpl<Experiment> implements
		ExperimentDao {

	protected String mapper = "dlnu.workload.module.theoryteaching.dao.ExperimentDao.";

	@Override
	public Experiment selectByCollegeAndName(Experiment experiment) {
		return this.getSqlSession().selectOne(
				mapper + "selectByCollegeAndName", experiment);
	}
}
