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
import dlnu.workload.module.practice.dao.CdesignAccountDao;
import dlnu.workload.module.practice.dao.CdesignActTeacherDao;
import dlnu.workload.module.practice.dao.CourseDesignDao;
import dlnu.workload.module.practice.dao.GrdtdesignActTeacherDao;
import dlnu.workload.module.practice.model.CdesignAccount;
import dlnu.workload.module.practice.model.CourseDesign;
import dlnu.workload.module.practice.model.GrdtdesignActTeacher;
import dlnu.workload.module.practice.service.CdesignAccountService;

@Service("cdesignAccountService")
@Transactional
public class CdesignAccountServiceImpl implements CdesignAccountService {

	@Autowired
	private CdesignAccountDao cdesignAccountDao;
	@Autowired
	private CourseDesignDao courseDesignDao;
	@Autowired
	private CdesignActTeacherDao cdesignActTeacherDao;

	@Override
	@Transactional
	public int save(CdesignAccount model) {

		CourseDesign courseDesign = courseDesignDao.selectOne(model
				.getCourseDesign());

		if (courseDesign == null) {
			throw new BusinessException("请求的课程设计不存在!");
		}

		model.setCoursedesignName(courseDesign.getName());

		return cdesignAccountDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(CdesignAccount model) {
		// 1.删除课程设计的分配
		// 2.删除课程设计
		return cdesignAccountDao.delete(model);
	}

	@Override
	@Transactional
	public int update(CdesignAccount model) {

		return cdesignAccountDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public CdesignAccount get(CdesignAccount model) {

		return cdesignAccountDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignAccount> findAll() {

		return cdesignAccountDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignAccount> findByCondition(CdesignAccount model) {

		return cdesignAccountDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignAccount> findByConditionPage(CdesignAccount model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (model.getCreator() != null) {
			params.put("creator", model.getCreator().getId());
		}
		params.put("semester", model.getSemester());
		return cdesignAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(CdesignAccount model) {

		return cdesignAccountDao.updateStatus(model);
	}

	@Override
	public List<CdesignAccount> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignAccount> findByCollege(String collegeId,
			CdesignAccount cdesignAccount) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("college", collegeId);
		params.put("semester", cdesignAccount.getSemester());

		return cdesignAccountDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignAccount> findByCollegePage(String collegeId,
			CdesignAccount cdesignAccount, PageBounds pageBound) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("college", collegeId);
		params.put("semester", cdesignAccount.getSemester());

		return cdesignAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignAccount> findByMajor(String majorId,
			CdesignAccount cdesignAccount) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", cdesignAccount.getSemester());

		return cdesignAccountDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignAccount> findByMajorPage(String majorId,
			CdesignAccount cdesignAccount, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", cdesignAccount.getSemester());

		return cdesignAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CdesignAccount> findMajorCourseAccount(String semester,
			String majorId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("semester", semester);
		params.put("major", majorId);
		List<CdesignAccount> cdesignAccounts = cdesignAccountDao
				.selectByConditions(params);

		if (cdesignAccounts == null) {
			return cdesignAccounts;
		}
		Iterator<CdesignAccount> it_ca = cdesignAccounts.iterator();
		while (it_ca.hasNext()) {
			CdesignAccount cdesignAccount = it_ca.next();
			cdesignAccount.setCdesignActTeachers(cdesignActTeacherDao
					.selectByCdesignAccount(cdesignAccount.getId()));
		}

		return cdesignAccounts;
	}

}
