package dlnu.workload.module.theoryteaching.service;

import java.util.List;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.theoryteaching.model.Course;

public interface CourseService extends BaseService<Course> {

	List<Course> findByCollegeAndNamePage(String collegeId, String name,
			PageBounds pageBound);
}
