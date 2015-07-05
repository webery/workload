package dlnu.workload.module.authority.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.authority.dao.PermissionDao;
import dlnu.workload.module.authority.model.Permission;
import dlnu.workload.module.authority.service.PermissionService;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	@Transactional
	public int save(Permission model) {
		return permissionDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Permission model) {
		return permissionDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Permission model) {
		return permissionDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Permission get(Permission model) {
		return permissionDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Permission> findAll() {
		return permissionDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Permission> findByCondition(Permission model) {
		return permissionDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Permission> findByConditionPage(Permission model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return permissionDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(Permission model) {
		return permissionDao.updateStatus(model);
	}

	@Override
	public List<Permission> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
