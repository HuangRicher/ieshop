package com.seamwhole.servicetradecore.service;


import com.seamwhole.servicetradecore.model.ShopToken;

import java.util.Map;


public interface TokenService {

    ShopToken queryByUserId(Long userId);

    ShopToken queryByToken(String token);

    void save(ShopToken token);

    void update(ShopToken token);

    Map<String, Object> createToken(long userId);
}
