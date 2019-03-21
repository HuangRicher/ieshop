package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ShopBrandMapper;
import com.seamwhole.servicetradecore.model.ShopBrand;
import com.seamwhole.servicetradecore.model.ShopBrandExample;
import com.seamwhole.servicetradecore.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private ShopBrandMapper shopBrandMapper;
	
	
	public ShopBrand getById(Integer id){
		return shopBrandMapper.selectByPrimaryKey(id);
	}
	
	
	public List<ShopBrand> queryList(Map<String, Object> map){
		ShopBrandExample example=new ShopBrandExample();
		return shopBrandMapper.selectByExample(example);
	}

	@Override
	public PageInfo<ShopBrand> queryByPage(Integer pageSize, Integer pageNum, Map<String, Object> param, String order) {
		PageHelper.startPage(pageNum, pageSize);
		ShopBrandExample example = new ShopBrandExample();
		example.setOrderByClause(order);
		List<ShopBrand> list = shopBrandMapper.selectByExample(example);
		PageInfo<ShopBrand> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	public int queryTotal(Map<String, Object> map){
		ShopBrandExample example=new ShopBrandExample();
		return shopBrandMapper.countByExample(example);
	}
	
	
	public void save(ShopBrand brand){
		shopBrandMapper.insertSelective(brand);
	}
	
	
	public void update(ShopBrand brand){
		shopBrandMapper.updateByPrimaryKeySelective(brand);
	}
	
	
	public void delete(Integer id){
		shopBrandMapper.deleteByPrimaryKey(id);
	}
	
	
	public void deleteBatch(Integer[] ids){
		ShopBrandExample example=new ShopBrandExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		shopBrandMapper.deleteByExample(example);
	}
	
}
