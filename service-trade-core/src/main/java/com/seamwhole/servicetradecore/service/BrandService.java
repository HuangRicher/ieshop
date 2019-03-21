package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ShopBrand;

import java.util.List;
import java.util.Map;


public interface BrandService {

	ShopBrand getById(Integer id);
	
	List<ShopBrand> queryList(Map<String, Object> map);

	PageInfo<ShopBrand> queryByPage(Integer pageSize, Integer pageNum, Map<String, Object> param, String order);

	int queryTotal(Map<String, Object> map);
	
	void save(ShopBrand brand);
	
	void update(ShopBrand brand);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
}
