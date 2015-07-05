package dlnu.workload.module.common.service;

import java.util.List;

import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.common.model.User;

public interface UserService extends BaseService<User> {
	
	List<String> findRoles(String userId);

	List<String> findPermissions(String userId);
	
	public int updatePassword(User user);
}
