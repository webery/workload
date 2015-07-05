package dlnu.workload.module.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.common.dao.SemesterDao;
import dlnu.workload.module.common.model.Semester;
import dlnu.workload.module.common.service.SemesterService;

@Service("semesterService")
@Transactional
public class SemesterServiceImpl implements SemesterService {

	@Autowired
	private SemesterDao semesterDao = null;

	@Override
	@Transactional
	public int save(Semester model) {
		return semesterDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Semester model) {
		return semesterDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Semester model) {
		return semesterDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Semester get(Semester model) {
		return semesterDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Semester> findAll() {
		return semesterDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Semester> findByCondition(Semester model) {
		return semesterDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Semester> findByConditionPage(Semester model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return semesterDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Semester> findByStatus(Integer status) {
		return semesterDao.selectByStatus(status);
	}

	@Override
	@Transactional
	public int updateStatus(Semester model) {
		return semesterDao.updateStatus(model);
	}

	@Override
	public List<Semester> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
