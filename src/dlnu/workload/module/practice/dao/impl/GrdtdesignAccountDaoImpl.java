package dlnu.workload.module.practice.dao.impl;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.practice.dao.GrdtdesignAccountDao;
import dlnu.workload.module.practice.model.GrdtdesignAccount;

public class GrdtdesignAccountDaoImpl extends BaseDaoImpl<GrdtdesignAccount>
		implements GrdtdesignAccountDao {

	protected String mapper = "dlnu.workload.module.practice.dao.GrdtdesignAccountDao.";

	@Override
	public GrdtdesignAccount selectBySemesterAndMajorAndGrade(
			GrdtdesignAccount grdtdesignAccount) {
		return this.getSqlSession().selectOne(
				mapper + "selectBySemesterAndMajorAndGrade", grdtdesignAccount);
	}

}
