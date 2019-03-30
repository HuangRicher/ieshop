package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.webtradeadmin.info.SysDept;
import com.seamwhole.webtradeadmin.info.SysDeptDO;
import com.seamwhole.webtradeadmin.info.SysDeptModel;
import com.seamwhole.webtradeadmin.service.SysDeptService;
import com.seamwhole.webtradeadmin.shiro.ShiroUtils;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门管理Controller
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController{

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 部门列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public ResponseObject list() {
        List<SysDeptDO> deptList = sysDeptService.queryList(ShiroUtils.getUserId(),ShiroUtils.getUserEntity().getDeptId());
        return ResponseObject.ok().put("list", deptList);
    }

    /**
     * 选择部门(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:dept:select")
    public ResponseObject select() {
        List<SysDeptDO> deptList = sysDeptService.querySelectList(ShiroUtils.getUserId(),ShiroUtils.getUserEntity().getDeptId());
        return ResponseObject.ok().put("deptList", deptList);
    }

    /**
     * 获取用户部门Id(管理员则为0)
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:dept:list")
    public ResponseObject info() {
        long deptId = sysDeptService.getUserDeptId(ShiroUtils.getUserId(),ShiroUtils.getUserEntity().getDeptId());
        return ResponseObject.ok().put("deptId", deptId);
    }

    /**
     * 根据主键获取部门信息
     */
    @RequestMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    public ResponseObject info(@PathVariable("deptId") Long deptId) {
        SysDept dept = sysDeptService.getDeptById(deptId);
        return ResponseObject.ok().put("dept", dept);
    }

    /**
     * 新增部门
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public ResponseObject save(@RequestBody SysDeptModel deptModel) {
        sysDeptService.saveDept(deptModel);
        return ResponseObject.ok();
    }

    /**
     * 修改部门
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public ResponseObject update(@RequestBody SysDeptModel deptModel) {
        sysDeptService.updateById(deptModel);
        return ResponseObject.ok();
    }

    /**
     * 删除部门
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public ResponseObject delete(long deptId) {
        //判断是否有子部门
        List<Long> deptList = sysDeptService.queryDeptIdList(deptId);
        if (deptList.size() > 0) {
            return ResponseObject.error("请先删除子部门");
        }
        sysDeptService.deleteById(deptId);
        return ResponseObject.ok();
    }

}
