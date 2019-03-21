package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.mapper.model.ShopCartModel;
import com.seamwhole.servicetradecore.model.ShopCart;

import java.util.List;
import java.util.Map;

public interface CartService {

    ShopCart queryObject(Integer id);

    List<ShopCartModel> queryList(Integer userId, Integer goodsId, Integer productId, Boolean checked, String order);

    int queryTotal(Map<String, Object> map);

    void save(ShopCart cart);

    void update(ShopCart cart);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    void updateCheck(Integer[] productIds, Integer isChecked, Integer userId);

    void deleteByProductIds(Integer[] productIds);

    void deleteByUserAndProductIds(Integer userId, Integer[] productIds);

    void deleteByCart(Integer userId, String sessionId, Integer checked);

}
