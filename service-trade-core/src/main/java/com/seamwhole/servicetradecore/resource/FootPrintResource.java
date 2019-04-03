package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopFootPrintDO;
import com.seamwhole.servicetradecore.model.FootPrint;
import com.seamwhole.servicetradecore.service.FootPrintService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/footPrint")
public class FootPrintResource {

    @Autowired
    private FootPrintService footPrintService;


    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        footPrintService.deleteBatch(ids);
    }

    @PostMapping("/update")
    public void update(@RequestBody FootPrint footprint){
        footPrintService.updateById(footprint);
    }

    @PostMapping("/save")
    public void save(@RequestBody FootPrint footprint){
        footPrintService.save(footprint);
    }

    @GetMapping("/queryObject/{id}")
    public FootPrint queryObject(@PathVariable("id") Integer id){
        return footPrintService.queryObject(id);
    }

    @PostMapping("/queryByPage")
    public PagesInfo<ShopFootPrintDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopFootPrintDO> pageInfo=footPrintService.queryShopByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopFootPrintDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }
}
