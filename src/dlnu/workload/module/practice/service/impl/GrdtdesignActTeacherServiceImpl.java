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
import dlnu.workload.module.practice.dao.GrdtdesignAccountDao;
import dlnu.workload.module.practice.dao.GrdtdesignActTeacherDao;
import dlnu.workload.module.practice.model.GrdtdesignAccount;
import dlnu.workload.module.practice.model.GrdtdesignActTeacher;
import dlnu.workload.module.practice.service.GrdtdesignActTeacherService;

@Service("grdtdesignActTeacherService")
@Transactional
public class GrdtdesignActTeacherServiceImpl implements
		GrdtdesignActTeacherService {

	@Autowired
	private GrdtdesignActTeacherDao grdtdesignActTeacherDao;
	@Autowired
	private GrdtdesignAccountDao grdtdesignAccountDao;

	@Override
	@Transactional
	public int save(GrdtdesignActTeacher model) {
		return grdtdesignActTeacherDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(GrdtdesignActTeacher model) {
		return grdtdesignActTeacherDao.delete(model);
	}

	@Override
	@Transactional
	public int update(GrdtdesignActTeacher model) {
		return grdtdesignActTeacherDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public GrdtdesignActTeacher get(GrdtdesignActTeacher model) {
		return grdtdesignActTeacherDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignActTeacher> findAll() {
		return grdtdesignActTeacherDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignActTeacher> findByCondition(GrdtdesignActTeacher model) {
		return grdtdesignActTeacherDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignActTeacher> findByConditionPage(
			GrdtdesignActTeacher model, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (model.getTeacher() != null) {
			params.put("teacher", model.getTeacher().getId());
		}

		if (model.getGrdtdesignAccount() != null) {
			params.put("semester", model.getGrdtdesignAccount().getSemester());
		}

		List<GrdtdesignActTeacher> grdtdesignActTeachers = grdtdesignActTeacherDao
				.selectByConditionPage(params, pageBound);

		Iterator<GrdtdesignActTeacher> it = grdtdesignActTeachers.iterator();
		while (it.hasNext()) {
			GrdtdesignActTeacher grdtdesignActTeacher = it.next();
			grdtdesignActTeacher.setGrdtdesignAccount(grdtdesignAccountDao
					.selectOne(grdtdesignActTeacher.getGrdtdesignAccount()));
		}

		return grdtdesignActTeachers;
	}

	@Override
	@Transactional
	public int updateStatus(GrdtdesignActTeacher model) {
		return grdtdesignActTeacherDao.updateStatus(model);
	}

	@Override
	@Transactional
	public void batchSave(GrdtdesignAccount grdtdesignAccount,
			List<GrdtdesignActTeacher> grdtdesignActTeachers) {

		// 1.删除旧数据
		grdtdesignActTeacherDao.deletesByGrdtdesignAccount(grdtdesignAccount
				.getId());
		// 2.插入新数据
		Iterator<GrdtdesignActTeacher> it = grdtdesignActTeachers.iterator();
		while (it.hasNext()) {
			GrdtdesignActTeacher grdtdesignActTeacher = it.next();
			grdtdesignActTeacher.setId(new ObjectId().toString());
			grdtdesignActTeacher.setGrdtdesignAccount(grdtdesignAccount);
			grdtdesignActTeacherDao.insert(grdtdesignActTeacher);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignActTeacher> findByGrdtdesignAccount(
			String grdtdesignAccountId) {
		return grdtdesignActTeacherDao
				.selectByGrdtdesignAccount(grdtdesignAccountId);
	}

	@Override
	public List<GrdtdesignActTeacher> findByConditions(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
