package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.OrderGoods;
import com.seamwhole.webtradeadmin.service.OrderGoodsService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("ordergoods")
public class OrderGoodsController {
	@Autowired
	private OrderGoodsService orderGoodsService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ordergoods:list")
	public ResponseObject list(@RequestParam Map<String, Object> params){
		//查询列表数据
		PagesInfo<OrderGoods> page=orderGoodsService.queryByPage(params);
		return ResponseObject.ok().put("page", page);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ordergoods:info")
	public ResponseObject info(@PathVariable("id") Integer id){
		OrderGoods orderGoods = orderGoodsService.queryObject(id);
		return ResponseObject.ok().put("orderGoods", orderGoods);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ordergoods:save")
	public ResponseObject save(@RequestBody OrderGoods orderGoods){
		orderGoodsService.save(orderGoods);
		return ResponseObject.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ordergoods:update")
	public ResponseObject update(@RequestBody OrderGoods orderGoods){
		orderGoodsService.update(orderGoods);
		return ResponseObject.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ordergoods:delete")
	public ResponseObject delete(@RequestBody Integer[] ids){
		orderGoodsService.deleteBatch(ids);
		return ResponseObject.ok();
	}

	/**
	 * 查看所有列表
	 */
	@RequestMapping("/queryAll")
	public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
		List<OrderGoods> list = orderGoodsService.queryList(params);
		return ResponseObject.ok().put("list", list);
	}
}
