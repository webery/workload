package dlnu.workload.module.theoryteaching.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.exception.BusinessException;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.theoryteaching.dao.CourseDao;
import dlnu.workload.module.theoryteaching.model.Course;
import dlnu.workload.module.theoryteaching.service.CourseService;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Override
	@Transactional
	public int save(Course model) {
		Course course = courseDao.selectByCollegeAndName(new Course(model
				.getCollege(), model.getName()));
		if (course != null) {
			throw new BusinessException("同一学院不能存在同名课程!");
		}
		return courseDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Course model) {
		return courseDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Course model) {

		Course course = courseDao.selectByCollegeAndName(new Course(model
				.getCollege(), model.getName()));
		if (course != null && !course.getId().equals(model.getId())) {
			throw new BusinessException("同一学院不能存在同名课程!");
		}

		return courseDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Course get(Course model) {
		Course result = courseDao.selectOne(model);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() {
		return courseDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findByCondition(Course model) {
		return courseDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findByConditionPage(Course course, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("name", course.getName());
		if (course.getCollege() != null) {
			params.put("college", course.getCollege().getId());
		}

		return courseDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(Course model) {
		return courseDao.updateStatus(model);
	}

	@Override
	public List<Course> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> findByCollegeAndNamePage(String collegeId, String name,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("college", collegeId);
		params.put("name", name);

		return courseDao.selectByConditionPage(params, pageBound);
	}

}
