package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.SysSmsLogDO;
import com.seamwhole.servicetradecore.model.SysSmsLog;
import com.seamwhole.servicetradecore.model.SysSmsLogWithBLOBs;
import com.seamwhole.servicetradecore.service.SysSmsLogService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysSmsLog")
public class SysSmsLogResource {

    @Autowired
    private SysSmsLogService sysSmsLogService;


    @PostMapping("/queryByPage")
    public PagesInfo<SysSmsLogDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<SysSmsLogDO> page=sysSmsLogService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<SysSmsLogDO>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }


    @GetMapping("/queryObject/{id}")
    public SysSmsLog queryObject(@PathVariable("id") String id){
        return sysSmsLogService.queryObject(id);
    }


    @PostMapping("/queryList")
    public List<SysSmsLogDO> queryList(@RequestBody Map<String, Object> params){
        return sysSmsLogService.queryList(params);
    }


    @PostMapping("/sendSms/{userId}")
    public SysSmsLogWithBLOBs sendSms(@RequestBody SysSmsLogWithBLOBs smsLog, @PathVariable("userId") Long userId){
        return sysSmsLogService.sendSms(smsLog,userId);
    }
}
