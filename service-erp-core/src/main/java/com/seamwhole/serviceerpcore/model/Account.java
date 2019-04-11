package com.seamwhole.serviceerpcore.model;

import java.math.BigDecimal;

public class Account {
    private Long id;

    private String name;

    private String serialno;

    private BigDecimal initialamount;

    private BigDecimal currentamount;

    private String remark;

    private Boolean isdefault;

    private Long tenantId;

    private String deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno == null ? null : serialno.trim();
    }

    public BigDecimal getInitialamount() {
        return initialamount;
    }

    public void setInitialamount(BigDecimal initialamount) {
        this.initialamount = initialamount;
    }

    public BigDecimal getCurrentamount() {
        return currentamount;
    }

    public void setCurrentamount(BigDecimal currentamount) {
        this.currentamount = currentamount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
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