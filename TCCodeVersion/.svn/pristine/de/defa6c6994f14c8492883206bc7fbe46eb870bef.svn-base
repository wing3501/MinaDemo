package com.tccv.core.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * 通用集合相关封装.
 *
 * @author chenshanben
 */
public class CommonCollectionUtils
{
	
	
	/**
	 * Checks if is only.
	 *
	 * @param collection the collection
	 * @return true, if checks if is only
	 */
	public static boolean isOnly(Collection<?> collection)
	{
		return (collection != null && collection.size() == 1);
	}
	
	/**
	 * Checks if is not only.
	 *
	 * @param collection the collection
	 * @return true, if checks if is not only
	 */
	public static boolean isNotOnly(Collection<?> collection)
	{
		return (collection == null || collection.size() != 1);
	}
	
	/**
	 * Checks if is empty.
	 *
	 * @param collection the collection
	 * @return true, if checks if is empty
	 */
	public static boolean isEmpty(Collection<?> collection)
	{
		return (collection == null || collection.isEmpty());
	}
	
	/**
	 * Checks if is not empty.
	 *
	 * @param collection the collection
	 * @return true, if checks if is not empty
	 */
	public static boolean isNotEmpty(Collection<?> collection)
	{
		return !CommonCollectionUtils.isEmpty(collection);
	}
	
	/**
	 * Splict to long list.
	 *
	 * @param str the str
	 * @param split the split
	 * @return the list< long>
	 */
	public static List<Long> splictToLongList(String str, String split)
	{
		if (StringUtils.isBlank(str) || StringUtils.isBlank(split))
		{
			return new ArrayList<Long>(0);
		}
		Iterator<String> iter = Splitter.on(split).omitEmptyStrings().trimResults().split(str).iterator();
		List<Long> list = new ArrayList<Long>();
		while (iter.hasNext())
		{
			String next = iter.next();
			if (NumberUtils.isDigits(next))
			{
				list.add(Long.parseLong(next));
			}
		}
		return list;
	}
	
	/**
	 * 抽取集合中符合条件的.
	 *
	 * @param <T> the generic type
	 * @param collection the collection
	 * @param condition the condition
	 * @return the list< t>
	 * @author ChenJunhui
	 */
	public static <T> List<T> filterCollection(Collection<T> collection, FetchCondition<T> condition)
	{
		List<T> list = Lists.newArrayList();
		if (collection == null || collection.isEmpty())
		{
			return list;
		}
		for (T t : collection)
		{
			if (condition.needFetch(t))
			{
				list.add(t);
			}
		}
		return list;
	}
	
	/**
	 * 把集合collection中的对象的属性为field并且值in (eqValue)的数据提取出来.
	 *
	 * @param collection the collection
	 * @param field the field
	 * @param eqValue the eq value
	 * @return the collection<?>
	 */
	public static Collection<?> fetchByFieldIn(Collection<?> collection, String field, Object... eqValue)
	{
		Collection<Object> c = new ArrayList<Object>();
		if (collection != null && StringUtils.isNotBlank(field))
		{
			for (Object obj : collection)
			{
				if (obj != null)
				{
					try
					{
						Object fieldValue = PropertyUtils.getProperty(obj, field);
						for (Object eqv : eqValue)
						{
							if (fieldValue == null)
							{
								if (eqv == null)
								{
									c.add(obj);
									break;
								}
							}
							else
							{
								if (fieldValue.equals(eqv))
								{
									c.add(obj);
									break;
								}
							}
						}
					}
					catch (Exception e)
					{
						throw new RuntimeException(e);
					}
				}
			}
		}
		return c;
	}
	
	/**
	 * 把 str 按照换行回车符号转成list.
	 *
	 * @param str the str
	 * @return the list< string>
	 */
	public static List<String> spliteEnterToList(String str)
	{
		List<String> list = new ArrayList<String>();
		if (StringUtils.isBlank(str))
			return list;
		String[] arr = StringUtils.split(str.trim(), "\r\n");
		for (String s : arr)
		{
			if (StringUtils.isNotBlank(s))
			{
				String[] arr1 = StringUtils.split(s.trim(), "\n");
				for (String s1 : arr1)
				{
					if (StringUtils.isNotBlank(s1))
					{
						list.add(s1);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * Gets the string list.
	 *
	 * @param str the str
	 * @return the string list
	 */
	public static List<String> getStringList(String... str)
	{
		List<String> list = new ArrayList<String>();
		if (str == null || str.length == 0)
			return list;
		for (String s : str)
		{
			list.add(s);
		}
		return list;
	}
	
	/**
	 * 数组通过regex组合成string.
	 *
	 * @param list the list
	 * @param regex the regex
	 * @return the string
	 */
	public static String listToString(List<String> list, String regex)
	{
		StringBuilder result = new StringBuilder();
		
		for (String string : list)
		{
			result.append(string).append(regex);
		}
		
		result.deleteCharAt(result.length() - 1);
		
		return result.toString();
	}
	
	public static <T> void putObjectToCollect(Collection<T> set, T t)
		throws Exception
	{
		if (t != null)
		{
			set.add(t);
		}
		else
		{
			throw new Exception("需要添加到集合中的元素为null");
		}
	}
}
