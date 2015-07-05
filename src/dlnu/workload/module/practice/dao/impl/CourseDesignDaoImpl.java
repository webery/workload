package dlnu.workload.module.practice.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.practice.dao.CourseDesignDao;
import dlnu.workload.module.practice.model.CourseDesign;

public class CourseDesignDaoImpl extends BaseDaoImpl<CourseDesign> implements
		CourseDesignDao {

	protected String mapper = "dlnu.workload.module.practice.dao.CourseDesignDao.";

	@Override
	public CourseDesign selectByCollegeAndName(CourseDesign courseDesign) {
		return this.getSqlSession().selectOne(
				mapper + "selectByCollegeAndName", courseDesign);
	}
}
