package dlnu.workload.module.experiment.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.framework.util.id.ObjectId;
import dlnu.workload.module.experiment.dao.ExperimentAccountDao;
import dlnu.workload.module.experiment.dao.ExperimentItemDao;
import dlnu.workload.module.experiment.model.ExperimentAccount;
import dlnu.workload.module.experiment.model.ExperimentItem;
import dlnu.workload.module.experiment.service.ExperimentItemService;

@Service("experimentItemService")
@Transactional
public class ExperimentItemServiceImpl implements ExperimentItemService {

	@Autowired
	private ExperimentItemDao experimentItemDao;
	@Autowired
	private ExperimentAccountDao experimentAccountDao;

	@Override
	@Transactional
	public int save(ExperimentItem model) {

		return experimentItemDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(ExperimentItem model) {

		return experimentItemDao.delete(model);
	}

	@Override
	@Transactional
	public int update(ExperimentItem model) {

		return experimentItemDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public ExperimentItem get(ExperimentItem model) {

		return experimentItemDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentItem> findAll() {

		return experimentItemDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentItem> findByCondition(ExperimentItem model) {

		return experimentItemDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentItem> findByConditions(Map<String, Object> params) {

		return experimentItemDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentItem> findByConditionPage(ExperimentItem model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (model.getExpAccount() != null) {
			params.put("expAccount", model.getExpAccount().getId());
		}
		return experimentItemDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(ExperimentItem model) {

		return experimentItemDao.update(model);
	}

	@Override
	@Transactional
	public void batchSave(ExperimentAccount expAccount,
			List<ExperimentItem> experimentItems) {

		// 1.删除旧数据
		experimentItemDao.deleteByExpAccount(expAccount);
		// 2.插入新数据
		double t_period = 0.0;
		double t_operiod = 0.0;
		Integer t_courseNum = 0;
		Integer t_courseRepnum = 0;
		Integer t_classStunum = 0;
		double t_classTime = 0.0;
		double t_workload = 0.0;
		Iterator<ExperimentItem> it = experimentItems.iterator();
		while (it.hasNext()) {
			ExperimentItem expItem = it.next();
			t_period += expItem.getPeriod();
			t_operiod += expItem.getOperiod();
			t_courseNum += expItem.getCourseNum();
			t_courseRepnum += expItem.getCourseRepnum();
			t_classStunum += expItem.getClassStunum();
			t_classTime += expItem.getClassTime();
			t_workload += expItem.getWorkload();
			expItem.setId(new ObjectId().toString());
			expItem.setExpAccount(expAccount);
			experimentItemDao.insert(expItem);
		}
		expAccount.setPeriod(t_period);
		expAccount.setOperiod(t_operiod);
		expAccount.setCourseNum(t_courseNum);
		expAccount.setCourseRepnum(t_courseRepnum);
		expAccount.setClassStunum(t_classStunum);
		expAccount.setClassTime(t_classTime);
		expAccount.setWorkload(t_workload);
		// 3.更新工作量数据
		experimentAccountDao.updateWithItems(expAccount);

	}

}
