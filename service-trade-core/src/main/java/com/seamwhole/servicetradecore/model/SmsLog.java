package com.seamwhole.servicetradecore.model;

public class SmsLog {
    private Integer id;

    private Integer userId;

    private String phone;

    private Long logDate;

    private String smsCode;

    private Long sendStatus;

    private String smsText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Long getLogDate() {
        return logDate;
    }

    public void setLogDate(Long logDate) {
        this.logDate = logDate;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode == null ? null : smsCode.trim();
    }

    public Long getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Long sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText == null ? null : smsText.trim();
    }
}