package dlnu.workload.module.theoryteaching.dao;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.theoryteaching.model.Course;

public interface CourseDao extends BaseDao<Course> {

	/**
	 * 
	 * @param course
	 *            : college.id 学院Id, name 课程名称
	 * @return
	 */
	Course selectByCollegeAndName(Course course);
}
