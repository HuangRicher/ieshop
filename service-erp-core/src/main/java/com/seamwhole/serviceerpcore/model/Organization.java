package com.seamwhole.serviceerpcore.model;

import java.util.Date;

public class Organization {
    private Long id;

    private String orgNo;

    private String orgFullName;

    private String orgAbr;

    private String orgTpcd;

    private String orgStcd;

    private String orgParentNo;

    private String sort;

    private String remark;

    private Date createTime;

    private Long creator;

    private Date updateTime;

    private Long updater;

    private Date orgCreateTime;

    private Date orgStopTime;

    private Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName == null ? null : orgFullName.trim();
    }

    public String getOrgAbr() {
        return orgAbr;
    }

    public void setOrgAbr(String orgAbr) {
        this.orgAbr = orgAbr == null ? null : orgAbr.trim();
    }

    public String getOrgTpcd() {
        return orgTpcd;
    }

    public void setOrgTpcd(String orgTpcd) {
        this.orgTpcd = orgTpcd == null ? null : orgTpcd.trim();
    }

    public String getOrgStcd() {
        return orgStcd;
    }

    public void setOrgStcd(String orgStcd) {
        this.orgStcd = orgStcd == null ? null : orgStcd.trim();
    }

    public String getOrgParentNo() {
        return orgParentNo;
    }

    public void setOrgParentNo(String orgParentNo) {
        this.orgParentNo = orgParentNo == null ? null : orgParentNo.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getOrgCreateTime() {
        return orgCreateTime;
    }

    public void setOrgCreateTime(Date orgCreateTime) {
        this.orgCreateTime = orgCreateTime;
    }

    public Date getOrgStopTime() {
        return orgStopTime;
    }

    public void setOrgStopTime(Date orgStopTime) {
        this.orgStopTime = orgStopTime;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}