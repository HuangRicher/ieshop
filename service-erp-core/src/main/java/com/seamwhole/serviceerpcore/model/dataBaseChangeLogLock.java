package com.seamwhole.serviceerpcore.model;

import java.util.Date;

public class dataBaseChangeLogLock {
    private Integer id;

    private Boolean locked;

    private Date lockgranted;

    private String lockedby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getLockgranted() {
        return lockgranted;
    }

    public void setLockgranted(Date lockgranted) {
        this.lockgranted = lockgranted;
    }

    public String getLockedby() {
        return lockedby;
    }

    public void setLockedby(String lockedby) {
        this.lockedby = lockedby == null ? null : lockedby.trim();
    }
}