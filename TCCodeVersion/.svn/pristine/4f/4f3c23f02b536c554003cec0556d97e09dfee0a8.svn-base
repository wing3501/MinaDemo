/**
 * 
 */
package com.tccv.core.dao.mysql;

import java.util.List;
import java.util.Map;

/**   
 * 此类描述的是：   分页的基础mapper
 * @author: chenshanben   
 * @version: 2016年3月3日 下午2:04:54    
 */
public interface BasePageMapper<T> extends BaseMapper<T>
{
	
	/**
	 * 分页查询.
	 *
	 * @param p 分页查询请求参数
	 * @return 记录列表
	 */
	List<T> getPageList(Map<String, Object> param);
	
	/**
	 * 查询符合搜索条件的记录总数.
	 *
	 * @param p 查询请求参数
	 * @return 符合搜索条件的记录总数
	 */
	Long count(Map<String, Object> param);
}
