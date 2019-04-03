package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.FootPrint;
import com.seamwhole.webtradeadmin.info.ShopFootPrintDO;
import com.seamwhole.webtradeadmin.service.FootprintService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("footprint")
public class FootprintController {
    @Autowired
    private FootprintService footprintService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("footprint:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<ShopFootPrintDO> page=footprintService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("footprint:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        FootPrint footprint = footprintService.queryObject(id);

        return ResponseObject.ok().put("footprint", footprint);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("footprint:save")
    public ResponseObject save(@RequestBody FootPrint footprint) {
        footprintService.save(footprint);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("footprint:update")
    public ResponseObject update(@RequestBody FootPrint footprint) {
        footprintService.update(footprint);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("footprint:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        footprintService.deleteBatch(ids);

        return ResponseObject.ok();
    }

}
