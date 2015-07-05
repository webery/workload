package dlnu.workload.module.practice.dao;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.practice.model.GrdtdesignAccount;

public interface GrdtdesignAccountDao extends BaseDao<GrdtdesignAccount> {

	/**
	 * 根据学期，专业和年级查询
	 * 
	 * @param grdtdesignAccount
	 * @return
	 */
	public GrdtdesignAccount selectBySemesterAndMajorAndGrade(
			GrdtdesignAccount grdtdesignAccount);
}
