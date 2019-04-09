package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.cache.RegionCacheUtil;
import com.seamwhole.servicetradecore.domain.SysRegionInfo;
import com.seamwhole.servicetradecore.mapper.model.SysRegionDO;
import com.seamwhole.servicetradecore.model.SysRegion;
import com.seamwhole.servicetradecore.service.SysRegionService;
import com.seamwhole.servicetradecore.util.TreeUtils;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysRegion")
public class SysRegionResource {

    @Autowired
    private SysRegionService sysRegionService;


    @GetMapping("/getAllCountry")
    public List<SysRegionDO> getAllCountry(){
        return RegionCacheUtil.getAllCountry();
    }

    @GetMapping("/getAllProvice")
    public List<SysRegionDO> getAllProvice(){
        return RegionCacheUtil.getAllProvice();
    }

    @GetMapping("/getAllCity")
    public List<SysRegionDO> getAllCity(){
        return RegionCacheUtil.getAllCity();
    }

    @GetMapping("/getAreaTree")
    public List<SysRegionInfo> getAreaTree(){
        List<SysRegionDO> list = RegionCacheUtil.sysRegionEntityList;
        List<SysRegionInfo> infoList=new ArrayList<>();
        for (SysRegionDO sysRegionEntity : list) {
            SysRegionInfo info=new SysRegionInfo();
            BeanUtils.copyProperties(sysRegionEntity,info);
            info.setValue(sysRegionEntity.getId() + "");
            info.setLabel(sysRegionEntity.getName());
            infoList.add(info);
        }
        return TreeUtils.factorTree(infoList);
    }

    @GetMapping("/getChildrenDistrict/{areaId}")
    public List<SysRegionDO> getChildrenDistrict(@PathVariable("areaId") Integer areaId){
        return RegionCacheUtil.getChildrenDistrict(areaId);
    }

    @GetMapping("/getAllProvinceByParentId/{areaId}")
    public List<SysRegionDO> getAllProvinceByParentId(@PathVariable("areaId") Integer areaId){
        return RegionCacheUtil.getAllProviceByParentId(areaId);
    }

    @GetMapping("/getChildrenCity/{areaId}")
    public List<SysRegionDO> getChildrenCity(@PathVariable("areaId") Integer areaId){
        return RegionCacheUtil.getChildrenCity(areaId);
    }

    @GetMapping("/queryObject/{id}")
    public SysRegion queryObject(@PathVariable("id") Integer id){
        return sysRegionService.queryObject(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody SysRegion region){
        sysRegionService.save(region);
    }

    @PostMapping("/update")
    public void update(@RequestBody SysRegion region){
        sysRegionService.update(region);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        sysRegionService.deleteBatch(ids);
    }

    @PostMapping("/queryByPage")
    public PagesInfo<SysRegionDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<SysRegionDO> page=sysRegionService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<SysRegionDO>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }
}
