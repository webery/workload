package dlnu.workload.module.common.dao;

import java.util.List;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.common.model.Semester;

public interface SemesterDao extends BaseDao<Semester> {

	List<Semester> selectByStatus(Integer status);
}
