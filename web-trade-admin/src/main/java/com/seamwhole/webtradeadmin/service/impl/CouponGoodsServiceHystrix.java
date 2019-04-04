package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.CouponGoods;
import com.seamwhole.webtradeadmin.service.CouponGoodsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CouponGoodsServiceHystrix implements CouponGoodsService {
    @Override
    public PagesInfo<CouponGoods> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public CouponGoods queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(CouponGoods couponGoods) {

    }

    @Override
    public void update(CouponGoods couponGoods) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<CouponGoods> queryList(Map<String, Object> params) {
        return null;
    }
}
