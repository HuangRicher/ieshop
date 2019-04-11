package com.seamwhole.serviceerpcore.model;

import java.math.BigDecimal;

public class Supplier {
    private Long id;

    private String supplier;

    private String contacts;

    private String phonenum;

    private String email;

    private String description;

    private Byte isystem;

    private String type;

    private Boolean enabled;

    private BigDecimal advancein;

    private BigDecimal beginneedget;

    private BigDecimal beginneedpay;

    private BigDecimal allneedget;

    private BigDecimal allneedpay;

    private String fax;

    private String telephone;

    private String address;

    private String taxnum;

    private String bankname;

    private String accountnumber;

    private BigDecimal taxrate;

    private Long tenantId;

    private String deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getIsystem() {
        return isystem;
    }

    public void setIsystem(Byte isystem) {
        this.isystem = isystem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public BigDecimal getAdvancein() {
        return advancein;
    }

    public void setAdvancein(BigDecimal advancein) {
        this.advancein = advancein;
    }

    public BigDecimal getBeginneedget() {
        return beginneedget;
    }

    public void setBeginneedget(BigDecimal beginneedget) {
        this.beginneedget = beginneedget;
    }

    public BigDecimal getBeginneedpay() {
        return beginneedpay;
    }

    public void setBeginneedpay(BigDecimal beginneedpay) {
        this.beginneedpay = beginneedpay;
    }

    public BigDecimal getAllneedget() {
        return allneedget;
    }

    public void setAllneedget(BigDecimal allneedget) {
        this.allneedget = allneedget;
    }

    public BigDecimal getAllneedpay() {
        return allneedpay;
    }

    public void setAllneedpay(BigDecimal allneedpay) {
        this.allneedpay = allneedpay;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTaxnum() {
        return taxnum;
    }

    public void setTaxnum(String taxnum) {
        this.taxnum = taxnum == null ? null : taxnum.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber == null ? null : accountnumber.trim();
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
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