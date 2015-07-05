package dlnu.workload.module.experiment.dao;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.experiment.model.Experiment;

public interface ExperimentDao extends BaseDao<Experiment> {

	/**
	 * 
	 * @param experiment
	 *            : college.id 学院Id, name 课程名称
	 * @return
	 */
	Experiment selectByCollegeAndName(Experiment experiment);
}
