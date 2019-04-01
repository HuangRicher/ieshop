package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.AdPosition;
import com.seamwhole.servicetradecore.service.AdPositionService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adPosition")
public class AdPositionResource {

    @Autowired
    private AdPositionService adPositionService;


    @PostMapping("/queryByPage")
    public PagesInfo<AdPosition> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<AdPosition> page=adPositionService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<AdPosition>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }

    @PostMapping("/queryObject/{id}")
    public AdPosition queryObject(@PathVariable("id") Integer id){
        return adPositionService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody AdPosition adPosition){
        adPositionService.save(adPosition);
    }

    @PostMapping("/update")
    public void update(@RequestBody AdPosition adPosition){
        adPositionService.updateById(adPosition);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        adPositionService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<AdPosition> queryList(@RequestBody Map<String, Object> params){
        return adPositionService.queryList(params);
    }
}
