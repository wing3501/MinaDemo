/**
 * 
 */
package com.tccv.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tccv.dao.AppVersionMapper;
import com.tccv.pojo.AppVersion;
import com.tccv.pojo.AppVersionExample;

/**   
 * 此类描述的是：   
 * @author: styf   
 * @version: 2016年4月15日 上午11:44:53    
 */
@Service
public class AppVersionService
{
	@Autowired
	private AppVersionMapper appVersionMapper;
	
	
	public int save(AppVersion appVersion){
		return appVersionMapper.insertSelective(appVersion);
	}


	/**
	 * @param appKey
	 * @return
	 */
	public List<AppVersion> selectByAppKey(String appKey)
	{
		AppVersionExample example = new AppVersionExample();
		AppVersionExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeleteEqualTo(0);
		criteria.andAppKeyEqualTo(appKey);
		example.setOrderByClause("update_time desc");
		
		return appVersionMapper.selectByExample(example);
	}


	/**
	 * @param appVersion
	 * @return
	 */
	public List<AppVersion> selectByAppVersion(AppVersion appVersion)
	{
		AppVersionExample example = new AppVersionExample();
		AppVersionExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeleteEqualTo(0);
		criteria.andAppNameEqualTo(appVersion.getAppName());
		if(StringUtils.isNotBlank(appVersion.getAppVersion()))
			criteria.andAppVersionEqualTo(appVersion.getAppVersion());
		example.setOrderByClause("update_time desc");
		
		return appVersionMapper.selectByExample(example);
	}


	/**
	 * @param appName
	 * @return
	 */
	public List<AppVersion> selectByAppName(String appName)
	{
		AppVersionExample example = new AppVersionExample();
		AppVersionExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeleteEqualTo(0);
		criteria.andIsReleasedEqualTo(1);
		criteria.andAppNameEqualTo(appName);
		example.setOrderByClause("update_time desc");
		
		return appVersionMapper.selectByExample(example);
	}

	public List<AppVersion> selectHistoryByAppName(String appName)
	{
		AppVersionExample example = new AppVersionExample();
		AppVersionExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeleteEqualTo(0);
		criteria.andAppNameEqualTo(appName);
		example.setOrderByClause("update_time desc");
		
		return appVersionMapper.selectByExample(example);
	}
	/**
	 * @return
	 */
	public List<AppVersion> selectAllApp()
	{
		return appVersionMapper.selectAllApp();
	}


	/**
	 * @param appVersion
	 * @return
	 */
	public List<AppVersion> selectByAppNameAndVersion(AppVersion appVersion)
	{
		AppVersionExample example = new AppVersionExample();
		AppVersionExample.Criteria criteria = example.createCriteria();
		criteria.andIsDeleteEqualTo(0);
		criteria.andAppNameEqualTo(appVersion.getAppName());
		criteria.andAppVersionEqualTo(appVersion.getAppVersion());
		example.setOrderByClause("update_time desc");
		return appVersionMapper.selectByExample(example);
	}


	/**
	 * @param appVersion
	 */
	public int updateById(AppVersion appVersion)
	{
		return appVersionMapper.updateByPrimaryKey(appVersion);
	}
}
