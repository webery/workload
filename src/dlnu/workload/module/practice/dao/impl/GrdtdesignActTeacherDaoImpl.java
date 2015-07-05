package dlnu.workload.module.practice.dao.impl;

import java.util.List;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.practice.dao.GrdtdesignActTeacherDao;
import dlnu.workload.module.practice.model.GrdtdesignActTeacher;

public class GrdtdesignActTeacherDaoImpl extends
		BaseDaoImpl<GrdtdesignActTeacher> implements GrdtdesignActTeacherDao {

	protected String mapper = "dlnu.workload.module.practice.dao.GrdtdesignActTeacherDao.";

	@Override
	public List<GrdtdesignActTeacher> selectByGrdtdesignAccount(
			String grdtdesignAccountId) {
		return this.getSqlSession().selectList(
				mapper + "selectByGrdtdesignAccount", grdtdesignAccountId);
	}

	@Override
	public int deletesByGrdtdesignAccount(String grdtdesignAccountId) {
		return this.getSqlSession().delete(
				mapper + "deletesByGrdtdesignAccount", grdtdesignAccountId);
	}
}
