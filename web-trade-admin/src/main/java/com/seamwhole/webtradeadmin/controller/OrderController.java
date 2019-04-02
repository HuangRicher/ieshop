package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Order;
import com.seamwhole.webtradeadmin.info.OrderDO;
import com.seamwhole.webtradeadmin.service.OrderService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        PagesInfo<OrderDO> page=orderService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        OrderDO order = orderService.queryObject(id);
        return ResponseObject.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:save")
    public ResponseObject save(@RequestBody Order order) {
        orderService.save(order);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:update")
    public ResponseObject update(@RequestBody Order order) {
        orderService.update(order);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        orderService.deleteBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<OrderDO> list = orderService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public ResponseObject queryTotal(@RequestParam Map<String, Object> params) {
        int sum = orderService.queryTotal(params);
        return ResponseObject.ok().put("sum", sum);
    }

    /**
     * 确定收货
     *
     * @param id
     * @return
     */
    @RequestMapping("/confirm")
    @RequiresPermissions("order:confirm")
    public ResponseObject confirm(@RequestBody Integer id) {
        orderService.confirm(id);
        return ResponseObject.ok();
    }

    /**
     * 发货
     *
     * @param order
     * @return
     */
    @RequestMapping("/sendGoods")
    @RequiresPermissions("order:sendGoods")
    public ResponseObject sendGoods(@RequestBody Order order) {
        orderService.sendGoods(order);
        return ResponseObject.ok();
    }
}
