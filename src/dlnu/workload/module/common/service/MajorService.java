package dlnu.workload.module.common.service;

import java.util.List;

import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.common.model.Major;

public interface MajorService extends BaseService<Major> {

	List<Major> findByCollege(String collegeId);
}
