package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.UnitClient;
import org.springframework.stereotype.Component;

@Component
public class UnitClientHystrix implements UnitClient{
    @Override
    public Object batchDeleteUnitByIds(String ids, String deleteType) {
        return null;
    }
}
