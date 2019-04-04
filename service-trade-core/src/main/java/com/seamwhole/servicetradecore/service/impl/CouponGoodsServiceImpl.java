package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.CouponGoodsMapper;
import com.seamwhole.servicetradecore.model.CouponGoods;
import com.seamwhole.servicetradecore.model.CouponGoodsExample;
import com.seamwhole.servicetradecore.service.CouponGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CouponGoodsServiceImpl implements CouponGoodsService {

    @Autowired
    private CouponGoodsMapper couponGoodsMapper;



    @Override
    public List<CouponGoods> queryList(Map<String, Object> params) {
        CouponGoodsExample example=new CouponGoodsExample();
        return couponGoodsMapper.selectByExample(example);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        CouponGoodsExample example=new CouponGoodsExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        couponGoodsMapper.deleteByExample(example);
    }

    @Override
    public void updateById(CouponGoods couponGoods) {
        couponGoodsMapper.updateByPrimaryKeySelective(couponGoods);
    }

    @Override
    public void save(CouponGoods couponGoods) {
        couponGoodsMapper.insertSelective(couponGoods);
    }

    @Override
    public CouponGoods getById(Integer id) {
        return couponGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<CouponGoods> queryShopCommentByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<CouponGoods> page= PageHelper.startPage(pageNum,pageSize);
        CouponGoodsExample example=new CouponGoodsExample();
        couponGoodsMapper.selectByExample(example);
        return page.toPageInfo();
    }
}
