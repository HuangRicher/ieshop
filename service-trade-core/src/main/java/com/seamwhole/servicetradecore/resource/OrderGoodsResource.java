package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.OrderGoods;
import com.seamwhole.servicetradecore.service.OrderGoodsService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderGoods")
public class OrderGoodsResource {

    @Autowired
    private OrderGoodsService orderGoodsService;

    @PostMapping("/queryByPage")
    public PagesInfo<OrderGoods> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<OrderGoods> pageInfo=orderGoodsService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<OrderGoods>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @PostMapping("/save")
    public void save(@RequestBody OrderGoods orderGoods){
        orderGoodsService.save(orderGoods);
    }

    @GetMapping("/queryObject/{id}")
    public OrderGoods queryObject(@PathVariable("id") Integer id){
        return orderGoodsService.queryObject(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody OrderGoods orderGoods){
        orderGoodsService.update(orderGoods);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        orderGoodsService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<OrderGoods> queryList(@RequestBody Map<String, Object> params){
        return orderGoodsService.queryList(params);
    }
}
