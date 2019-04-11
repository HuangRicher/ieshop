package com.seamwhole.serviceerpcore.model;

import java.math.BigDecimal;

public class AccountItem {
    private Long id;

    private Long headerid;

    private Long accountid;

    private Long inoutitemid;

    private BigDecimal eachamount;

    private String remark;

    private Long tenantId;

    private String deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHeaderid() {
        return headerid;
    }

    public void setHeaderid(Long headerid) {
        this.headerid = headerid;
    }

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public Long getInoutitemid() {
        return inoutitemid;
    }

    public void setInoutitemid(Long inoutitemid) {
        this.inoutitemid = inoutitemid;
    }

    public BigDecimal getEachamount() {
        return eachamount;
    }

    public void setEachamount(BigDecimal eachamount) {
        this.eachamount = eachamount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }
}