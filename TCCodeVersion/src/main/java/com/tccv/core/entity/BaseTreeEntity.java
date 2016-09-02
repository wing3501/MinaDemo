/**
 * 
 */
package com.tccv.core.entity;


/**   
 * 此类描述的是：   
 * @author: chenshanben   
 * @version: 2016年3月23日 上午9:29:06    
 */
public class BaseTreeEntity extends BaseEntity
{
	private String name;
	private String image;
	private Integer sort;
	private Integer level;
	
	
	
	public Integer getLevel()
	{
		return level;
	}


	
	public void setLevel(Integer level)
	{
		this.level = level;
	}


	public String getImage()
	{
		return image;
	}

	
	public void setImage(String image)
	{
		this.image = image;
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	


	
	public Integer getSort()
	{
		return sort;
	}

	
	public void setSort(Integer sort)
	{
		this.sort = sort;
	}

	public BaseTreeEntity()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
