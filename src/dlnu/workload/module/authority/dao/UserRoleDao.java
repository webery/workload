package dlnu.workload.module.authority.dao;

import java.util.List;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.authority.model.UserRole;

public interface UserRoleDao extends BaseDao<UserRole> {

	/**
	 * 根据用户id查找
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRole> selectByUser(String userId);

	/**
	 * 根据角色id查找
	 * 
	 * @param roleId
	 * @return
	 */
	List<UserRole> selectByRole(Integer roleId);
}
