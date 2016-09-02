/**
 * 
 */
package com.tccv.core.util.time;

/**
 * 此类描述的是：星期格式.
 *
 * @author: leiliang
 * @version: 2016年3月4日 上午11:41:51
 */
public enum Week
{
	
	/** The MONDAY. */
	MONDAY("星期一", "Monday", "Mon.", 1),
	
	/** The TUESDAY. */
	TUESDAY("星期二", "Tuesday", "Tues.", 2),
	
	/** The WEDNESDAY. */
	WEDNESDAY("星期三", "Wednesday", "Wed.", 3),
	
	/** The THURSDAY. */
	THURSDAY("星期四", "Thursday", "Thur.", 4),
	
	/** The FRIDAY. */
	FRIDAY("星期五", "Friday", "Fri.", 5),
	
	/** The SATURDAY. */
	SATURDAY("星期六", "Saturday", "Sat.", 6),
	
	/** The SUNDAY. */
	SUNDAY("星期日", "Sunday", "Sun.", 7);
	
	/** The name_cn. */
	String name_cn;
	
	/** The name_en. */
	String name_en;
	
	/** The name_en short. */
	String name_enShort;
	
	/** The number. */
	int number;
	
	/**
	 * The Constructor.
	 *
	 * @param name_cn the name_cn
	 * @param name_en the name_en
	 * @param name_enShort the name_en short
	 * @param number the number
	 */
	Week(String name_cn, String name_en, String name_enShort, int number)
	{
		this.name_cn = name_cn;
		this.name_en = name_en;
		this.name_enShort = name_enShort;
		this.number = number;
	}
	
	/**
	 * Gets the chinese name.
	 *
	 * @return the chinese name
	 */
	public String getChineseName()
	{
		return name_cn;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name_en;
	}
	
	/**
	 * Gets the short name.
	 *
	 * @return the short name
	 */
	public String getShortName()
	{
		return name_enShort;
	}
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber()
	{
		return number;
	}
}
