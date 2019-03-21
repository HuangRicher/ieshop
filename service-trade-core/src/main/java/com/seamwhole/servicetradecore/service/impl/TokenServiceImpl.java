package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.ShopTokenMapper;
import com.seamwhole.servicetradecore.model.ShopToken;
import com.seamwhole.servicetradecore.model.ShopTokenExample;
import com.seamwhole.servicetradecore.service.TokenService;
import com.seamwhole.servicetradecore.util.CharUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private ShopTokenMapper shopTokenMapper;

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    public ShopToken queryByUserId(Long userId) {
        return shopTokenMapper.selectByPrimaryKey(userId);
    }

    public ShopToken queryByToken(String token) {
        ShopTokenExample example=new ShopTokenExample();
        example.createCriteria().andTokenEqualTo(token);
        List<ShopToken> list=shopTokenMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }

    public void save(ShopToken token) {
        shopTokenMapper.insertSelective(token);
    }

    public void update(ShopToken token) {
        shopTokenMapper.updateByPrimaryKeySelective(token);
    }

    public Map<String, Object> createToken(long userId) {
        //生成一个token
        String token = CharUtil.getRandomString(32);
        //当前时间
        Date now = new Date();

        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        ShopToken tokenEntity = queryByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new ShopToken();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);

        return map;
    }
}
