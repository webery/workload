package dlnu.workload.framework.dao;

import java.util.List;
import java.util.Map;

import dlnu.workload.framework.page.domain.PageBounds;

public interface BaseDao<T> {
	/**
	 * 插入一条数据
	 * 
	 * @param model
	 * @return
	 */
	int insert(T model);

	/**
	 * 删除一条数据
	 * 
	 * @param model
	 * @return
	 */
	int delete(T model);

	/**
	 * 跟新一条数据
	 * 
	 * @param model
	 * @return
	 */
	int update(T model);

	/**
	 * 查询一条数据
	 * 
	 * @param model
	 * @return
	 */
	T selectOne(T model);

	/**
	 * 查询全部的数据
	 * 
	 * @return
	 */
	List<T> selectAll();

	/**
	 * 条件查询所有的数据(不分页)
	 * 
	 * @param model
	 * @return
	 */
	List<T> selectByCondition(T model);

	List<T> selectByConditions(Map<String, Object> params);

	/**
	 * 条件查询的数据(分页)
	 * 
	 * @param params
	 * @param pageBound
	 * @return
	 */
	List<T> selectByConditionPage(Map<String, Object> params,
			PageBounds pageBound);

	/**
	 * 根据id修改状态
	 * 
	 * @param course
	 *            : id:课id status:需要改成的状态
	 * @return
	 */
	int updateStatus(T model);

}
