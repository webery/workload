package dlnu.workload.module.practice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dlnu.workload.framework.exception.BusinessException;
import dlnu.workload.framework.page.domain.PageBounds;
import dlnu.workload.module.practice.dao.CourseDesignDao;
import dlnu.workload.module.practice.model.CourseDesign;
import dlnu.workload.module.practice.service.CourseDesignService;

@Service("courseDesignService")
@Transactional
public class CourseDesignServiceImpl implements CourseDesignService {

	@Autowired
	private CourseDesignDao courseDesignDao;

	@Override
	@Transactional
	public int save(CourseDesign model) {

		CourseDesign courseDesign = courseDesignDao
				.selectByCollegeAndName(model);
		if (courseDesign != null) {
			throw new BusinessException("同一学院不能存在同名课程设计!");
		}
		return courseDesignDao.insert(model);
	}

	@Override
	@Transactional
	public int delete(CourseDesign model) {

		return courseDesignDao.delete(model);
	}

	@Override
	@Transactional
	public int update(CourseDesign model) {

		CourseDesign courseDesign = courseDesignDao
				.selectByCollegeAndName(model);

		if (courseDesign != null && !courseDesign.getId().equals(model.getId())) {
			throw new BusinessException("同一学院不能存在同名课程设计!");
		}

		return courseDesignDao.update(model);
	}

	@Override
	@Transactional(readOnly = true)
	public CourseDesign get(CourseDesign model) {

		return courseDesignDao.selectOne(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseDesign> findAll() {

		return courseDesignDao.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseDesign> findByCondition(CourseDesign model) {

		return courseDesignDao.selectByCondition(model);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CourseDesign> findByConditionPage(CourseDesign model,
			PageBounds pageBound) {

		Map<String, Object> params = new HashMap<String, Object>();
		if (model.getCollege() != null) {
			params.put("college", model.getCollege().getId());
		}
		params.put("name", model.getName());

		return courseDesignDao.selectByConditionPage(params, pageBound);
	}

	@Override
	@Transactional
	public int updateStatus(CourseDesign model) {

		return courseDesignDao.updateStatus(model);
	}

	@Override
	@Transactional(readOnly = true)
	public CourseDesign findByCollegeAndName(CourseDesign courseDesign) {
		return courseDesignDao.selectByCollegeAndName(courseDesign);
	}

	@Override
	public List<CourseDesign> findByConditions(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
