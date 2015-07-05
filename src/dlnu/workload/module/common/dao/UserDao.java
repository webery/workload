package dlnu.workload.module.common.dao;

import java.util.List;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.common.model.Major;
import dlnu.workload.module.common.model.User;

public interface UserDao extends BaseDao<User> {

	List<String> selectRoles(String userId);

	List<String> selectPermissions(String userId);

	int updatePassword(User user);

	List<User> selectByMajor(Major major);
}
