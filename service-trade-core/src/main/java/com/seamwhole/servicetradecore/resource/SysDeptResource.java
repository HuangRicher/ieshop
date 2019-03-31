package com.seamwhole.servicetradecore.resource;

import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.SysDeptModel;
import com.seamwhole.servicetradecore.mapper.model.SysDeptDO;
import com.seamwhole.servicetradecore.model.SysDept;
import com.seamwhole.servicetradecore.service.SysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysDept")
public class SysDeptResource {

    @Autowired
    private SysDeptService sysDeptService;



    @GetMapping("/getUserDeptId/{userId}/{deptId}")
    public long getUserDeptId(@PathVariable("userId") Long userId, @PathVariable("deptId") Long deptId){
        long pDeptId = 0;
        if (userId != Constant.SUPER_ADMIN) {
            SysDept dept = sysDeptService.queryObject(deptId);
            pDeptId = dept.getParentId();
        }
        return pDeptId;
    }

    @PostMapping("/deleteById/{deptId}")
    public void deleteById(@PathVariable("deptId")Long deptId){
        //判断是否有子部门
        sysDeptService.delete(deptId);
    }

    @GetMapping("/queryDeptIdList/{deptId}")
    public List<Long> queryDeptIdList(@PathVariable("deptId")Long deptId){
        return sysDeptService.queryDeptIdList(deptId);
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody SysDeptModel deptModel){
        SysDept dept=new SysDept();
        dept.setDeptId(deptModel.getDeptId());
        dept.setParentId(deptModel.getParentId());
        if(deptModel.getOrderNum()!=null && deptModel.getOrderNum()>0)
            dept.setOrderNum(deptModel.getOrderNum());
        if(StringUtils.isNotBlank(deptModel.getName()))
            dept.setName(deptModel.getName());
        sysDeptService.update(dept);
    }

    @PostMapping("/saveDept")
    public void saveDept(@RequestBody SysDeptModel deptModel){
        SysDept dept=new SysDept();
        dept.setParentId(deptModel.getParentId());
        dept.setName(deptModel.getName());
        dept.setOrderNum(deptModel.getOrderNum());
        sysDeptService.save(dept);
    }
    @GetMapping("/getDeptById/{deptId}")
    public SysDept getDeptById(@PathVariable("deptId")Long deptId){
        return sysDeptService.queryObject(deptId);
    }

    @GetMapping("/querySelectList/{userId}/{deptId}")
    public List<SysDeptDO> querySelectList(@PathVariable("userId") Long userId,@PathVariable("deptId")Long deptId){
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if (userId != Constant.SUPER_ADMIN) {
            map.put("deptFilter", sysDeptService.getSubDeptIdList(deptId));
        }
        List<SysDeptDO> deptList = sysDeptService.queryList(map);

        //添加一级部门
        if (userId == Constant.SUPER_ADMIN) {
            SysDeptDO root = new SysDeptDO();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }
        return deptList;
    }

    @GetMapping("/queryList/{userId}/{deptId}")
    public List<SysDeptDO> queryList(@PathVariable("userId") Long userId,@PathVariable("deptId")Long deptId){
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if (userId != Constant.SUPER_ADMIN) {
            map.put("deptFilter", sysDeptService.getSubDeptIdList(deptId));
        }
        return sysDeptService.queryList(map);
    }
}
