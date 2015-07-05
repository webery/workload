package dlnu.workload.module.experiment.service;

import java.util.List;

import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.experiment.model.ExperimentAccount;
import dlnu.workload.module.experiment.model.ExperimentItem;

public interface ExperimentItemService extends BaseService<ExperimentItem> {
	
	void batchSave(ExperimentAccount expAccount, List<ExperimentItem> experimentItems);
}
