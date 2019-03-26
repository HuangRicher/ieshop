package com.seamwhole.servicetradecore.service.impl;


import com.seamwhole.servicetradecore.mapper.SysDeptMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysDeptExtMapper;
import com.seamwhole.servicetradecore.mapper.model.SysDeptDO;
import com.seamwhole.servicetradecore.model.SysDept;
import com.seamwhole.servicetradecore.model.SysDeptExample;
import com.seamwhole.servicetradecore.service.SysDeptService;
import com.seamwhole.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysDeptExtMapper sysDeptExtMapper;


    @Override
    public SysDept queryObject(Long deptId) {
        SysDeptExample example=new SysDeptExample();
        example.createCriteria().andDeptIdEqualTo(deptId).andDelFlagEqualTo(0);
        List<SysDept> list=sysDeptMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public List<SysDeptDO> queryList(Map<String, Object> map) {
        return sysDeptExtMapper.queryList(map);
    }

    @Override
    public void save(SysDept sysDept) {
        sysDeptMapper.insertSelective(sysDept);
    }

    @Override
    public void update(SysDept sysDept) {
        sysDeptMapper.updateByPrimaryKeySelective(sysDept);
    }

    @Override
    public void delete(Long deptId) {
        SysDept dept=new SysDept();
        dept.setDeptId(deptId);
        dept.setDelFlag(-1);
        sysDeptMapper.updateByPrimaryKeySelective(dept);
    }

    @Override
    public List<Long> queryDeptIdList(Long parentId) {
        return sysDeptExtMapper.queryDetpIdList(parentId);
    }

    @Override
    public String getSubDeptIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> deptIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList = queryDeptIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        //添加本部门
        deptIdList.add(deptId);

        String deptFilter = StringUtils.join(deptIdList, ",");
        return deptFilter;
    }

    /**
     * 递归
     */
    public void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList) {
        for (Long deptId : subIdList) {
            List<Long> list = queryDeptIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }

    /*@Override
    public Page<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        sysDeptDao.queryPageByDto(userWindowDto);
        return PageHelper.endPage();
    }*/
}
