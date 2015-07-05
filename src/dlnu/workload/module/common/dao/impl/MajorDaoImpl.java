package dlnu.workload.module.common.dao.impl;

import java.util.List;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.common.dao.MajorDao;
import dlnu.workload.module.common.model.Major;

public class MajorDaoImpl extends BaseDaoImpl<Major> implements MajorDao {

	protected String mapper = "dlnu.workload.module.common.dao.BaseDao.";

	@Override
	public List<Major> selectByCollege(String collegeId) {
		return this.getSqlSession().selectList(this.mapper + "selectByCollege",
				collegeId);
	}

}
