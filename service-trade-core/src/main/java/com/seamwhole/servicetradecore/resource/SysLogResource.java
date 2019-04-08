package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.SysLog;
import com.seamwhole.servicetradecore.service.SysLogService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/sysLog")
public class SysLogResource {
    @Autowired
    private SysLogService sysLogService;

    @PostMapping("/queryByPage")
    public PagesInfo<SysLog> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<SysLog> page=sysLogService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<SysLog>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }
}
