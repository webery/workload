package dlnu.workload.module.common.dao;

import java.util.List;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.common.model.Major;

public interface MajorDao extends BaseDao<Major> {

	List<Major> selectByCollege(String collegeId);

}
