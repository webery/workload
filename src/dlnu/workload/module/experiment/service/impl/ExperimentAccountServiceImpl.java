package dlnu.workload.module.experiment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.exception.BusinessException;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.experiment.dao.ExpActTeacherDao;
import dlnu.workload.module.experiment.dao.ExperimentAccountDao;
import dlnu.workload.module.experiment.dao.ExperimentDao;
import dlnu.workload.module.experiment.dao.ExperimentItemDao;
import dlnu.workload.module.experiment.model.Experiment;
import dlnu.workload.module.experiment.model.ExperimentAccount;
import dlnu.workload.module.experiment.service.ExperimentAccountService;

@Service("experimentAccountService")
@Transactional
public class ExperimentAccountServiceImpl implements ExperimentAccountService {

	@Autowired
	private ExperimentAccountDao experimentAccountDao;
	@Autowired
	private ExperimentDao experimentDao;
	@Autowired
	private ExpActTeacherDao expActTeacherDao;
	@Autowired
	private ExperimentItemDao experimentItemDao;

	@Override
	@Transactional
	public int save(ExperimentAccount model) {
		Experiment experiment = experimentDao.selectOne(model.getExperiment());
		if (experiment == null) {
			throw new BusinessException("实验不存在!");
		}
		model.setExperimentName(experiment.getName());
		return experimentAccountDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(ExperimentAccount model) {
		// 1.
		experimentItemDao.deleteByExpAccount(model);
		// 2.
		expActTeacherDao.deleteByExpAccount(model);
		// 3.
		return experimentAccountDao.delete(model);
	}

	@Override
	@Transactional
	public int update(ExperimentAccount model) {

		return experimentAccountDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public ExperimentAccount get(ExperimentAccount model) {

		return experimentAccountDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentAccount> findAll() {

		return experimentAccountDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentAccount> findByCondition(ExperimentAccount model) {

		return experimentAccountDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentAccount> findByConditions(Map<String, Object> params) {

		return experimentAccountDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentAccount> findByConditionPage(ExperimentAccount model,
			PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("semester", model.getSemester());
		if (model.getCreator() != null) {
			params.put("creator", model.getCreator().getId());
		}
		return experimentAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(ExperimentAccount model) {

		return experimentAccountDao.updateStatus(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentAccount> findByCollege(String collegeId,
			ExperimentAccount experimentAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("college", collegeId);
		params.put("semester", experimentAccount.getSemester());

		return experimentAccountDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentAccount> findByCollegePage(String collegeId,
			ExperimentAccount experimentAccount, PageBounds pageBound) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("college", collegeId);
		params.put("semester", experimentAccount.getSemester());

		return experimentAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentAccount> findByMajor(String majorId,
			ExperimentAccount experimentAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", experimentAccount.getSemester());

		return experimentAccountDao.selectByConditions(params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentAccount> findByMajorPage(String majorId,
			ExperimentAccount experimentAccount, PageBounds pageBound) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("major", majorId);
		params.put("semester", experimentAccount.getSemester());

		return experimentAccountDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperimentAccount> findMajorExperimentAccount(String semester,
			String majorId) {
		// TODO Auto-generated method stub
		return null;
	}

}
