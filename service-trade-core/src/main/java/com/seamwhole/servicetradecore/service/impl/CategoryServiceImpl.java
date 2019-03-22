package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.CategoryMapper;
import com.seamwhole.servicetradecore.model.Category;
import com.seamwhole.servicetradecore.model.CategoryExample;
import com.seamwhole.servicetradecore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	
	
	public Category queryObject(Integer id){
		return categoryMapper.selectByPrimaryKey(id);
	}
	
	
	public List<Category> queryList(Map<String, Object> map){
		CategoryExample example=new CategoryExample();
		CategoryExample.Criteria criteria=example.createCriteria();
		if(map.get("parent_id")!=null)
			criteria.andParentIdEqualTo((int)(map.get("parent_id")));
		if(map.get("notName")!=null)
			criteria.andNameNotEqualTo((String)map.get("notName"));
		if(map.get("ids")!=null)
			criteria.andIdIn(Arrays.asList((Integer[])(map.get("ids"))));
		return categoryMapper.selectByExample(example);
	}
	
	
	public int queryTotal(Map<String, Object> map){
		CategoryExample example=new CategoryExample();
		return categoryMapper.countByExample(example);
	}
	
	
	public void save(Category category){
		categoryMapper.insertSelective(category);
	}
	
	
	public void update(Category category){
		categoryMapper.updateByPrimaryKeySelective(category);
	}
	
	
	public void delete(Integer id){
		categoryMapper.deleteByPrimaryKey(id);
	}
	
	
	public void deleteBatch(Integer[] ids){
		CategoryExample example=new CategoryExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		categoryMapper.deleteByExample(example);
	}
	
}
