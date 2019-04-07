package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.KeyWords;
import com.seamwhole.servicetradecore.service.KeyWordsService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/keyWords")
public class KeyWordsResource {

    @Autowired
    private KeyWordsService keyWordsService;


    @PostMapping("/queryByPage")
    public PagesInfo<KeyWords> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<KeyWords> pageInfo=keyWordsService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<KeyWords>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public KeyWords queryObject(@PathVariable("id") Integer id){
        return keyWordsService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody KeyWords keywords){
        keyWordsService.save(keywords);
    }

    @PostMapping("/update")
    public void update(@RequestBody KeyWords keywords){
        keyWordsService.updateById(keywords);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        keyWordsService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<KeyWords> queryList(@RequestBody Map<String, Object> params){
        return keyWordsService.queryList(params);
    }
}
