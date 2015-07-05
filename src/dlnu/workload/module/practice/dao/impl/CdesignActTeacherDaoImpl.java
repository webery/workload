package dlnu.workload.module.practice.dao.impl;

import java.util.List;

import dlnu.workload.framework.dao.BaseDaoImpl;
import dlnu.workload.module.practice.dao.CdesignActTeacherDao;
import dlnu.workload.module.practice.model.CdesignActTeacher;

public class CdesignActTeacherDaoImpl extends BaseDaoImpl<CdesignActTeacher>
		implements CdesignActTeacherDao {

	protected String mapper = "dlnu.workload.module.practice.dao.CdesignActTeacherDao.";

	@Override
	public List<CdesignActTeacher> selectByCdesignAccount(
			String cdesignAccountId) {
		return this.getSqlSession().selectList(
				mapper + "selectByCdesignAccount", cdesignAccountId);
	}

	@Override
	public int deletesByCdesignAccount(String cdesignAccountId) {
		return this.getSqlSession().delete(mapper + "deletesByCdesignAccount",
				cdesignAccountId);
	}
}
