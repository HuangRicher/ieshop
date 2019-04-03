package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.GoodsGallery;
import com.seamwhole.webtradeadmin.info.GoodsGalleryDO;
import com.seamwhole.webtradeadmin.service.GoodsGalleryService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GoodsGalleryServiceHystrix implements GoodsGalleryService {
    @Override
    public PagesInfo<GoodsGalleryDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public GoodsGallery queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(GoodsGallery goodsGallery) {

    }

    @Override
    public void update(GoodsGallery goodsGallery) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<GoodsGalleryDO> queryList(Map<String, Object> params) {
        return null;
    }
}
