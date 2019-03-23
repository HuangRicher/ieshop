package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.GoodsGalleryMapper;
import com.seamwhole.servicetradecore.model.GoodsGallery;
import com.seamwhole.servicetradecore.model.GoodsGalleryExample;
import com.seamwhole.servicetradecore.service.GoodsGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GoodsGalleryServiceImpl implements GoodsGalleryService {
    @Autowired
    private GoodsGalleryMapper goodsGalleryDao;


    public List<GoodsGallery> queryList(Map<String, Object> map) {
        GoodsGalleryExample example=new GoodsGalleryExample();
        if(map.get("goodsId")!=null)
            example.createCriteria().andGoodsIdEqualTo((int)map.get("goodsId"));
        return goodsGalleryDao.selectByExample(example);
    }

}
