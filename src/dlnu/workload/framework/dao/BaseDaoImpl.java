package dlnu.workload.framework.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dlnu.workload.framework.page.domain.PageBounds;

public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

	protected String mapper;

	@Override
	public int insert(T model) {
		return this.getSqlSession().insert(mapper + "insert", model);
	}

	@Override
	public int delete(T model) {
		return this.getSqlSession().delete(mapper + "delete", model);
	}

	@Override
	public int update(T model) {
		return this.getSqlSession().update(mapper + "update", model);
	}

	@Override
	public T selectOne(T model) {
		return this.getSqlSession().selectOne(mapper + "selectOne", model);
	}

	@Override
	public List<T> selectAll() {
		return this.getSqlSession().selectList(mapper + "selectAll");
	}

	@Override
	public List<T> selectByCondition(T model) {
		return this.getSqlSession().selectList(mapper + "selectByConditions",
				model);
	}

	@Override
	public List<T> selectByConditions(Map<String, Object> params) {
		return this.getSqlSession().selectList(mapper + "selectByConditions",
				params);
	}

	@Override
	public List<T> selectByConditionPage(Map<String, Object> params,
			PageBounds pageBound) {
		return this.getSqlSession().selectList(
				mapper + "selectByConditionPage", params, pageBound);
	}

	@Override
	public int updateStatus(T model) {
		return this.getSqlSession().update(mapper + "updateStatus", model);
	}

}
