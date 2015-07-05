package dlnu.workload.module.authority.service;

import java.util.List;

import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.authority.model.UserRole;

/**
 * 
 * @author weber 2015.4.18
 *
 */
public interface UserRoleService extends BaseService<UserRole> {

	/**
	 * 根据用户id查找
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRole> findByUser(String userId);

	/**
	 * 根据角色id查找
	 * 
	 * @param roleId
	 * @return
	 */
	List<UserRole> findByRole(Integer roleId);

}
