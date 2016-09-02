package com.tccv.core.service.mysql;

/**
 * 此类描述的是： service基础类.
 *
 * @param <T> the generic type
 * @param <K> the key type
 * @author: chenshanben
 * @version: 2016年3月1日 下午1:57:23
 */
public interface BaseService<T>
{
	
	/**
	 * 保存.
	 *
	 * @param t the t
	 * @return the int
	 */
	public int save(T t);
	
	/**
	 * 查找.
	 *
	 * @param k the k
	 * @return the T
	 * @throws Exception 
	 */
	public T find(Long id) throws Exception;
	
	/**
	 * 物理删除.
	 *
	 * @param k the k
	 * @return the int
	 */
	public int delete(Long id);
	
	/**
	 * 逻辑删除.
	 *
	 * @param k the k
	 * @return the int
	 */
	public int deleteByLogic(Long id);
	
	/**
	 * 更新.
	 *
	 * @param t the t
	 * @return the int
	 */
	public int update(T t);
	
	/**
	 * 若id存在则为更新，不存在为保存
	 *
	 * @param t the t
	 * @return the int
	 */
	public int saveOrUpdate(T t);
}
