package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.MaterialPropertyClient;
import org.springframework.stereotype.Component;

@Component
public class MaterialPropertyClientHystrix implements MaterialPropertyClient{
    @Override
    public Object batchDeleteMaterialPropertyByIds(String ids) {
        return null;
    }
}
