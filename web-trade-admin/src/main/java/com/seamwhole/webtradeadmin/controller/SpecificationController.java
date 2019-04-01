package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Specification;
import com.seamwhole.webtradeadmin.service.SpecificationService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 规格表
 */
@RestController
@RequestMapping("specification")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("specification:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<Specification> page=specificationService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("specification:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        Specification specification = specificationService.queryObject(id);
        return ResponseObject.ok().put("specification", specification);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("specification:save")
    public ResponseObject save(@RequestBody Specification specification) {
        specificationService.save(specification);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("specification:update")
    public ResponseObject update(@RequestBody Specification specification) {
        specificationService.update(specification);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("specification:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        specificationService.deleteBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<Specification> list = specificationService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }
}
