package com.seamwhole.webtradeadmin.service;

import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SysDept;
import com.seamwhole.webtradeadmin.info.SysDeptDO;
import com.seamwhole.webtradeadmin.info.SysDeptModel;
import com.seamwhole.webtradeadmin.service.impl.SysDeptServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysDeptServiceHystrix.class)
public interface SysDeptService {

    @GetMapping(value = "/sysDept/getUserDeptId/{userId}/{deptId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    long getUserDeptId(@PathVariable("userId") Long userId, @PathVariable("deptId") Long deptId);

    @PostMapping(value = "/sysDept/deleteById/{deptId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteById(@PathVariable("deptId")Long deptId);

    @GetMapping(value = "/sysDept/queryDeptIdList/{deptId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<Long> queryDeptIdList(@PathVariable("deptId")Long deptId);

    @PostMapping(value = "/sysDept/updateById",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateById(@RequestBody SysDeptModel deptModel);

    @PostMapping(value = "/sysDept/saveDept",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void saveDept(@RequestBody SysDeptModel deptModel);

    @GetMapping(value = "/sysDept/getDeptById/{deptId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    SysDept getDeptById(@PathVariable("deptId")Long deptId);

    @GetMapping(value = "/sysDept/querySelectList/{userId}/{deptId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<SysDeptDO> querySelectList(@PathVariable("userId") Long userId,@PathVariable("deptId")Long deptId);

    @GetMapping(value = "/sysDept/queryList/{userId}/{deptId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<SysDeptDO> queryList(@PathVariable("userId") Long userId,@PathVariable("deptId")Long deptId);
}
