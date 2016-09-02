/**
 * 
 */
package com.tccv.core.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tccv.core.dto.ResultPageDOHelper;
import com.tccv.core.util.ajax.AjaxUtils;

/**
 * 此类描述的是： controller父类
 * 
 * @author: chenshanben
 * @version: 2016年3月1日 下午2:50:07
 */
public class BaseController
{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 异常处理.不健全
	 *
	 * @param e the e
	 * @param response the response
	 */
	public void exceptionOperate(Exception e, HttpServletResponse response)
	{
		logger.error(getClass().getName(), e);
		AjaxUtils.renderJson(response, ResultPageDOHelper.getErrorResultDO("-1", e.getMessage()));
	}
}
