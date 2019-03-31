package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.UserLevelMapper;
import com.seamwhole.servicetradecore.model.UserLevel;
import com.seamwhole.servicetradecore.model.UserLevelExample;
import com.seamwhole.servicetradecore.service.ShopUserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ShopUserLevelServiceImpl implements ShopUserLevelService {

    @Autowired
    private UserLevelMapper userLevelMapper;


    @Override
    public List<UserLevel> queryList(Map<String, Object> params) {
        UserLevelExample example=new UserLevelExample();
        if(params.get("name")!=null)
            example.createCriteria().andNameLike("%"+params.get("name")+"%");
        return userLevelMapper.selectByExample(example);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        UserLevelExample example=new UserLevelExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        userLevelMapper.deleteByExample(example);
    }

    @Override
    public void updateById(UserLevel userLevel) {
        userLevelMapper.updateByPrimaryKeySelective(userLevel);
    }

    @Override
    public void save(UserLevel userLevel) {
        userLevelMapper.insertSelective(userLevel);
    }

    @Override
    public UserLevel getById(Integer id) {
        return userLevelMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<UserLevel> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<UserLevel> page= PageHelper.startPage(pageNum,pageSize);
        UserLevelExample example=new UserLevelExample();
        if(params.get("name")!=null)
            example.createCriteria().andNameLike("%"+params.get("name")+"%");
        userLevelMapper.selectByExample(example);
        return page.toPageInfo();
    }
}
