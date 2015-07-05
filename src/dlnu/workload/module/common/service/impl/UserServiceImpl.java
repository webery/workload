package dlnu.workload.module.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.authority.dao.RoleDao;
import dlnu.workload.module.common.dao.UserDao;
import dlnu.workload.module.common.model.User;
import dlnu.workload.module.common.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public int save(User model) {
		return userDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(User model) {
		return userDao.delete(model);
	}

	@Override
	@Transactional
	public int update(User model) {
		return userDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public User get(User model) {
		return userDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByCondition(User model) {
		return userDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByConditionPage(User model, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", model.getName());
		if (model.getCollege() != null) {
			params.put("college", model.getCollege().getId());
		}
		if (model.getMajor() != null) {
			params.put("major", model.getMajor().getId());
		}
		return userDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findRoles(String userId) {
		return userDao.selectRoles(userId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findPermissions(String userId) {
		return userDao.selectPermissions(userId);
	}

	@Override
	@Transactional
	public int updateStatus(User model) {
		return userDao.updateStatus(model);
	}

	@Override
	@Transactional
	public int updatePassword(User user) {

		return userDao.updatePassword(user);
	}

	@Override
	public List<User> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
