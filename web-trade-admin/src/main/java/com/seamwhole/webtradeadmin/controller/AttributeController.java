package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attribute:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<AttributeEntity> attributeList = attributeService.queryList(query);
        int total = attributeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(attributeList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("attribute:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        AttributeEntity attribute = attributeService.queryObject(id);

        return ResponseObject.ok().put("attribute", attribute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attribute:save")
    public ResponseObject save(@RequestBody AttributeEntity attribute) {
        attributeService.save(attribute);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attribute:update")
    public ResponseObject update(@RequestBody AttributeEntity attribute) {
        attributeService.update(attribute);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attribute:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        attributeService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<AttributeEntity> list = attributeService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
