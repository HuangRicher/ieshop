package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.GoodsAttribute;
import com.seamwhole.webtradeadmin.service.GoodsAttributeService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/goodsattribute")
public class GoodsAttributeController {
	@Autowired
	private GoodsAttributeService goodsAttributeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("goodsattribute:list")
	public ResponseObject list(@RequestParam Map<String, Object> params){
		//查询列表数据
		PagesInfo<GoodsAttribute> page=goodsAttributeService.queryByPage(params);
		
		return ResponseObject.ok().put("page", page);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goodsattribute:info")
	public ResponseObject info(@PathVariable("id") Integer id){
		GoodsAttribute goodsAttribute = goodsAttributeService.queryObject(id);
		
		return ResponseObject.ok().put("goodsAttribute", goodsAttribute);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goodsattribute:save")
	public ResponseObject save(@RequestBody GoodsAttribute goodsAttribute){
		goodsAttributeService.save(goodsAttribute);
		
		return ResponseObject.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goodsattribute:update")
	public ResponseObject update(@RequestBody GoodsAttribute goodsAttribute){
		goodsAttributeService.update(goodsAttribute);
		
		return ResponseObject.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goodsattribute:delete")
	public ResponseObject delete(@RequestBody Integer[] ids){
		goodsAttributeService.deleteBatch(ids);
		
		return ResponseObject.ok();
	}
	
}
