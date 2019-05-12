package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.InOutItemClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class InOutItemClientHystrix implements InOutItemClient{
    @Override
    public String findBySelect(String type, HttpServletRequest request) {
        return null;
    }

    @Override
    public Object batchDeleteInOutItemByIds(String ids, String deleteType) {
        return null;
    }
}
