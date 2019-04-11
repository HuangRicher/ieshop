package com.seamwhole.serviceerpcore.model;

import java.math.BigDecimal;

public class Material {
    private Long id;

    private Long categoryid;

    private String name;

    private String mfrs;

    private BigDecimal packing;

    private BigDecimal safetystock;

    private String model;

    private String standard;

    private String color;

    private String unit;

    private String remark;

    private BigDecimal retailprice;

    private BigDecimal lowprice;

    private BigDecimal presetpriceone;

    private BigDecimal presetpricetwo;

    private Long unitid;

    private String firstoutunit;

    private String firstinunit;

    private String pricestrategy;

    private Boolean enabled;

    private String otherfield1;

    private String otherfield2;

    private String otherfield3;

    private String enableserialnumber;

    private Long tenantId;

    private String deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMfrs() {
        return mfrs;
    }

    public void setMfrs(String mfrs) {
        this.mfrs = mfrs == null ? null : mfrs.trim();
    }

    public BigDecimal getPacking() {
        return packing;
    }

    public void setPacking(BigDecimal packing) {
        this.packing = packing;
    }

    public BigDecimal getSafetystock() {
        return safetystock;
    }

    public void setSafetystock(BigDecimal safetystock) {
        this.safetystock = safetystock;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getRetailprice() {
        return retailprice;
    }

    public void setRetailprice(BigDecimal retailprice) {
        this.retailprice = retailprice;
    }

    public BigDecimal getLowprice() {
        return lowprice;
    }

    public void setLowprice(BigDecimal lowprice) {
        this.lowprice = lowprice;
    }

    public BigDecimal getPresetpriceone() {
        return presetpriceone;
    }

    public void setPresetpriceone(BigDecimal presetpriceone) {
        this.presetpriceone = presetpriceone;
    }

    public BigDecimal getPresetpricetwo() {
        return presetpricetwo;
    }

    public void setPresetpricetwo(BigDecimal presetpricetwo) {
        this.presetpricetwo = presetpricetwo;
    }

    public Long getUnitid() {
        return unitid;
    }

    public void setUnitid(Long unitid) {
        this.unitid = unitid;
    }

    public String getFirstoutunit() {
        return firstoutunit;
    }

    public void setFirstoutunit(String firstoutunit) {
        this.firstoutunit = firstoutunit == null ? null : firstoutunit.trim();
    }

    public String getFirstinunit() {
        return firstinunit;
    }

    public void setFirstinunit(String firstinunit) {
        this.firstinunit = firstinunit == null ? null : firstinunit.trim();
    }

    public String getPricestrategy() {
        return pricestrategy;
    }

    public void setPricestrategy(String pricestrategy) {
        this.pricestrategy = pricestrategy == null ? null : pricestrategy.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getOtherfield1() {
        return otherfield1;
    }

    public void setOtherfield1(String otherfield1) {
        this.otherfield1 = otherfield1 == null ? null : otherfield1.trim();
    }

    public String getOtherfield2() {
        return otherfield2;
    }

    public void setOtherfield2(String otherfield2) {
        this.otherfield2 = otherfield2 == null ? null : otherfield2.trim();
    }

    public String getOtherfield3() {
        return otherfield3;
    }

    public void setOtherfield3(String otherfield3) {
        this.otherfield3 = otherfield3 == null ? null : otherfield3.trim();
    }

    public String getEnableserialnumber() {
        return enableserialnumber;
    }

    public void setEnableserialnumber(String enableserialnumber) {
        this.enableserialnumber = enableserialnumber == null ? null : enableserialnumber.trim();
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