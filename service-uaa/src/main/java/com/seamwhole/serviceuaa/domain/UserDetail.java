package com.seamwhole.serviceuaa.domain;

import com.seamwhole.serviceuaa.model.Authority;
import com.seamwhole.serviceuaa.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetail extends User implements UserDetails {

    private Set<RoleDO> roles = new HashSet<>();
    private Set<GrantedAuthority> authorities = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (RoleDO role : this.roles) {
            for (Authority authority : role.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(authority.getValue()));
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return getUserSerial()+"";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
