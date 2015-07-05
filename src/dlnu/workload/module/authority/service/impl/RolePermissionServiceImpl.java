package dlnu.workload.module.authority.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.authority.dao.RolePermissionDao;
import dlnu.workload.module.authority.model.RolePermission;
import dlnu.workload.module.authority.service.RolePermissionService;

@Service("rolePermssionService")
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	private RolePermissionDao rolePermssionDao;

	@Override
	@Transactional
	public int save(RolePermission model) {

		return rolePermssionDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(RolePermission model) {

		return rolePermssionDao.update(model);
	}

	@Override
	@Transactional
	public int update(RolePermission model) {

		return rolePermssionDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public RolePermission get(RolePermission model) {

		return rolePermssionDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RolePermission> findAll() {

		return rolePermssionDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<RolePermission> findByCondition(RolePermission model) {

		return rolePermssionDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RolePermission> findByConditionPage(RolePermission model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return rolePermssionDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(RolePermission model) {
		return rolePermssionDao.updateStatus(model);
	}

	@Override
	public List<RolePermission> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
