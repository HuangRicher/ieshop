package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;
import java.util.List;


public class CategoryInfo extends Category implements Serializable {
    private static final long serialVersionUID = 1L;


    private Boolean checked;

    private Boolean open;

    private List<CategoryInfo> subCategoryList;


    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<CategoryInfo> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<CategoryInfo> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
