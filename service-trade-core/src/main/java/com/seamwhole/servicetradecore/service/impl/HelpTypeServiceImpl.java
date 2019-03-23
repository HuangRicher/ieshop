package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.HelpTypeMapper;
import com.seamwhole.servicetradecore.model.HelpType;
import com.seamwhole.servicetradecore.model.HelpTypeExample;
import com.seamwhole.servicetradecore.service.HelpTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-11-07 11:04:20
 */
@Service
public class HelpTypeServiceImpl implements HelpTypeService {

    @Autowired
    private HelpTypeMapper helpTypeMapper;


    public List<HelpType> queryList(Map<String, Object> map) {
        HelpTypeExample example=new HelpTypeExample();
        if(map.get("typeName")!=null)
            example.createCriteria().andTypeNameLike("%"+(String)map.get("typeName")+"%");
        return helpTypeMapper.selectByExample(example);
    }

}
