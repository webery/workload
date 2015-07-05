package dlnu.workload.module.theoryteaching.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.exception.BusinessException;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.common.dao.UserDao;
import dlnu.workload.module.common.model.Major;
import dlnu.workload.module.common.model.User;
import dlnu.workload.module.theoryteaching.dao.CourseAccountDao;
import dlnu.workload.module.theoryteaching.dao.CourseDao;
import dlnu.workload.module.theoryteaching.model.Course;
import dlnu.workload.module.theoryteaching.model.CourseAccount;
import dlnu.workload.module.theoryteaching.service.CourseAccountService;

@Service("courseAccountService")
@Transactional
public class CourseAccountServiceImpl implements CourseAccountService {

	@Autowired
	private CourseAccountDao courseAccountDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public int save(CourseAccount model) {

		Course course = courseDao.selectOne(model.getCourse());
		if (course == null) {
			throw new BusinessException("选择的课程不存在");
		}
		model.setCourseName(course.getName());

		return courseAccountDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(CourseAccount model) {

		return courseAccountDao.delete(model);
	}

	@Override
	@Transactional
	public int update(CourseAccount model) {

		return courseAccountDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public CourseAccount get(CourseAccount model) {

		return courseAccountDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseAccount> findAll() {

		return courseAccountDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseAccount> findByCondition(CourseAccount model) {

		return courseAccountDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseAccount> findByConditionPage(CourseAccount model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (model.getTeacher() != null) {
			params.put("teacher", model.getTeacher().getId());
		}
		if (model.getSemester() != null) {
			params.put("semester", model.getSemester());
		}
		return courseAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(CourseAccount model) {

		return courseAccountDao.updateStatus(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseAccount> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseAccount> findByMajor(String majorId,
			CourseAccount courseAccount) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", courseAccount.getSemester());

		return courseAccountDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseAccount> findByCollege(String collegeId,
			CourseAccount courseAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("college", collegeId);
		params.put("semester", courseAccount.getSemester());
		return courseAccountDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseAccount> findByMajorPage(String majorId,
			CourseAccount courseAccount, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", courseAccount.getSemester());

		return courseAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseAccount> findByCollegePage(String collegeId,
			CourseAccount courseAccount, PageBounds pageBound) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("college", collegeId);
		params.put("semester", courseAccount.getSemester());

		return courseAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseAccount> findMajorCourseAccount(String semester,
			String majorId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("semester", semester);
		params.put("major", majorId);
		params.put("orders", "user");

		return courseAccountDao.selectByConditions(params);
	}

}
