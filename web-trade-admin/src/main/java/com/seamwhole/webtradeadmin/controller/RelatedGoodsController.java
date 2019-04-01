package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.RelatedGoods;
import com.seamwhole.webtradeadmin.service.RelatedGoodsService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("relatedgoods")
public class RelatedGoodsController {
	@Autowired
	private RelatedGoodsService relatedGoodsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("relatedgoods:list")
	public ResponseObject list(@RequestParam Map<String, Object> params){
		//查询列表数据
		PagesInfo<RelatedGoods> page=relatedGoodsService.queryByPage(params);
		return ResponseObject.ok().put("page", page);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("relatedgoods:info")
	public ResponseObject info(@PathVariable("id") Integer id){
		RelatedGoods relatedGoods = relatedGoodsService.queryObject(id);
		return ResponseObject.ok().put("relatedGoods", relatedGoods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("relatedgoods:save")
	public ResponseObject save(@RequestBody RelatedGoods relatedGoods){
		relatedGoodsService.save(relatedGoods);
		return ResponseObject.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("relatedgoods:update")
	public ResponseObject update(@RequestBody RelatedGoods relatedGoods){
		relatedGoodsService.update(relatedGoods);
		return ResponseObject.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("relatedgoods:delete")
	public ResponseObject delete(@RequestBody Integer[] ids){
		relatedGoodsService.deleteBatch(ids);
		return ResponseObject.ok();
	}
	
}
