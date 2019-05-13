package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.SerialNumberClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SerialNumberClientHystrix implements SerialNumberClient{
    @Override
    public Object checkIsExist(Long id, String materialName, String serialNumber) {
        return null;
    }

    @Override
    public Object addSerialNumber(String beanJson) {
        return null;
    }

    @Override
    public Object updateSerialNumber(String beanJson) {
        return null;
    }

    @Override
    public Object batAddSerialNumber(String materialName, String serialNumberPrefix, Integer batAddTotal, String remark) {
        return null;
    }

    @Override
    public Object batchDeleteSerialNumberByIds(String ids) {
        return null;
    }
}
