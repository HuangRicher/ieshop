package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysRegion;
import com.seamwhole.webtradeadmin.info.SysRegionDO;
import com.seamwhole.webtradeadmin.info.SysRegionInfo;
import com.seamwhole.webtradeadmin.service.SysRegionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SysRegionServiceHystrix implements SysRegionService {
    @Override
    public List<SysRegionDO> getAllCountry() {
        return null;
    }

    @Override
    public List<SysRegionDO> getAllProvice() {
        return null;
    }

    @Override
    public List<SysRegionDO> getAllCity() {
        return null;
    }

    @Override
    public List<SysRegionInfo> getAreaTree() {
        return null;
    }

    @Override
    public List<SysRegionDO> getChildrenDistrict(Integer areaId) {
        return null;
    }

    @Override
    public List<SysRegionDO> getAllProvinceByParentId(Integer areaId) {
        return null;
    }

    @Override
    public List<SysRegionDO> getChildrenCity(Integer areaId) {
        return null;
    }

    @Override
    public SysRegion queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(SysRegion region) {

    }

    @Override
    public void update(SysRegion region) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public PagesInfo<SysRegionDO> queryByPage(Map<String, Object> params) {
        return null;
    }
}
