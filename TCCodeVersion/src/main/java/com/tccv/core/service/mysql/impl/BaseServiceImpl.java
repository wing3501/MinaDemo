package com.tccv.core.service.mysql.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tccv.core.dao.mysql.BaseMapper;
import com.tccv.core.entity.BaseEntity;
import com.tccv.core.service.mysql.BaseService;

/**
 * 此类描述的是：   service基础类实现类.
 *
 * @param <T> the generic type
 * @param <K> the key type
 * @param <M> the generic type
 * @author: chenshanben
 * @version: 2016年3月1日 下午1:57:23
 */
public class BaseServiceImpl<T extends BaseEntity, M extends BaseMapper<T>> implements BaseService<T>
{
	
	/** The logger. */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/** The df. */
	protected SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/** The mapper. */
	@Autowired
	protected M mapper;

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 */
	public int save(T t)
	{
		Date now = new Date();
		t.setCreateTime(now);
		t.setUpdateTime(now);
		t.setIsDelete(0);
		return mapper.insertSelective(t);
	}

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @throws Exception 
	 */
	public T find(Long id) throws Exception
	{
		T t = mapper.selectByPrimaryKey(id);
		
		//判断是否为空
		if(t == null){
			throw new Exception("通过主键查询出的实体为空，请检查您的传参");
		}
		return t;
	}
	
	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 */
	public int delete(Long id){
		return mapper.deleteByPrimaryKey(id);
	}

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 */
	public int update(T t)
	{
		t.setUpdateTime(new Date());
		return mapper.updateByPrimaryKeySelective(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int deleteByLogic(Long id)
	{
		//通过反射创建t实体
		Type type = getClass().getGenericSuperclass();
		Type[] generics = ((ParameterizedType) type).getActualTypeArguments();
		Class<T> tClass = (Class<T>) (generics[0]);
		T t = null;
		try
		{
			t = tClass.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//逻辑删除
		t.setIsDelete(1);
		t.setId(id);
		return this.update(t);
	}

	public int saveOrUpdate(T t)
	{
		if(null!=t.getId()&&0<t.getId()){
			return update(t);
		}else{
			return save(t);
		}
	}
}
