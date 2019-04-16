package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.GoodsGalleryMapper;
import com.seamwhole.servicetradecore.mapper.ext.GoodsGalleryExtMapper;
import com.seamwhole.servicetradecore.mapper.model.GoodsGalleryDO;
import com.seamwhole.servicetradecore.model.GoodsGallery;
import com.seamwhole.servicetradecore.model.GoodsGalleryExample;
import com.seamwhole.servicetradecore.service.GoodsGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class GoodsGalleryServiceImpl implements GoodsGalleryService {
    @Autowired
    private GoodsGalleryMapper goodsGalleryMapper;
    @Autowired
    private GoodsGalleryExtMapper goodsGalleryExtMapper;




    public List<GoodsGallery> queryList(Map<String, Object> map) {
        GoodsGalleryExample example=new GoodsGalleryExample();
        if(map.get("goodsId")!=null)
            example.createCriteria().andGoodsIdEqualTo((int)map.get("goodsId"));
        return goodsGalleryMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public PageInfo<GoodsGalleryDO> queryShopByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<GoodsGalleryDO> page= PageHelper.startPage(pageNum,pageSize);
        goodsGalleryExtMapper.queryShopList(params);
        return page.toPageInfo();
    }

    @Override
    public GoodsGallery getById(Integer id) {
        return goodsGalleryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(GoodsGallery goodsGallery) {
        goodsGalleryMapper.insertSelective(goodsGallery);
    }

    @Override
    public void updateById(GoodsGallery goodsGallery) {
        goodsGalleryMapper.updateByPrimaryKeyWithBLOBs(goodsGallery);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        GoodsGalleryExample example=new GoodsGalleryExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        goodsGalleryMapper.deleteByExample(example);
    }

    @Override
    public List<GoodsGalleryDO> queryShopList(Map<String, Object> params) {
        return goodsGalleryExtMapper.queryShopList(params);
    }
}
