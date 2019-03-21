package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.GoodsGallery;

import java.util.List;
import java.util.Map;


public interface GoodsGalleryService {

    GoodsGallery queryObject(Integer id);

    List<GoodsGallery> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(GoodsGallery goods);

    void update(GoodsGallery goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
