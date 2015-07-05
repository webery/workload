package dlnu.workload.module.practice.service;

import java.util.List;

import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.practice.model.GrdtdesignAccount;
import dlnu.workload.module.practice.model.GrdtdesignActTeacher;

public interface GrdtdesignActTeacherService extends
		BaseService<GrdtdesignActTeacher> {

	public void batchSave(GrdtdesignAccount grdtdesignAccount,
			List<GrdtdesignActTeacher> grdtdesignActTeachers);

	public List<GrdtdesignActTeacher> findByGrdtdesignAccount(
			String grdtdesignAccountId);

}
