/**
 * 
 */
package com.tccv.core.util.page;

import java.util.List;

/**   
 * 此类描述的是：   
 * @author: chenshanben   
 * @version: 2016年4月13日 下午4:30:41    
 */
public class PageMetro<T>
{
	private Long recordsTotal;
	private Long recordsFiltered;
	private List<T> aaData;
	
	
	
	public PageMetro(Long recordsTotal, Long recordsFiltered, List<T> aaData)
	{
		super();
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.aaData = aaData;
	}

	public PageMetro()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getRecordsTotal()
	{
		return recordsTotal;
	}
	
	public void setRecordsTotal(Long recordsTotal)
	{
		this.recordsTotal = recordsTotal;
	}
	
	public Long getRecordsFiltered()
	{
		return recordsFiltered;
	}
	
	public void setRecordsFiltered(Long recordsFiltered)
	{
		this.recordsFiltered = recordsFiltered;
	}
	
	public List<T> getAaData()
	{
		return aaData;
	}
	
	public void setAaData(List<T> aaData)
	{
		this.aaData = aaData;
	}
	
	
}
