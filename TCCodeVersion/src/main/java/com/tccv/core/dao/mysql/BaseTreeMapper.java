package com.tccv.core.dao.mysql;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public interface BaseTreeMapper<T> extends BasePageMapper<T>
{
	
	/**
	 * 通过父id得到子列表.
	 *
	 * @param id the id
	 * @return the list by parent id
	 */
	List<T> getChildren(@PathVariable("id")Long id);
}
