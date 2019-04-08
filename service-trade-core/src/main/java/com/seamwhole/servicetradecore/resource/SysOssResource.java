package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.SysOss;
import com.seamwhole.servicetradecore.service.SysOssService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysOss")
public class SysOssResource {

    @Autowired
    private SysOssService sysOssService;


    @PostMapping("/queryByPage")
    public PagesInfo<SysOss> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<SysOss> page=sysOssService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<SysOss>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }

    @PostMapping("/removeByIds")
    public void removeByIds(@RequestBody List<Long> longs){
        sysOssService.removeByIds(longs);
    }

    @PostMapping("/save")
    public void save(@RequestBody SysOss ossEntity){
        sysOssService.save(ossEntity);
    }
}
