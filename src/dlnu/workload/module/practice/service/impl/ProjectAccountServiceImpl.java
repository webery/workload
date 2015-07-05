package dlnu.workload.module.practice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.practice.dao.ProjectAccountDao;
import dlnu.workload.module.practice.model.ProjectAccount;
import dlnu.workload.module.practice.service.ProjectAccountService;

@Service("projectAccountService")
@Transactional
public class ProjectAccountServiceImpl implements ProjectAccountService {

	@Autowired
	private ProjectAccountDao projectAccountDao;

	@Override
	@Transactional
	public int save(ProjectAccount model) {

		return projectAccountDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(ProjectAccount model) {

		return projectAccountDao.delete(model);
	}

	@Override
	@Transactional
	public int update(ProjectAccount model) {

		return projectAccountDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public ProjectAccount get(ProjectAccount model) {

		return projectAccountDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectAccount> findAll() {

		return projectAccountDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectAccount> findByCondition(ProjectAccount model) {

		return projectAccountDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectAccount> findByConditionPage(ProjectAccount model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (model.getCreator() != null) {
			params.put("creator", model.getCreator().getId());
		}
		if (model.getSemester() != null) {
			params.put("semester", model.getSemester());
		}
		return projectAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(ProjectAccount model) {

		return projectAccountDao.updateStatus(model);
	}

	@Override
	public List<ProjectAccount> findByConditions(Map<String, Object> params) {
		return null;
	}

	@Override
	public List<ProjectAccount> findByCollege(String collegeId,
			ProjectAccount projectAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("college", collegeId);
		params.put("semester", projectAccount.getSemester());

		return projectAccountDao.selectByConditions(params);
	}

	@Override
	public List<ProjectAccount> findByCollegePage(String collegeId,
			ProjectAccount projectAccount, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("college", collegeId);
		params.put("semester", projectAccount.getSemester());
		return projectAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	public List<ProjectAccount> findByMajor(String majorId,
			ProjectAccount projectAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", projectAccount.getSemester());
		return projectAccountDao.selectByConditions(params);
	}

	@Override
	public List<ProjectAccount> findByMajorPage(String majorId,
			ProjectAccount projectAccount, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", projectAccount.getSemester());
		return projectAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	public List<ProjectAccount> findMajorProjectAccount(String semester,
			String majorId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", semester);
		return projectAccountDao.selectByConditions(params);
	}

}
