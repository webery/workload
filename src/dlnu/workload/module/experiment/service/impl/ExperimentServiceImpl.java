package dlnu.workload.module.experiment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.exception.BusinessException;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.experiment.dao.ExperimentDao;
import dlnu.workload.module.experiment.model.Experiment;
import dlnu.workload.module.experiment.service.ExperimentService;

@Service("experimentService")
@Transactional
public class ExperimentServiceImpl implements ExperimentService {

	@Autowired
	private ExperimentDao experimentDao;

	@Override
	@Transactional
	public int save(Experiment model) {

		Experiment experiment = experimentDao
				.selectByCollegeAndName(new Experiment(model.getCollege(),
						model.getName()));
		if (experiment != null) {
			throw new BusinessException("同一学院不能存在同名实验!");
		}
		return experimentDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(Experiment model) {
		return experimentDao.delete(model);
	}

	@Override
	@Transactional
	public int update(Experiment model) {
		Experiment experiment = experimentDao
				.selectByCollegeAndName(new Experiment(model.getCollege(),
						model.getName()));
		if (experiment != null && !experiment.getId().equals(model.getId())) {
			throw new BusinessException("同一学院不能存在同名实验!");
		}

		return experimentDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Experiment get(Experiment model) {
		return experimentDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Experiment> findAll() {
		return experimentDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Experiment> findByCondition(Experiment model) {
		return experimentDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Experiment> findByConditionPage(Experiment model,
			PageBounds pageBound) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", model.getName());
		if (model.getCollege() != null) {
			params.put("college", model.getCollege().getId());
		}

		return experimentDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(Experiment model) {
		return experimentDao.updateStatus(model);
	}

	@Override
	public List<Experiment> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
