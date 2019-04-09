package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SysMacro;
import com.seamwhole.webtradeadmin.service.impl.SysMacroServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysMacroServiceHystrix.class)
public interface SysMacroService {

    @PostMapping("/sysMacro/queryByPage")
    PagesInfo<SysMacro> queryByPage(@RequestBody Map<String, Object> params);

    @PostMapping("/sysMacro/queryObject")
    SysMacro queryObject(@PathVariable("macroId")Long macroId);

    @PostMapping("/sysMacro/save")
    void save(@RequestBody SysMacro sysMacro);

    @PostMapping("/sysMacro/update")
    void update(@RequestBody SysMacro sysMacro);

    @PostMapping("/sysMacro/deleteBatch")
    void deleteBatch(@RequestBody Long[] macroIds);

    @PostMapping("/sysMacro/queryList")
    List<SysMacro> queryList(@RequestBody Map<String, Object> params);

    @PostMapping("/sysMacro/queryAllParent")
    List<SysMacro> queryAllParent(@RequestBody Map<String, Object> params);

    @PostMapping("/sysMacro/queryMacrosByValue/{value}")
    List<SysMacro> queryMacrosByValue(@PathVariable("value") String value);
}
