/**
 * 
 */
package com.tccv.core.dao.mysql;

/**
 * 此类描述的是：共用的dao层接口，提供一版 的增删改查方法<br>
 * T:表的映射对象<br>
 * K:一版为主键的对象类型，一版为Long<br>
 * E:查询条件实例.
 *
 * @param <T> the generic type
 * @param <K> the key type
 * @author: chenshanben
 * @version: 2015年3月18日 上午11:11:36
 */
public interface BaseMapper<T>
{
	
    /**
     * 通过主键删除
     *
     * @param k the k
     * @return the int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入.
     *
     * @param t the t
     * @return the int
     */
    int insert(T t);

    /**
     * 安全的插入.
     *
     * @param t the t
     * @return the int
     */
    int insertSelective(T t);

    /**
     * 通过主键查找.
     *
     * @param k the k
     * @return the T
     */
    T selectByPrimaryKey(Long id);

    /**
     * 安全的通过主键更新.
     *
     * @param t the t
     * @return the int
     */
    int updateByPrimaryKeySelective(T t);

    /**
     * 无用.
     *
     * @param t the t
     * @return the int
     */
    int updateByPrimaryKeyWithBLOBs(T t);

    /**
     * 不安全的更新.
     *
     * @param t the t
     * @return the int
     */
    int updateByPrimaryKey(T t);
}

