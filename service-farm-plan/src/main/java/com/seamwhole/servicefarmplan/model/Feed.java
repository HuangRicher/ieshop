package com.seamwhole.servicefarmplan.model;

public class Feed {
    private Integer id;

    private String name;

    private String constituentPart;

    private String constituentElement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getConstituentPart() {
        return constituentPart;
    }

    public void setConstituentPart(String constituentPart) {
        this.constituentPart = constituentPart == null ? null : constituentPart.trim();
    }

    public String getConstituentElement() {
        return constituentElement;
    }

    public void setConstituentElement(String constituentElement) {
        this.constituentElement = constituentElement == null ? null : constituentElement.trim();
    }
}