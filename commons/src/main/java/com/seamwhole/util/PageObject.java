package com.seamwhole.util;

import java.util.List;

public class PageObject<T> {

    private int pageNo = 1;  //当前页
    private int pageSize = 10; //每页个数
    private long totalCount;  //总记录数
    private int totalPages;  //总页数--只读
    private List<T> pageList;  //每页对应的集合泛型
    public int getPageNo() {
        return pageNo;
    }
    //当前页码不能小于1不能大于总页数
    public void setPageNo(int pageNo) {
        if(pageNo<1)
            this.pageNo = 1;
        else if(pageNo > totalPages)
            this.pageNo = totalPages;
        else
            this.pageNo = pageNo;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    //总记录数决定总页数
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.totalPages =Integer.valueOf (((this.totalCount%this.pageSize==0)?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1)+"");
    }
    public long getTotalCount() {
        return totalCount;
    }

    //只读
    public int getTotalPages() {
        return totalPages;
    }


    public List<T> getPageList() {
        return pageList;
    }
    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
    public PageObject(int pageNo, int pageSize, long totalCount, int totalPages,
                    List<T> pageList) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
        this.pageList = pageList;
    }
    public PageObject() {
        super();
    }
}
