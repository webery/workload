package dlnu.workload.module.practice.dao;

import java.util.List;

import dlnu.workload.framework.dao.BaseDao;
import dlnu.workload.module.practice.model.GrdtdesignActTeacher;

public interface GrdtdesignActTeacherDao extends BaseDao<GrdtdesignActTeacher> {

	List<GrdtdesignActTeacher> selectByGrdtdesignAccount(
			String grdtdesignAccountId);

	public int deletesByGrdtdesignAccount(String grdtdesignAccountId);
}
