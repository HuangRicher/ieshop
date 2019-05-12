package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.SystemConfigClient;
import org.springframework.stereotype.Component;

@Component
public class SystemConfigClientHystrix implements SystemConfigClient{
    @Override
    public Object batchDeleteSystemConfigByIds(String ids) {
        return null;
    }
}
