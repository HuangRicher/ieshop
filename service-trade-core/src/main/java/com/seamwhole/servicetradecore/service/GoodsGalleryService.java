package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.GoodsGalleryDO;
import com.seamwhole.servicetradecore.model.GoodsGallery;

import java.util.List;
import java.util.Map;


public interface GoodsGalleryService {

    List<GoodsGallery> queryList(Map<String, Object> map);

    PageInfo<GoodsGalleryDO> queryShopByPage(Map<String, Object> params, Integer page, Integer limit);

    GoodsGallery getById(Integer id);

    void save(GoodsGallery goodsGallery);

    void updateById(GoodsGallery goodsGallery);

    void deleteBatch(Integer[] ids);

    List<GoodsGalleryDO> queryShopList(Map<String, Object> params);
}
