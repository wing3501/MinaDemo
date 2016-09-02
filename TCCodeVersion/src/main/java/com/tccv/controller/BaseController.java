/**   
* @Title: BaseController.java 
* @Package com.cn.styf.controller 
* @Description: TODO
* @author styf  
* @date 2015年12月13日 上午10:24:37   
*/
package com.tccv.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tccv.constants.ErrorCode;
import com.tccv.core.dto.ResultPageDOHelper;
import com.tccv.core.util.ajax.AjaxUtils;
import com.tccv.exception.CustomException;

/** 
* @ClassName: BaseController 
* @Description: TODO
* @author styf 
* @date 2015年12月13日 上午10:24:37  
*/
public abstract class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public void exceptionOperate(Exception e, HttpServletResponse response) {
		if (e instanceof CustomException) {
			AjaxUtils.renderJson(response,
					ResultPageDOHelper.getErrorResultDO(((CustomException) e).getResultCode(),
							((CustomException) e).getResultMessage()));
		} else {
			e.printStackTrace();
			AjaxUtils.renderJson(response,
					ResultPageDOHelper.getErrorResultDO(ErrorCode.E999999999.getErrorCode(), ErrorCode.E999999999.getZhMessage()));
		}
	}
}
