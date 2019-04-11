package com.seamwhole.serviceerpcore.model;

public class MaterialProperty {
    private Long id;

    private String nativename;

    private Boolean enabled;

    private String sort;

    private String anothername;

    private String deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNativename() {
        return nativename;
    }

    public void setNativename(String nativename) {
        this.nativename = nativename == null ? null : nativename.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getAnothername() {
        return anothername;
    }

    public void setAnothername(String anothername) {
        this.anothername = anothername == null ? null : anothername.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }
}