package dlnu.workload.module.theoryteaching.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.theoryteaching.dao.CourseDao;
import dlnu.workload.module.theoryteaching.model.Course;

public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {

	protected String mapper = "dlnu.workload.module.theoryteaching.dao.CourseDao.";

	@Override
	public Course selectByCollegeAndName(Course course) {
		return this.getSqlSession().selectOne(
				mapper + "selectByCollegeAndName", course);
	}
}
