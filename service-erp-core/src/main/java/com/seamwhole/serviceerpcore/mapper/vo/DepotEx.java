package com.seamwhole.serviceerpcore.mapper.vo;

import com.seamwhole.serviceerpcore.model.Depot;

/**
 * Description
 *
 * @Author: cjl
 * @Date: 2019/2/25 11:40
 */
public class DepotEx extends Depot {
    //负责人名字
    private String principalName;

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }
}
