package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.Category;

import java.util.List;
import java.util.Map;


public interface CategoryService {
	
	Category queryObject(Integer id);
	
	List<Category> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Category category);
	
	void update(Category category);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
}
