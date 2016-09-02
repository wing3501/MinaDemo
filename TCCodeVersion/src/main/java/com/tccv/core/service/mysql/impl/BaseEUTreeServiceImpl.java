/**
 * 
 */
package com.tccv.core.service.mysql.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.tccv.core.dao.mysql.BaseTreeMapper;
import com.tccv.core.dto.EasyUiTreeEntity;
import com.tccv.core.entity.BaseTreeEntity;
import com.tccv.core.service.mysql.BaseEUTreeService;

/**
 * 此类描述的是：
 * 
 * @author: chenshanben
 * @version: 2016年3月23日 上午9:27:36
 */
@Transactional(rollbackFor = Exception.class)
public class BaseEUTreeServiceImpl<T extends BaseTreeEntity, M extends BaseTreeMapper<T>>
								  extends BaseEUPageServiceImpl<T, M> implements BaseEUTreeService<T>
{
	
	@Override
	public List<EasyUiTreeEntity> trees()
	{
		
		//
		//List<T> itemList = mapper.getChildren(0l);
		
		// 顶级节点
		List<T> roots = mapper.getChildren(0l);
		
		// 将最顶级信息转为EasyUiTreeDO
		List<EasyUiTreeEntity> tree = Lists.newArrayListWithCapacity(roots.size());
		for (T item : roots)
		{
			EasyUiTreeEntity menuTree = new EasyUiTreeEntity();
			menuTree.setId(item.getId());
			menuTree.setName(item.getName());
			menuTree.setSort(item.getSort());
			menuTree.setImage(item.getImage());
			menuTree.setLevel(item.getLevel());
			tree.add(menuTree);
		}
		
		// 为每个子节点无限递归出树形结构
		List<EasyUiTreeEntity> endTree = Lists.newArrayList();
		for (EasyUiTreeEntity eachRootTree : tree)
		{
			EasyUiTreeEntity eachTree = this.fn(eachRootTree);
			endTree.add(eachTree);
		}
		
		return endTree;
	}
	
	/**
	 * 递归无限极树:在外面循环每个顶级节点时调用此递归 eachRootTree:每个顶级节点 all:所有参与递归列表
	 * 
	 * @param request
	 * @param response
	 */
	private EasyUiTreeEntity fn(EasyUiTreeEntity eachRootTree)
	{
		
		// 得到eachRootTree的子节点
		List<T> childList = mapper.getChildren(eachRootTree.getId());
		
		// 如果子节点不为空继续递归
		if (childList.size() > 0)
		{
			List<EasyUiTreeEntity> newTreeList = Lists.newArrayList();
			for (T pCategory : childList)
			{
				EasyUiTreeEntity tree = new EasyUiTreeEntity();
				tree.setId(pCategory.getId());
				tree.setName(pCategory.getName());
				tree.setSort(pCategory.getSort());
				tree.setImage(pCategory.getImage());
				tree.setLevel(pCategory.getLevel());
				fn(tree);
				newTreeList.add(tree);
			}
			eachRootTree.setChildren(newTreeList);
		}
		return eachRootTree;
	}
}
