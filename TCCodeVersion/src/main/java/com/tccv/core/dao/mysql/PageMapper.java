/**
 * 
 */
package com.tccv.core.dao.mysql;

import java.util.List;
import java.util.Map;

/**
 * 此类描述的是：共用的分页查询方法<br>
 * T:表的映射对象<br>
 * K:一版为主键的对象类型，一版为Long<br>
 * P：分页查询对象类型<br>
 * 
 * @author: leiliang
 * @version: 2015年12月18日 上午11:16:59
 */
public interface PageMapper<T, K, P> extends BaseMapper<T>
{
	
	/**
	 * 分页查询.
	 *
	 * @param p 分页查询请求参数
	 * @return 记录列表
	 */
	List<T> getPageList(P p);
	
	/**
	 * 分页查询.
	 *
	 * @param param 分页查询请求参数
	 * @return 记录列表
	 */
	List<T> getPageListByMap(Map<String, Object> param);
	
	/**
	 * 查询符合搜索条件的记录总数.
	 *
	 * @param p 查询请求参数
	 * @return 符合搜索条件的记录总数
	 */
	Long count(P p);
	
	/**
	 * 查询符合搜索条件的记录总数.
	 *
	 * @param p 查询请求参数
	 * @return 符合搜索条件的记录总数
	 */
	Long countByMap(Map<String, Object> param);
}
