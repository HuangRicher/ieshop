package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.ShopAdMapper;
import com.seamwhole.servicetradecore.model.ShopAd;
import com.seamwhole.servicetradecore.model.ShopAdExample;
import com.seamwhole.servicetradecore.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private ShopAdMapper shopAdMapper;

    public List<ShopAd> queryByPositionId(Integer positionId) {
        ShopAdExample example=new ShopAdExample();
        example.createCriteria().andAdPositionIdEqualTo(positionId).andEnabledEqualTo(1);
        return shopAdMapper.selectByExample(example);
    }


}
