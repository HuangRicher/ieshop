package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.Category;
import com.seamwhole.servicetradecore.service.CategoryService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/queryByPage")
    public PagesInfo<Category> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<Category> pageInfo=categoryService.queryByPage(Integer.valueOf((String)params.get("limit")),Integer.valueOf((String)params.get("page")),params,"");
        return new PagesInfo<Category>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @PostMapping("/queryObject/{id}")
    public Category queryObject(@PathVariable("id") Integer id){
        return categoryService.queryObject(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Category category){
        categoryService.save(category);
    }

    @PostMapping("/update")
    public void update(@RequestBody Category category){
        categoryService.update(category);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        categoryService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<Category> queryList(@RequestBody Map<String, Object> params){
        return categoryService.queryList(params);
    }
}
