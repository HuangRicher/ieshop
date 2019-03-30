package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.cache.RegionCacheUtil;
import com.seamwhole.servicetradecore.controller.model.SysRegionModel;
import com.seamwhole.servicetradecore.domain.SysRegionInfo;
import com.seamwhole.servicetradecore.mapper.model.SysRegionDO;
import com.seamwhole.servicetradecore.model.SysRegion;
import com.seamwhole.servicetradecore.service.SysRegionService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import com.seamwhole.servicetradecore.util.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地址管理Controller
 */
@RestController
@RequestMapping("/old/sys/region")
public class SysRegionController {
    @Autowired
    private SysRegionService sysRegionService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    public ResponseObject list(@RequestBody SysRegionModel regionModel) {
        //查询列表数据
        Map<String,Object> params=new HashMap<>();
        if(StringUtils.isNotBlank(regionModel.getName()))
            params.put("name",regionModel.getName());
        if(StringUtils.isNotBlank(regionModel.getParentName()))
            params.put("parentName",regionModel.getParentName());
        if(regionModel.getType()!=null)
            params.put("type",regionModel.getType());
        PageInfo<SysRegionDO> pageInfo=sysRegionService.queryByPage(params,regionModel.getPageNum(),regionModel.getPageSize());
        return ResponseObject.ok().put("page", pageInfo);
    }

    /**
     * 根据主键获取信息
     */
    @RequestMapping("/info/{id}")
    public ResponseObject info(@PathVariable("id") Integer id) {
        SysRegion region = sysRegionService.queryObject(id);
        return ResponseObject.ok().put("region", region);
    }

    /**
     * 新增地址
     */
    @RequestMapping("/save")
    public ResponseObject save(@RequestBody SysRegionModel regionModel) {
        SysRegion region=new SysRegion();
        if(regionModel.getParentId()!=null)
            region.setParentId(regionModel.getParentId());
        if(StringUtils.isNotBlank(regionModel.getName()))
            region.setName(regionModel.getName());
        if(regionModel.getType()!=null)
            region.setType(regionModel.getType());
        if(regionModel.getAgencyId()!=null)
            region.setAgencyId(regionModel.getAgencyId());
        sysRegionService.save(region);

        return ResponseObject.ok();
    }

    /**
     * 修改地址
     */
    @RequestMapping("/update")
    public ResponseObject update(@RequestBody SysRegionModel regionModel) {
        SysRegion region=new SysRegion();
        region.setId(regionModel.getRegionId());
        if(regionModel.getParentId()!=null)
            region.setParentId(regionModel.getParentId());
        if(StringUtils.isNotBlank(regionModel.getName()))
            region.setName(regionModel.getName());
        if(regionModel.getType()!=null)
            region.setType(regionModel.getType());
        if(regionModel.getAgencyId()!=null)
            region.setAgencyId(regionModel.getAgencyId());
        return ResponseObject.ok();
    }

    /**
     * 删除地址
     */
    @RequestMapping("/delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        sysRegionService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查询所有国家
     */
    @RequestMapping("/getAllCountry")
    public ResponseObject getAllCountry() {
        List<SysRegionDO> list = RegionCacheUtil.getAllCountry();
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 查询所有省份
     */
    @RequestMapping("/getAllProvice")
    public ResponseObject getAllProvice(@RequestParam(required = false) Integer areaId) {
        List<SysRegionDO> list = RegionCacheUtil.getAllProviceByParentId(areaId);
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 查询所有城市
     */
    @RequestMapping("/getAllCity")
    public ResponseObject getAllCity(@RequestParam(required = false) Integer areaId) {
        List<SysRegionDO> list = RegionCacheUtil.getChildrenCity(areaId);
        return ResponseObject.ok().put("list", list);
    }


    /**
     * 查询所有区县
     */
    @RequestMapping("/getChildrenDistrict")
    public ResponseObject getChildrenDistrict(@RequestParam(required = false) Integer areaId) {
        List<SysRegionDO> list = RegionCacheUtil.getChildrenDistrict(areaId);
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 查看信息(全部加载页面渲染太慢！)
     */
    @RequestMapping("/getAreaTree")
    public ResponseObject getAreaTree() {
        List<SysRegionDO> list = RegionCacheUtil.sysRegionEntityList;
        List<SysRegionInfo> infoList=new ArrayList<>();
        for (SysRegionDO sysRegionEntity : list) {
            SysRegionInfo info=new SysRegionInfo();
            BeanUtils.copyProperties(sysRegionEntity,info);
            info.setValue(sysRegionEntity.getId() + "");
            info.setLabel(sysRegionEntity.getName());
            infoList.add(info);
        }
        List<SysRegionInfo> node = TreeUtils.factorTree(infoList);

        return ResponseObject.ok().put("node", node);
    }

    /**
     * 根据类型获取区域
     */
    @RequestMapping("/getAreaByType")
    public ResponseObject getAreaByType(@RequestParam(required = false) Integer type) {

        List<SysRegionDO> list = new ArrayList<>();
        if (type.equals(0)) {

        } else if (type.equals(1)) {//省份
            list = RegionCacheUtil.getAllCountry();
        } else if (type.equals(2)) {
            list = RegionCacheUtil.getAllProvice();
        } else if (type.equals(3)) {
            list = RegionCacheUtil.getAllCity();
        }
        return ResponseObject.ok().put("list", list);
    }
}
