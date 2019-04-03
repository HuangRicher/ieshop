package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopAttribute;
import com.seamwhole.webtradeadmin.info.ShopAttributeDO;
import com.seamwhole.webtradeadmin.service.AttributeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class AttributeServiceHystrix implements AttributeService {
    @Override
    public PagesInfo<ShopAttributeDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopAttribute queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopAttribute attribute) {

    }

    @Override
    public void update(ShopAttribute attribute) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<ShopAttributeDO> queryList(Map<String, Object> params) {
        return null;
    }
}
