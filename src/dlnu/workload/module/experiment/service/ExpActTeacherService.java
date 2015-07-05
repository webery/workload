package dlnu.workload.module.experiment.service;

import java.util.List;

import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.experiment.model.ExpActTeacher;
import dlnu.workload.module.experiment.model.ExperimentAccount;

public interface ExpActTeacherService extends BaseService<ExpActTeacher> {
	
	void batchSave(ExperimentAccount expAccount, List<ExpActTeacher> expActTeachers);
}
