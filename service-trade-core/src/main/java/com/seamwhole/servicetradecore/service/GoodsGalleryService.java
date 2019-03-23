package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.GoodsGallery;

import java.util.List;
import java.util.Map;


public interface GoodsGalleryService {

    List<GoodsGallery> queryList(Map<String, Object> map);

}
