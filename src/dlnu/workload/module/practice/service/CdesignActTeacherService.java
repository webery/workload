package dlnu.workload.module.practice.service;

import java.util.List;

import dlnu.workload.framework.service.BaseService;
import dlnu.workload.module.practice.model.CdesignAccount;
import dlnu.workload.module.practice.model.CdesignActTeacher;

public interface CdesignActTeacherService extends
		BaseService<CdesignActTeacher> {

	public void batchSave(CdesignAccount cdesignAccount,
			List<CdesignActTeacher> cdesignActTeachers);

	public List<CdesignActTeacher> findByCdesignAccount(String cdesignAccountId);

}
