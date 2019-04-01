package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.Specification;
import com.seamwhole.servicetradecore.service.SpecificationService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification")
public class SpecificationResource {

    @Autowired
    private SpecificationService specificationService;

    @PostMapping("/queryByPage")
    public PagesInfo<Specification> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<Specification> page=specificationService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<Specification>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }

    @PostMapping("/queryObject/{id}")
    public Specification queryObject(@PathVariable("id") Integer id){
        return specificationService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Specification specification){
        specificationService.save(specification);
    }

    @PostMapping("/update")
    public void update(@RequestBody Specification specification){
        specificationService.updateById(specification);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        specificationService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<Specification> queryList(@RequestBody Map<String, Object> params){
        return specificationService.queryList(params);
    }
}
