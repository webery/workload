package dlnu.workload.module.authority.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.authority.dao.RoleDao;
import dlnu.workload.module.authority.model.Role;
import dlnu.workload.module.authority.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public int save(Role model) {
		return roleDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Role model) {

		return roleDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Role model) {

		return roleDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Role get(Role model) {

		return roleDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {

		return roleDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> findByCondition(Role model) {

		return roleDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> findByConditionPage(Role model, PageBounds pageBound) {

		Map<String, Object> params = new HashMap<String, Object>();
		return roleDao.selectByConditionPage(params, pageBound);
	}

	@Override
	public int updateStatus(Role model) {
		return roleDao.updateStatus(model);
	}

	@Override
	public List<Role> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
