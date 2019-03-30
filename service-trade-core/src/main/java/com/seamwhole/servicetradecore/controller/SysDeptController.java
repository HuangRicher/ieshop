package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.SysDeptModel;
import com.seamwhole.servicetradecore.mapper.model.SysDeptDO;
import com.seamwhole.servicetradecore.model.SysDept;
import com.seamwhole.servicetradecore.service.SysDeptService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理Controller
 */
@RestController
@RequestMapping("/old/sys/dept")
public class SysDeptController{
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 部门列表
     *
     * @return R
     */
    @RequestMapping("/list")
    public ResponseObject list(@RequestBody SysDeptModel deptModel) {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if (deptModel.getUserId() != Constant.SUPER_ADMIN) {
            map.put("deptFilter", sysDeptService.getSubDeptIdList(deptModel.getDeptId()));
        }
        List<SysDeptDO> deptList = sysDeptService.queryList(map);
        return ResponseObject.ok().put("list", deptList);
    }

    /**
     * 选择部门(添加、修改菜单)
     *
     * @return R
     */
    @RequestMapping("/select")
    public ResponseObject select(@RequestBody SysDeptModel deptModel) {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if (deptModel.getUserId() != Constant.SUPER_ADMIN) {
            map.put("deptFilter", sysDeptService.getSubDeptIdList(deptModel.getDeptId()));
        }
        List<SysDeptDO> deptList = sysDeptService.queryList(map);

        //添加一级部门
        if (deptModel.getUserId() == Constant.SUPER_ADMIN) {
            SysDeptDO root = new SysDeptDO();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }

        return ResponseObject.ok().put("deptList", deptList);
    }

    /**
     * 获取用户部门Id(管理员则为0)
     *
     * @return
     */
    @RequestMapping("/info")
    public ResponseObject info(@RequestBody SysDeptModel deptModel) {
        long deptId = 0;
        if (deptModel.getUserId() != Constant.SUPER_ADMIN) {
            SysDept dept = sysDeptService.queryObject(deptModel.getDeptId());
            deptId = dept.getParentId();
        }

        return ResponseObject.ok().put("deptId", deptId);
    }

    /**
     * 根据主键获取部门信息
     */
    @RequestMapping("/info/{deptId}")
    public ResponseObject info(@PathVariable("deptId") Long deptId) {
        SysDept dept = sysDeptService.queryObject(deptId);

        return ResponseObject.ok().put("dept", dept);
    }

    /**
     * 新增部门
     */
    @RequestMapping("/save")
    public ResponseObject save(@RequestBody SysDeptModel deptModel) {
        SysDept dept=new SysDept();
        dept.setParentId(deptModel.getParentId());
        dept.setName(deptModel.getName());
        dept.setOrderNum(deptModel.getOrderNum());
        sysDeptService.save(dept);
        return ResponseObject.ok();
    }

    /**
     * 修改部门
     */
    @RequestMapping("/update")
    public ResponseObject update(@RequestBody SysDeptModel deptModel) {
        SysDept dept=new SysDept();
        dept.setDeptId(deptModel.getDeptId());
        if(deptModel.getOrderNum()!=null && deptModel.getOrderNum()>0)
            dept.setOrderNum(deptModel.getOrderNum());
        if(StringUtils.isNotBlank(deptModel.getName()))
            dept.setName(deptModel.getName());
        sysDeptService.update(dept);

        return ResponseObject.ok();
    }

    /**
     * 删除部门
     */
    @RequestMapping("/delete")
    public ResponseObject delete(@RequestBody SysDeptModel deptModel) {
        //判断是否有子部门
        List<Long> deptList = sysDeptService.queryDeptIdList(deptModel.getDeptId());
        if (deptList.size() > 0) {
            return ResponseObject.error("请先删除子部门");
        }

        sysDeptService.delete(deptModel.getDeptId());

        return ResponseObject.ok();
    }

}
