package com.seamwhole.serviceerpcore.mapper.vo;

import com.seamwhole.serviceerpcore.model.DepotItem;

public class DepotItemVo4Material extends DepotItem {

    private String mname;

    private String mmodel;

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMmodel() {
        return mmodel;
    }

    public void setMmodel(String mmodel) {
        this.mmodel = mmodel;
    }
}