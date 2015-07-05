package dlnu.workload.module.practice.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.id.ObjectId;
import dlnu.workload.module.practice.dao.CdesignAccountDao;
import dlnu.workload.module.practice.dao.CdesignActTeacherDao;
import dlnu.workload.module.practice.model.CdesignAccount;
import dlnu.workload.module.practice.model.CdesignActTeacher;
import dlnu.workload.module.practice.service.CdesignActTeacherService;

@Service("cdesignActTeacherService")
@Transactional
public class CdesignActTeacherServiceImpl implements CdesignActTeacherService {

	@Autowired
	private CdesignActTeacherDao cdesignActTeacherDao;

	@Autowired
	private CdesignAccountDao cdesignAccountDao;

	@Override
	@Transactional
	public int save(CdesignActTeacher model) {

		return cdesignActTeacherDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(CdesignActTeacher model) {

		return cdesignActTeacherDao.delete(model);
	}

	@Override
	@Transactional
	public int update(CdesignActTeacher model) {

		return cdesignActTeacherDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public CdesignActTeacher get(CdesignActTeacher model) {

		return cdesignActTeacherDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignActTeacher> findAll() {

		return cdesignActTeacherDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignActTeacher> findByCondition(CdesignActTeacher model) {

		return cdesignActTeacherDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignActTeacher> findByConditionPage(CdesignActTeacher model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (model.getTeacher() != null) {
			params.put("teacher", model.getTeacher().getId());
		}
		if (model.getCdesignAccount() != null) {
			params.put("semester", model.getCdesignAccount().getSemester());
		}

		List<CdesignActTeacher> cdesignActTeachers = cdesignActTeacherDao
				.selectByConditionPage(params, pageBound);
		Iterator<CdesignActTeacher> it = cdesignActTeachers.iterator();

		while (it.hasNext()) {
			CdesignActTeacher cdesignActTeacher = it.next();
			cdesignActTeacher.setCdesignAccount(cdesignAccountDao
					.selectOne(cdesignActTeacher.getCdesignAccount()));
		}

		return cdesignActTeachers;
	}

	@Override
	@Transactional
	public int updateStatus(CdesignActTeacher model) {

		return cdesignActTeacherDao.updateStatus(model);
	}

	@Override
	@Transactional
	public void batchSave(CdesignAccount cdesignAccount,
			List<CdesignActTeacher> cdesignActTeachers) {

		// 1.删除旧数据
		cdesignActTeacherDao.deletesByCdesignAccount(cdesignAccount.getId());
		// 2.插入新数据
		Iterator<CdesignActTeacher> it = cdesignActTeachers.iterator();
		while (it.hasNext()) {
			CdesignActTeacher cdesignActTeacher = it.next();
			cdesignActTeacher.setId(new ObjectId().toString());
			cdesignActTeacher.setCdesignAccount(cdesignAccount);
			cdesignActTeacherDao.insert(cdesignActTeacher);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignActTeacher> findByCdesignAccount(String cdesignAccountId) {

		return cdesignActTeacherDao.selectByCdesignAccount(cdesignAccountId);
	}

	@Override
	public List<CdesignActTeacher> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
