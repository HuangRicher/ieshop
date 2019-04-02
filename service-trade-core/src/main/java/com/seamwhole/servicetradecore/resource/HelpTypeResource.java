package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.HelpType;
import com.seamwhole.servicetradecore.service.HelpTypeService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/helpType")
public class HelpTypeResource {

    @Autowired
    private HelpTypeService helpTypeService;


    @PostMapping("/queryByPage")
    public PagesInfo<HelpType> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<HelpType> pageInfo=helpTypeService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<HelpType>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public HelpType queryObject(@PathVariable("id") Integer id){
        return helpTypeService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody HelpType helpType){
        helpTypeService.save(helpType);
    }

    @PostMapping("/update")
    public void update(@RequestBody HelpType helpType){
        helpTypeService.updateById(helpType);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        helpTypeService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<HelpType> queryList(@RequestBody Map<String, Object> params){
        return helpTypeService.queryList(params);
    }
}
