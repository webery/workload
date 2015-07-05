package dlnu.workload.module.authority.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.authority.dao.RolePermissionDao;
import dlnu.workload.module.authority.model.RolePermission;

public class RolePermissionDaoImpl extends BaseDaoImpl<RolePermission> implements
		RolePermissionDao {

	protected String mapper = "dlnu.workload.module.authority.dao.RolePermssionDao.";
}
