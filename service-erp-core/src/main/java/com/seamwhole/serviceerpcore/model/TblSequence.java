package com.seamwhole.serviceerpcore.model;

public class TblSequence {
    private String seqName;

    private Long minValue;

    private Long maxValue;

    private Long currentVal;

    private Integer incrementVal;

    private String remark;

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName == null ? null : seqName.trim();
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public Long getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(Long currentVal) {
        this.currentVal = currentVal;
    }

    public Integer getIncrementVal() {
        return incrementVal;
    }

    public void setIncrementVal(Integer incrementVal) {
        this.incrementVal = incrementVal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}