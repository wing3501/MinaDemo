/**
 * 
 */
package com.tccv.core.service.mysql.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tccv.core.dao.mysql.BasePageMapper;
import com.tccv.core.entity.BaseEntity;
import com.tccv.core.service.mysql.BaseEUPageService;
import com.tccv.core.util.page.Page;
import com.tccv.core.util.page.PageQueryParam;
import com.tccv.core.util.page.PageQueryUtils;

/**   
 * 此类描述的是：   
 * @author: chenshanben   
 * @version: 2016年3月3日 下午12:01:32    
 */
public class BaseEUPageServiceImpl<T extends BaseEntity, M extends BasePageMapper<T>> extends BaseServiceImpl<T, M> implements BaseEUPageService<T>
{

	public Page<T> getPageList(HttpServletRequest request)
	{
		Map<String,Object> param = PageQueryUtils.builderQueryMap(request);
		PageQueryParam<T> page = PageQueryUtils.preparePage(request);
		List<T> list = mapper.getPageList(param);
		Long count = mapper.count(param);
		return new Page<T>(list, page.getPageIndex(), page.getPageSize(), count);
	}
	
}
