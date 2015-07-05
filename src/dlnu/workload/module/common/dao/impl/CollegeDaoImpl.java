package dlnu.workload.module.common.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.common.dao.CollegeDao;
import dlnu.workload.module.common.model.College;

public class CollegeDaoImpl extends BaseDaoImpl<College> implements CollegeDao {

	protected String mapper = "dlnu.workload.module.common.dao.CollegeDao.";
}
