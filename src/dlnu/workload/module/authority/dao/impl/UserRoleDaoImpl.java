package dlnu.workload.module.authority.dao.impl;

import java.util.List;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.authority.dao.UserRoleDao;
import dlnu.workload.module.authority.model.UserRole;

public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements
		UserRoleDao {

	protected String mapper = "dlnu.workload.module.authority.dao.UserRoleDao.";

	@Override
	public List<UserRole> selectByUser(String userId) {
		return this.getSqlSession().selectList(mapper + "findByUser", userId);
	}

	@Override
	public List<UserRole> selectByRole(Integer roleId) {
		return this.getSqlSession().selectList(mapper + "findByRole", roleId);
	}
}
