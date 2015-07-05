package dlnu.workload.module.practice.dao;

import java.util.List;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.practice.model.CdesignActTeacher;

public interface CdesignActTeacherDao extends BaseDao<CdesignActTeacher> {

	List<CdesignActTeacher> selectByCdesignAccount(String cdesignAccountId);

	public int deletesByCdesignAccount(String cdesignAccountId);

}
