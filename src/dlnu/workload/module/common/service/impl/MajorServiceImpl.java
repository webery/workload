package dlnu.workload.module.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.common.dao.MajorDao;
import dlnu.workload.module.common.model.Major;
import dlnu.workload.module.common.service.MajorService;

@Service("majorService")
@Transactional
public class MajorServiceImpl implements MajorService {

	@Autowired
	private MajorDao majorDao;

	@Override
	@Transactional
	public int save(Major model) {
		return majorDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Major model) {
		return majorDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Major model) {
		return majorDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Major get(Major model) {
		return majorDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Major> findAll() {
		return majorDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Major> findByCondition(Major model) {
		return majorDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Major> findByConditionPage(Major model, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return majorDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Major> findByCollege(String collegeId) {
		return majorDao.selectByCollege(collegeId);
	}

	@Override
	@Transactional
	public int updateStatus(Major model) {
		return majorDao.updateStatus(model);
	}

	@Override
	public List<Major> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
