package com.studyinghome.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studyinghome.business.entity.Role;
import com.studyinghome.business.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-10 下午 4:11
 */
@Data
public class SysUser implements UserDetails {
    private User user;
    /**
     * 用户角色
     */
    private List<Role> roles;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPwd();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
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
        if (this.user.getStatus() == 1) {
            return true;
        } else {
            return false;
        }
    }
}
