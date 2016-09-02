package com.tccv.core.util.page;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.tccv.core.util.number.ToNumberUtils;
import com.tccv.core.util.time.DateStyle;
import com.tccv.core.util.time.DateUtils;

/**
 * 此类描述的是： 分页工具类
 *
 * @param <T> the generic type
 * @author: chenshanben
 * @version: 2016年3月3日 下午12:01:32
 */
public class PageQueryUtils
{
	
	/* 分页默认条数 */
	public static final int DEFAULT_PAGE_SIZE = 20;
	
	/* 分页开始位置 */
	public static final String PAGE_NO = "page";
	
	/* 每页条数(分页偏移量) */
	public static final String PAGE_SIZE = "rows";
	
	/* 排序字段 */
	public static final String SORT_FIELD = "sortField";
	
	/* 排序类型 */
	public static final String SORT_ORDER = "sortOrder";
	
	public static final String PAGE = "page";
	
	public static final String ROWS = "rows";
	
	/**
	 * 获取easy-ui页面请求中的数据.
	 *
	 * @param request 请求
	 * @return the map
	 */
	public static Map<String,Object> builderQueryMap(HttpServletRequest request){
		Map<String, String[]> requestMap = request.getParameterMap();
		Set<String> set = requestMap.keySet();
		Map<String,Object> param = Maps.newHashMapWithExpectedSize(set.size());
		/*for (Object key : set) {
			String[] values = (String[]) requestMap.get(key);
			if(values.length==1&&)
		}*/
		for (Iterator<String> iter = set.iterator(); iter.hasNext();) {  
			String key = (String) iter.next();
			String[] values = (String[]) requestMap.get(key);
			if(!ArrayUtils.isEmpty(values)){
				if(values.length==1){
					if(StringUtils.isNotBlank(values[0])){
						if(key.equals("page")||key.equals("rows")||key.equals("id")||key.equals("orgId")){
							Integer value;
							if(key.equals("page")){
								value = (Integer.valueOf(values[0])-1)*(Integer.valueOf(request.getParameter("rows")));
							}else{
								value = Integer.valueOf(values[0]);
							}
							param.put(key, value);
						}else if(key.equals("endDate")){
							String value = values[0];
							Date nextDay = DateUtils.nextDay(DateUtils.StringToDate(value, DateStyle.YYYY_MM_DD));
							param.put(key, DateUtils.DateToString(nextDay, DateStyle.YYYY_MM_DD));
						}else{
							String value = values[0];
							param.put(key, value);
						}
					}
				}else{
					List<Object> list = new ArrayList<Object>();
					for(String value:values){
						if(StringUtils.isNotBlank(value)){
							Object _value = value;
							if(_value!=null){
								list.add(_value);
							}
						}
					}
					param.put(key, list);
				}
			}
		}
		return param;
	}
	
	/**
	 * 组装分页查询参数 easyUI
	 * 
	 * @param <T>
	 * @param request
	 * @param pageSize
	 * @param pageKey
	 * @return
	 */
	public static <T> PageQueryParam<T> preparePageQueryParam(HttpServletRequest request)
	{
		PageQueryParam<T> query = new PageQueryParam<T>();
		
		Integer pageNO = ToNumberUtils
				.getIntegerValue(request.getParameter(PAGE) == null ? Integer.valueOf(1) : request.getParameter(PAGE));
				
		Integer pageSize = ToNumberUtils.getIntegerValue(request.getParameter(ROWS));
		if ((pageSize == null) || (pageSize.intValue() < 1))
		{
			pageSize = Integer.valueOf(DEFAULT_PAGE_SIZE);
		}
		
		int rowStart = (pageNO.intValue() - 1) * pageSize.intValue();
		
		String sortField = request.getParameter(SORT_FIELD);
		if (StringUtils.isEmpty(sortField))
		{
			sortField = "id";
		}
		String sortOrder = request.getParameter(SORT_ORDER);
		
		query.setPageIndex(Integer.valueOf(rowStart));
		query.setPageSize(pageSize);
		if (!StringUtils.isBlank(sortField))
		{
			query.setSortField(sortField);
		}
		
		if (!StringUtils.isBlank(sortField))
		{
			query.setSortOrder(sortOrder);
		}
		
		return query;
	}
	
	/**
	 * 将请求转分页对象
	 * 
	 * @param <T>
	 * @param request
	 * @return
	 */
	public static <T> PageQueryParam<T> preparePage(HttpServletRequest request)
	{
		return preparePageQueryParam(request);
	}
	
}
