package dlnu.workload.module.practice.dao;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.practice.model.CourseDesign;

public interface CourseDesignDao extends BaseDao<CourseDesign> {
	
	/**
	 * 
	 * @param course
	 *            : college.id 学院Id, name 课程名称
	 * @return
	 */
	CourseDesign selectByCollegeAndName(CourseDesign courseDesign);
}
