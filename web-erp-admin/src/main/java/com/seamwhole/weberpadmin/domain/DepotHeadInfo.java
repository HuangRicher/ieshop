package com.seamwhole.weberpadmin.domain;

import java.math.BigDecimal;

public class DepotHeadInfo {

    private Integer currentPage;
    private Integer pageSize;
    private Integer organId;
    private Integer projectId;
    private String depotIds;
    private String beginTime;
    private String endTime;
    private String type;
    private String supType;
    private Long id;
    private String beanJson;
    private String info;
    private String inserted;
    private String deleted;
    private String updated;
    private BigDecimal preTotalPrice;
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getDepotIds() {
        return depotIds;
    }

    public void setDepotIds(String depotIds) {
        this.depotIds = depotIds;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupType() {
        return supType;
    }

    public void setSupType(String supType) {
        this.supType = supType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeanJson() {
        return beanJson;
    }

    public void setBeanJson(String beanJson) {
        this.beanJson = beanJson;
    }

    public String getInserted() {
        return inserted;
    }

    public void setInserted(String inserted) {
        this.inserted = inserted;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public BigDecimal getPreTotalPrice() {
        return preTotalPrice;
    }

    public void setPreTotalPrice(BigDecimal preTotalPrice) {
        this.preTotalPrice = preTotalPrice;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
