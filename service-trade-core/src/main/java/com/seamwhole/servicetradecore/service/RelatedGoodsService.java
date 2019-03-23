package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.RelatedGoods;

import java.util.List;
import java.util.Map;


public interface RelatedGoodsService {

    List<RelatedGoods> queryList(Map<String, Object> map);

}
