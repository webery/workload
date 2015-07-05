package dlnu.workload.module.common.dao.impl;

import java.util.List;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.common.dao.UserDao;
import dlnu.workload.module.common.model.Major;
import dlnu.workload.module.common.model.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	protected String mapper = "dlnu.workload.module.common.dao.UserDao.";

	@Override
	public List<String> selectRoles(String userId) {
		return this.getSqlSession().selectList(mapper + "selectRoles", userId);
	}

	@Override
	public List<String> selectPermissions(String userId) {
		return this.getSqlSession().selectList(mapper + "selectPermissions",
				userId);
	}

	@Override
	public int updatePassword(User user) {
		return this.getSqlSession().update(mapper + "updatePassword", user);
	}

	@Override
	public List<User> selectByMajor(Major major) {
		return this.getSqlSession().selectList(mapper + "selectByMajor", major);
	}

}
