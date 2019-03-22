package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopAd;

import java.util.List;
import java.util.Map;


public interface AdService {

    List<ShopAd> queryByPositionId(Integer positionId);
}
