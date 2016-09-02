/**
 * 
 */
package com.tccv.core.dto;

import java.util.Date;

/**   
 * 此类描述的是：   分页查询条件
 * @author: leiliang   
 * @version: 2016年2月18日 上午9:48:22    
 */
public class PagingQuestReq
{
	/** 起始记录数. */
	private Integer rowStart = 0;
	
	/** 第几页. */
	private Integer page = 1;
	
	/** 每页记录数， 为-1时代表查询所有. */
	private Integer rows = 10;
	
	/** 排序字段(默认排序字段为：update_time). */
	private String sort = "update_time";
	
	/** 排序方式：(升序:asc,降序：desc)默认降序. */
	private String order = "desc";
	
	/** 开始时间. */
	private Date startTime;
	
	/** 结束时间. */
	private Date endTime;
	
	/**
	 * @return the startTime
	 */
	public Date getStartTime()
	{
		return startTime;
	}
	
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}
	
	/**
	 * @return the endTime
	 */
	public Date getEndTime()
	{
		return endTime;
	}
	
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}
	
	/**
	 * @return the rowStart
	 */
	public Integer getRowStart()
	{
		return rowStart;
	}
	
	/**
	 * @param rowStart the rowStart to set
	 */
	public void setRowStart(Integer rowStart)
	{
		this.rowStart = rowStart;
	}
	
	
	/**
	 * @return the sort
	 */
	public String getSort()
	{
		return sort;
	}
	
	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort)
	{
		this.sort = sort;
	}
	
	
	/**
	 * @return the order
	 */
	public String getOrder()
	{
		return order;
	}

	
	/**
	 * @param order the order to set
	 */
	public void setOrder(String order)
	{
		this.order = order;
	}

	
	/**
	 * @return the page
	 */
	public Integer getPage()
	{
		return page;
	}

	
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page)
	{
		this.page = page;
	}

	
	/**
	 * @return the rows
	 */
	public Integer getRows()
	{
		return rows;
	}

	
	/**
	 * @param rows the rows to set
	 */
	public void setRows(Integer rows)
	{
		this.rows = rows;
	}

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("PagingQuest [rowStart=").append(rowStart)
			   .append(", page=").append(page).append(", rows=").append(rows)
			   .append(", sort=").append(sort).append(", order=").append(order)
			   .append(", startTime=").append(startTime).append(", endTime=")
			   .append(endTime).append("]");
		return builder.toString();
	}

}

