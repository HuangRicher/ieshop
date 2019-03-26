package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.cache.RegionCacheUtil;
import com.seamwhole.servicetradecore.mapper.model.SysRegionDO;
import com.seamwhole.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "评论")
@RestController
@RequestMapping("/api/region")
public class RegionController extends BaseController {

    @ApiOperation(value = "地区列表")
    @PostMapping("list")
    public Object list(Integer parentId) {
        List<SysRegionDO> regionEntityList = RegionCacheUtil.getChildrenByParentId(parentId);
        return toResponsSuccess(regionEntityList);
    }

    @PostMapping("provinceList")
    public Object provinceList() {
        List<SysRegionDO> regionEntityList = RegionCacheUtil.getAllProvice();
        return toResponsSuccess(regionEntityList);
    }

    @PostMapping("cityList")
    public Object provinceList(String proviceName) {
        List<SysRegionDO> regionEntityList = RegionCacheUtil.getChildrenCity(proviceName);
        return toResponsSuccess(regionEntityList);
    }

    @PostMapping("distinctList")
    public Object distinctList(String proviceName, String cityName) {
        List<SysRegionDO> regionEntityList = RegionCacheUtil.getChildrenDistrict(proviceName, cityName);
        return toResponsSuccess(regionEntityList);
    }

    @PostMapping("info")
    public Object info(Integer regionId) {
        SysRegionDO regionEntity = RegionCacheUtil.getAreaByAreaId(regionId);
        return toResponsSuccess(regionEntity);
    }

    @PostMapping("regionIdsByNames")
    public Object regionIdsByNames(String provinceName, String cityName, String districtName) {
        Map<String, Integer> resultObj = new HashMap<String, Integer>();
        Integer provinceId = 0;
        Integer cityId = 0;
        Integer districtId = 0;
        if (null != provinceName) {
            provinceId = RegionCacheUtil.getProvinceIdByName(provinceName);
        }
        if (null != provinceId && !StringUtils.isNullOrEmpty(cityName)) {
            cityId = RegionCacheUtil.getCityIdByName(provinceId, cityName);
        }
        if (null != provinceId && null != cityId && !StringUtils.isNullOrEmpty(districtName)) {
            districtId = RegionCacheUtil.getDistrictIdByName(provinceId, cityId, districtName);
        }
        resultObj.put("provinceId", provinceId);
        resultObj.put("cityId", cityId);
        resultObj.put("districtId", districtId);
        return toResponsSuccess(resultObj);
    }
}