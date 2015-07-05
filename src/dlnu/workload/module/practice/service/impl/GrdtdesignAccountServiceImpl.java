package dlnu.workload.module.practice.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.exception.BusinessException;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.practice.dao.GrdtdesignAccountDao;
import dlnu.workload.module.practice.dao.GrdtdesignActTeacherDao;
import dlnu.workload.module.practice.model.GrdtdesignAccount;
import dlnu.workload.module.practice.service.GrdtdesignAccountService;

@Service("grdtdesignAccountService")
@Transactional
public class GrdtdesignAccountServiceImpl implements GrdtdesignAccountService {

	@Autowired
	private GrdtdesignAccountDao grdtdesignAccountDao;
	@Autowired
	private GrdtdesignActTeacherDao grdtdesignActTeacherDao;

	@Override
	@Transactional
	public int save(GrdtdesignAccount model) {

		GrdtdesignAccount grdtdesignAccount = grdtdesignAccountDao
				.selectBySemesterAndMajorAndGrade(model);
		if (grdtdesignAccount != null) {

			throw new BusinessException("该毕业设计已经存在!");
		}

		return grdtdesignAccountDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(GrdtdesignAccount model) {

		return grdtdesignAccountDao.delete(model);
	}

	@Override
	@Transactional
	public int update(GrdtdesignAccount model) {

		return grdtdesignAccountDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public GrdtdesignAccount get(GrdtdesignAccount model) {

		return grdtdesignAccountDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignAccount> findAll() {

		return grdtdesignAccountDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignAccount> findByCondition(GrdtdesignAccount model) {

		return grdtdesignAccountDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignAccount> findByConditionPage(GrdtdesignAccount model,
			PageBounds pageBound) {

		Map<String, Object> params = new HashMap<String, Object>();
		if (model.getCreator() != null) {
			params.put("creator", model.getCreator().getId());
		}
		params.put("semester", model.getSemester());

		return grdtdesignAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(GrdtdesignAccount model) {

		return grdtdesignAccountDao.updateStatus(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignAccount> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignAccount> findByCollege(String collegeId,
			GrdtdesignAccount grdtdesignAccount) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("college", collegeId);
		params.put("semester", grdtdesignAccount.getSemester());

		return grdtdesignAccountDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignAccount> findByCollegePage(String collegeId,
			GrdtdesignAccount grdtdesignAccount, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("college", collegeId);
		params.put("semester", grdtdesignAccount.getSemester());

		return grdtdesignAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignAccount> findByMajor(String majorId,
			GrdtdesignAccount grdtdesignAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", grdtdesignAccount.getSemester());

		return grdtdesignAccountDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignAccount> findByMajorPage(String majorId,
			GrdtdesignAccount grdtdesignAccount, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", grdtdesignAccount.getSemester());
		return grdtdesignAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrdtdesignAccount> findMajorCourseAccount(String semester,
			String majorId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", semester);

		List<GrdtdesignAccount> grdtdesignAccounts = grdtdesignAccountDao
				.selectByConditions(params);
		if (grdtdesignAccounts == null) {
			return grdtdesignAccounts;
		}
		Iterator<GrdtdesignAccount> it = grdtdesignAccounts.iterator();
		while (it.hasNext()) {
			GrdtdesignAccount grdtdesignAccount = it.next();
			grdtdesignAccount.setGrdtdesignActTeachers(grdtdesignActTeacherDao
					.selectByGrdtdesignAccount(grdtdesignAccount.getId()));
		}

		return grdtdesignAccounts;
	}

}
