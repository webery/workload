package dlnu.workload.module.theoryteaching.service;

import java.util.List;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.common.model.User;
import dlnu.workload.module.theoryteaching.model.CourseAccount;

public interface CourseAccountService extends BaseService<CourseAccount> {

	/**
	 * 根据专业和学期查询
	 * 
	 * @param majorId
	 * @param semester
	 * @return
	 */
	List<CourseAccount> findByMajor(String majorId, CourseAccount courseAccount);

	List<CourseAccount> findByMajorPage(String majorId,
			CourseAccount courseAccount, PageBounds pageBound);

	/**
	 * 根据学院和学期查询
	 * 
	 * @param collegeId
	 * @param semester
	 * @return
	 */
	List<CourseAccount> findByCollege(String collegeId,
			CourseAccount courseAccount);

	List<CourseAccount> findByCollegePage(String collegeId,
			CourseAccount courseAccount, PageBounds pageBound);

	/**
	 * 根据学期和专业查询用户的课程工作量
	 * 
	 * @param semester
	 * @param majorId
	 * @return
	 */
	List<CourseAccount> findMajorCourseAccount(String semester, String majorId);

}
