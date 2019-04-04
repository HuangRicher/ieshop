package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.AttributeCategory;
import com.seamwhole.servicetradecore.service.AttributeCategoryService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attributeCategory")
public class AttributeCategoryResource {

    @Autowired
    private AttributeCategoryService attributeCategoryService;



    @PostMapping("/queryByPage")
    public PagesInfo<AttributeCategory> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<AttributeCategory> page=attributeCategoryService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<AttributeCategory>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }

    @GetMapping("/queryObject/{id}")
    public AttributeCategory queryObject(@PathVariable("id") Integer id){
        return attributeCategoryService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody AttributeCategory attributeCategory){
        attributeCategoryService.save(attributeCategory);
    }

    @PostMapping("/update")
    public void update(@RequestBody AttributeCategory attributeCategory){
        attributeCategoryService.updateById(attributeCategory);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        attributeCategoryService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<AttributeCategory> queryList(@RequestBody Map<String, Object> params){
        return attributeCategoryService.queryList(params);
    }
}
