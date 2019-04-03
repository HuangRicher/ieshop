package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopCollect;
import com.seamwhole.webtradeadmin.info.ShopCollectDO;
import com.seamwhole.webtradeadmin.service.CollectService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("collect")
public class CollectController {

	@Autowired
	private CollectService collectService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("collect:list")
	public ResponseObject list(@RequestParam Map<String, Object> params){
		//查询列表数据
		PagesInfo<ShopCollectDO> page=collectService.queryByPage(params);
		return ResponseObject.ok().put("page", page);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("collect:info")
	public ResponseObject info(@PathVariable("id") Integer id){
		ShopCollect collect = collectService.queryObject(id);
		return ResponseObject.ok().put("collect", collect);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("collect:save")
	public ResponseObject save(@RequestBody ShopCollect collect){
		collectService.save(collect);
		return ResponseObject.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("collect:update")
	public ResponseObject update(@RequestBody ShopCollect collect){
		collectService.update(collect);
		return ResponseObject.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("collect:delete")
	public ResponseObject delete(@RequestBody Integer[] ids){
		collectService.deleteBatch(ids);
		return ResponseObject.ok();
	}
	
}
