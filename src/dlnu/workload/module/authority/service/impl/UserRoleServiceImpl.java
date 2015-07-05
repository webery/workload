package dlnu.workload.module.authority.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.authority.dao.UserRoleDao;
import dlnu.workload.module.authority.model.UserRole;
import dlnu.workload.module.authority.service.UserRoleService;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	@Transactional
	public int save(UserRole model) {

		return userRoleDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(UserRole model) {

		return userRoleDao.delete(model);
	}

	@Override
	@Transactional
	public int update(UserRole model) {

		return userRoleDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public UserRole get(UserRole model) {

		return userRoleDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserRole> findAll() {

		return userRoleDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserRole> findByCondition(UserRole model) {

		return userRoleDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserRole> findByConditionPage(UserRole model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return userRoleDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(UserRole model) {
		return userRoleDao.updateStatus(model);
	}

	@Override
	public List<UserRole> findByUser(String userId) {
		return userRoleDao.selectByUser(userId);
	}

	@Override
	public List<UserRole> findByRole(Integer roleId) {
		return userRoleDao.selectByRole(roleId);
	}

	@Override
	public List<UserRole> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
