package dlnu.workload.module.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.common.dao.CollegeDao;
import dlnu.workload.module.common.model.College;
import dlnu.workload.module.common.service.CollegeService;

@Service("collegeService")
@Transactional
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	private CollegeDao collegeDao;

	@Override
	@Transactional
	public int save(College model) {
		return collegeDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(College model) {
		return collegeDao.delete(model);
	}

	@Override
	@Transactional
	public int update(College model) {
		return collegeDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public College get(College model) {
		return collegeDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<College> findAll() {
		return collegeDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<College> findByCondition(College model) {
		return collegeDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<College> findByConditionPage(College model, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return collegeDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(College model) {
		return collegeDao.updateStatus(model);
	}

	@Override
	public List<College> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
