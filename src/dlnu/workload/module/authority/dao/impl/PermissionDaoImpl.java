package dlnu.workload.module.authority.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.authority.dao.PermissionDao;
import dlnu.workload.module.authority.model.Permission;

public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements
		PermissionDao {
	protected String mapper = "dlnu.workload.module.authority.dao.PermissionDao.";
}
