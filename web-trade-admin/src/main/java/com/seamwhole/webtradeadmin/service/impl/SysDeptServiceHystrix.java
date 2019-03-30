package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.webtradeadmin.info.SysDept;
import com.seamwhole.webtradeadmin.info.SysDeptDO;
import com.seamwhole.webtradeadmin.info.SysDeptModel;
import com.seamwhole.webtradeadmin.service.SysDeptService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysDeptServiceHystrix implements SysDeptService {
    @Override
    public long getUserDeptId(Long userId, Long deptId) {
        return 0;
    }

    @Override
    public void deleteById(Long deptId) {

    }

    @Override
    public List<Long> queryDeptIdList(Long deptId) {
        return null;
    }

    @Override
    public void updateById(SysDeptModel deptModel) {

    }

    @Override
    public void saveDept(SysDeptModel deptModel) {

    }

    @Override
    public SysDept getDeptById(Long deptId) {
        return null;
    }

    @Override
    public List<SysDeptDO> querySelectList(Long userId, Long deptId) {
        return null;
    }

    @Override
    public List<SysDeptDO> queryList(Long userId, Long deptId) {
        return null;
    }
}
