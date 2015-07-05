package dlnu.workload.module.practice.service;

import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.practice.model.CourseDesign;

public interface CourseDesignService extends BaseService<CourseDesign> {

	public CourseDesign findByCollegeAndName(CourseDesign courseDesign);
}
