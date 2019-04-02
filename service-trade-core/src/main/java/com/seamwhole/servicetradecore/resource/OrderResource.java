package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.OrderDO;
import com.seamwhole.servicetradecore.model.Order;
import com.seamwhole.servicetradecore.service.OrderService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderResource {

    @Autowired
    private OrderService orderService;


    @PostMapping("/queryByPage")
    public PagesInfo<OrderDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<OrderDO> pageInfo=orderService.queryShopOrderByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<OrderDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public OrderDO queryObject(@PathVariable("id") Integer id){
        return orderService.getShopOrderById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Order order){
        orderService.save(order);
    }

    @PostMapping("/update")
    public void update(@RequestBody Order order){
        orderService.update(order);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        orderService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<OrderDO> queryList(@RequestBody Map<String, Object> params){
        return orderService.queryShopOrderList(params);
    }

    @PostMapping("/queryTotal")
    public int queryTotal(@RequestBody Map<String, Object> params){
        return orderService.getTotalCount(params);
    }

    @GetMapping("/confirm/{id}")
    public void confirm(@PathVariable("/id") Integer id){
        orderService.confirm(id);
    }

    @PostMapping("/sendGoods")
    public void sendGoods(@RequestBody Order order){
        orderService.sendGoods(order);
    }
}
