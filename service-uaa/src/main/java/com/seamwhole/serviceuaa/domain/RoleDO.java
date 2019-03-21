package com.seamwhole.serviceuaa.domain;

import com.seamwhole.serviceuaa.model.Authority;
import com.seamwhole.serviceuaa.model.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleDO extends Role {

    private Set<Authority> authorities = new HashSet<>();

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
