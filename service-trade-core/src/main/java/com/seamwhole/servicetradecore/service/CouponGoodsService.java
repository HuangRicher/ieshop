package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.CouponGoods;

import java.util.List;
import java.util.Map;

public interface CouponGoodsService {
    List<CouponGoods> queryList(Map<String, Object> params);

    void deleteBatch(Integer[] ids);

    void updateById(CouponGoods couponGoods);

    void save(CouponGoods couponGoods);

    CouponGoods getById(Integer id);

    PageInfo<CouponGoods> queryShopCommentByPage(Map<String, Object> params, Integer page, Integer limit);
}
