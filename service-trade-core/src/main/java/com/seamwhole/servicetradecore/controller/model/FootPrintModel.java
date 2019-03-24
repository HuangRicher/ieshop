package com.seamwhole.servicetradecore.controller.model;

public class FootPrintModel {

    private Integer userId;
    private Integer footprintId;
    private Integer pageNum;
    private Integer pageSize;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFootprintId() {
        return footprintId;
    }

    public void setFootprintId(Integer footprintId) {
        this.footprintId = footprintId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
