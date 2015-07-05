package dlnu.workload.module.common.service;

import java.util.List;

import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.common.model.Semester;

public interface SemesterService extends BaseService<Semester> {
	
	List<Semester> findByStatus(Integer status);

}
