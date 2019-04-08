package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.SysConfig;
import com.seamwhole.servicetradecore.service.SysConfigService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sysConfig")
public class SysConfigResource {

    @Autowired
    private SysConfigService sysConfigService;


    @PostMapping("/queryByPage")
    public PagesInfo<SysConfig> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<SysConfig> page=sysConfigService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<SysConfig>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }

    @GetMapping("/queryByPage/{id}")
    public SysConfig queryObject(@PathVariable("id") Long id){
        return sysConfigService.queryObject(id);
    }

    @PostMapping("/queryByPage")
    public void save(@RequestBody SysConfig config){
        sysConfigService.save(config);
    }

    @PostMapping("/queryByPage")
    public void update(@RequestBody SysConfig config){
        sysConfigService.update(config);
    }

    @PostMapping("/queryByPage")
    public void deleteBatch(@RequestBody Long[] ids){
        sysConfigService.deleteBatch(ids);
    }

}
