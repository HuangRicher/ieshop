package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.HelpIssueMapper;
import com.seamwhole.servicetradecore.mapper.ext.HelpIssueExtMapper;
import com.seamwhole.servicetradecore.mapper.model.HelpIssueDO;
import com.seamwhole.servicetradecore.model.HelpIssue;
import com.seamwhole.servicetradecore.model.HelpIssueExample;
import com.seamwhole.servicetradecore.service.HelpIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class HelpIssueServiceImpl implements HelpIssueService {

    @Autowired
    private HelpIssueMapper helpIssueMapper;

    @Autowired
    private HelpIssueExtMapper helpIssueExtMapper;



    @Override
    public List<HelpIssue> queryList(Map<String, Object> map) {
        HelpIssueExample example=new HelpIssueExample();
        if(map.get("typeId")!=null)
            example.createCriteria().andTypeIdEqualTo((int)map.get("typeId"));
        return helpIssueMapper.selectByExample(example);
    }

    @Override
    public PageInfo<HelpIssueDO> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<HelpIssueDO> page= PageHelper.startPage(pageNum,pageSize);
        helpIssueExtMapper.queryShopHelpIssueList(params);
        return page.toPageInfo();
    }

    @Override
    public HelpIssue getById(Integer id) {
        return helpIssueMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(HelpIssue helpIssue) {
        helpIssueMapper.insertSelective(helpIssue);
    }

    @Override
    public void updateById(HelpIssue helpIssue) {
        helpIssueMapper.updateByPrimaryKeySelective(helpIssue);
    }

    @Override
    public List<HelpIssueDO> queryShopHelpIssueList(Map<String, Object> params) {
        return helpIssueExtMapper.queryShopHelpIssueList(params);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        HelpIssueExample example=new HelpIssueExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        helpIssueMapper.deleteByExample(example);
    }
}
