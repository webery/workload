package dlnu.workload.module.experiment.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.experiment.dao.ExpActTeacherDao;
import dlnu.workload.module.experiment.model.ExpActTeacher;
import dlnu.workload.module.experiment.model.ExperimentAccount;

public class ExpActTeacherDaoImpl extends BaseDaoImpl<ExpActTeacher> implements
		ExpActTeacherDao {
	protected String mapper = "dlnu.workload.module.theoryteaching.dao.ExpActTeacherDao.";

	@Override
	public int deleteByExpAccount(ExperimentAccount expAccount) {
		return this.getSqlSession().delete(mapper + "deleteByExpAccount",
				expAccount);
	}
}
