/**
 * 
 */
package com.tccv.core.dto;

import java.util.List;

/**
 * 此类描述的是：分页查询共用返回结果.
 *
 * @param <T> the generic type
 * @author: leiliang
 * @version: 2016年2月25日 下午3:06:54
 */
public class PageQuestRsp<T>
{
	
	/** 总数. */
	private int totalAmount;
	
	/** 是否有下一页. */
	private Boolean hasNext;
	
	/** The data. */
	private List<T> data;
	
	/**
	 * The Constructor.
	 *
	 * @param totalAmount the total amount
	 * @param data the data
	 * @param pageNo the page no
	 * @param pageSize the page size
	 */
	public PageQuestRsp(int totalAmount, List<T> data, int pageNo, int pageSize)
	{
		super();
		this.totalAmount = totalAmount;
		this.data = data;
		this.hasNext = isHasNext(pageNo, pageSize);
	}
	
	/**
	 * Checks if is has next.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return true, if checks if is has next
	 */
	private boolean isHasNext(int pageNo, int pageSize)
	{
		if (totalAmount < 0)
		{
			return false;
		}
		
		int count = (totalAmount % pageSize == 0) ? totalAmount / pageSize : totalAmount / pageSize + 1;
		
		return (pageNo + 1 <= count);
	}
	
	/**
	 * Gets the total amount.
	 *
	 * @return the total amount
	 */
	public int getTotalAmount()
	{
		return totalAmount;
	}
	
	/**
	 * Sets the total amount.
	 *
	 * @param totalAmount the total amount
	 */
	public void setTotalAmount(int totalAmount)
	{
		this.totalAmount = totalAmount;
	}
	
	/**
	 * Gets the has next page.
	 *
	 * @return the checks for next page
	 */
	public Boolean getHasNext()
	{
		return hasNext;
	}
	
	/**
	 * Sets the has next page.
	 *
	 * @param hasNextPage the checks for next page
	 */
	public void setHasNext(Boolean hasNext)
	{
		this.hasNext = hasNext;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<T> getData()
	{
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the data
	 */
	public void setData(List<T> data)
	{
		this.data = data;
	}
	
	/**
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("PageQuestRsp [totalAmount=");
		builder.append(totalAmount);
		builder.append(", hasNextPage=");
		builder.append(hasNext);
		builder.append(", data=");
		builder.append(data);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
