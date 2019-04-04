package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Goods;
import com.seamwhole.webtradeadmin.info.GoodsModel;
import com.seamwhole.webtradeadmin.info.ShopGoodsDO;
import com.seamwhole.webtradeadmin.service.GoodsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GoodsServiceHystrix implements GoodsService {
    @Override
    public PagesInfo<ShopGoodsDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public Goods queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(GoodsModel goods, Long userId, Long deptId) {

    }

    @Override
    public void update(Goods goods) {

    }

    @Override
    public void deleteBatch(Integer[] ids, Long userId) {

    }

    @Override
    public List<ShopGoodsDO> queryList(Map<String, Object> params) {
        return null;
    }

    @Override
    public void back(Integer[] ids, Long userId) {

    }

    @Override
    public int queryTotal(Map<String, Object> params) {
        return 0;
    }

    @Override
    public void enSale(Integer id, Long userId) {

    }

    @Override
    public void unSale(Integer id, Long userId) {

    }
}
