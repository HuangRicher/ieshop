package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.webtradeadmin.service.CartService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("cart:list")
	public ResponseObject list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CartEntity> cartList = cartService.queryList(query);
		int total = cartService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(cartList, total, query.getLimit(), query.getPage());
		
		return ResponseObject.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cart:info")
	public ResponseObject info(@PathVariable("id") Integer id){
		CartEntity cart = cartService.queryObject(id);
		
		return ResponseObject.ok().put("cart", cart);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("cart:save")
	public ResponseObject save(@RequestBody CartEntity cart){
		cartService.save(cart);
		
		return ResponseObject.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("cart:update")
	public ResponseObject update(@RequestBody CartEntity cart){
		cartService.update(cart);
		
		return ResponseObject.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("cart:delete")
	public ResponseObject delete(@RequestBody Integer[] ids){
		cartService.deleteBatch(ids);
		
		return ResponseObject.ok();
	}
	
}
