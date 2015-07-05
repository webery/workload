package dlnu.workload.module.common.dao.impl;

import java.util.List;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.common.dao.SemesterDao;
import dlnu.workload.module.common.model.Semester;

public class SemesterDaoImpl extends BaseDaoImpl<Semester> implements
		SemesterDao {

	protected String mapper = "dlnu.workload.module.common.dao.SemesterDao.";

	@Override
	public List<Semester> selectByStatus(Integer status) {

		return this.getSqlSession().selectList(mapper + "selectByStatus",
				status);
	}

}
