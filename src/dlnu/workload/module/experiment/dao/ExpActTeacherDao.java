package dlnu.workload.module.experiment.dao;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.experiment.model.ExpActTeacher;
import dlnu.workload.module.experiment.model.ExperimentAccount;

public interface ExpActTeacherDao extends BaseDao<ExpActTeacher> {
	
	int deleteByExpAccount(ExperimentAccount expAccount);
}
