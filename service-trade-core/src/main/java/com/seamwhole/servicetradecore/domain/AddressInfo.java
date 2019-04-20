package com.seamwhole.servicetradecore.domain;

import com.seamwhole.servicetradecore.model.ShopAddress;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

public class AddressInfo extends ShopAddress implements Serializable{

    private String fullRegion;

    public String getFullRegion() {
        if (StringUtils.isEmpty(fullRegion)) {
            fullRegion = getProvinceName() + getCityName() + getCountyName();
        }
        return fullRegion;
    }

    public void setFullRegion(String fullRegion) {
        this.fullRegion = fullRegion;
    }
}
