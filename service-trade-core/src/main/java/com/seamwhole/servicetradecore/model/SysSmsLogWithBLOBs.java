package com.seamwhole.servicetradecore.model;

public class SysSmsLogWithBLOBs extends SysSmsLog {
    private String content;

    private String mobile;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}