package dlnu.workload.module.experiment.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.id.ObjectId;
import dlnu.workload.module.experiment.dao.ExpActTeacherDao;
import dlnu.workload.module.experiment.model.ExpActTeacher;
import dlnu.workload.module.experiment.model.ExperimentAccount;
import dlnu.workload.module.experiment.service.ExpActTeacherService;

@Service("expActTeacherService")
@Transactional
public class ExpActTeacherServiceImpl implements ExpActTeacherService {

	@Autowired
	private ExpActTeacherDao expActTeacherDao;

	@Override
	@Transactional
	public int save(ExpActTeacher model) {

		return expActTeacherDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(ExpActTeacher model) {

		return expActTeacherDao.delete(model);
	}

	@Override
	@Transactional
	public int update(ExpActTeacher model) {

		return expActTeacherDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public ExpActTeacher get(ExpActTeacher model) {

		return expActTeacherDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExpActTeacher> findAll() {

		return expActTeacherDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExpActTeacher> findByCondition(ExpActTeacher model) {

		return expActTeacherDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExpActTeacher> findByConditions(Map<String, Object> params) {
		return expActTeacherDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExpActTeacher> findByConditionPage(ExpActTeacher model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (model.getExpAccount() != null) {
			params.put("expAccount", model.getExpAccount().getId());
		}
		return expActTeacherDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(ExpActTeacher model) {

		return expActTeacherDao.updateStatus(model);
	}

	@Override
	@Transactional
	public void batchSave(ExperimentAccount expAccount,
			List<ExpActTeacher> expActTeachers) {
		expActTeacherDao.deleteByExpAccount(expAccount);

		Iterator<ExpActTeacher> it = expActTeachers.iterator();
		while (it.hasNext()) {
			ExpActTeacher expActTeacher = it.next();
			expActTeacher.setId(new ObjectId().toString());
			expActTeacher.setExpAccount(expAccount);
			expActTeacherDao.insert(expActTeacher);
		}
	}

}
