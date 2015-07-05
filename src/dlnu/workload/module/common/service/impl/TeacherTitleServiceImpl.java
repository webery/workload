package dlnu.workload.module.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.common.dao.TeacherTitleDao;
import dlnu.workload.module.common.model.TeacherTitle;
import dlnu.workload.module.common.service.TeacherTitleService;

@Service("teacherTitleService")
@Transactional
public class TeacherTitleServiceImpl implements TeacherTitleService {

	@Autowired
	private TeacherTitleDao teacherTitleDao;

	@Override
	@Transactional
	public int save(TeacherTitle model) {
		return teacherTitleDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(TeacherTitle model) {
		return teacherTitleDao.delete(model);
	}

	@Override
	@Transactional
	public int update(TeacherTitle model) {
		return teacherTitleDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public TeacherTitle get(TeacherTitle model) {
		return teacherTitleDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TeacherTitle> findAll() {
		return teacherTitleDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TeacherTitle> findByCondition(TeacherTitle model) {
		return teacherTitleDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TeacherTitle> findByConditionPage(TeacherTitle model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		return teacherTitleDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(TeacherTitle model) {
		return teacherTitleDao.updateStatus(model);
	}

	@Override
	public List<TeacherTitle> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
