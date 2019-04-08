package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SysRegion;
import com.seamwhole.webtradeadmin.info.SysRegionDO;
import com.seamwhole.webtradeadmin.info.SysRegionInfo;
import com.seamwhole.webtradeadmin.service.impl.SysRegionServiceHystrix;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysRegionServiceHystrix.class)
public interface SysRegionService {

    @GetMapping("/sysRegion/getAllCountry")
    List<SysRegionDO> getAllCountry();

    @GetMapping("/sysRegion/getAllProvice")
    List<SysRegionDO> getAllProvice();

    @GetMapping("/sysRegion/getAllCity")
    List<SysRegionDO> getAllCity();

    @GetMapping("/sysRegion/getAreaTree")
    List<SysRegionInfo> getAreaTree();

    @GetMapping("/sysRegion/getChildrenDistrict/{areaId}")
    List<SysRegionDO> getChildrenDistrict(@PathVariable("areaId") Integer areaId);

    @GetMapping("/sysRegion/getAllProvinceByParentId/{areaId}")
    List<SysRegionDO> getAllProvinceByParentId(@PathVariable("areaId") Integer areaId);

    @GetMapping("/sysRegion/getChildrenCity/{areaId}")
    List<SysRegionDO> getChildrenCity(@PathVariable("areaId") Integer areaId);

    @GetMapping("/sysRegion/queryObject/{id}")
    SysRegion queryObject(@PathVariable("id") Integer id);

    @PostMapping("/sysRegion/save")
    void save(SysRegion region);

    @PostMapping("/sysRegion/update")
    void update(SysRegion region);

    @PostMapping("/sysRegion/deleteBatch")
    void deleteBatch(Integer[] ids);

    @PostMapping("/sysRegion/queryByPage")
    PagesInfo<SysRegionDO> queryByPage(Map<String, Object> params);
}
