package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;

public class HelpIssueDO extends HelpIssue implements Serializable {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
