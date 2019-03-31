package com.seamwhole.util;

import java.io.Serializable;
import java.util.List;

/**
 *  分页工具
 */
public class PagesInfo<T> implements Serializable {

    //总记录数
    private long totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int currPage;
    //列表数据
    private List<T> list;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PagesInfo(int pageNo, int pageSize, long totalCount, int totalPages, List<T> pageList) {
        super();
        this.currPage = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPages;
        this.list = pageList;
    }
    public PagesInfo() {
        super();
    }
}
