package com.seamwhole.servicetradecore.resource;

import com.seamwhole.servicetradecore.mapper.model.SysMenuDO;
import com.seamwhole.servicetradecore.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sysMenu")
public class SysMenuResource {

    @Autowired
    private SysMenuService sysMenuService;


    @GetMapping("/queryList")
    public List<SysMenuDO> queryList(){
        return sysMenuService.queryList(new HashMap<String, Object>());
    }
}
