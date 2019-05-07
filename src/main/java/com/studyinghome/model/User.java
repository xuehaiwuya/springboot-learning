package com.studyinghome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * 用户信息
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:02
 */
@Getter
@Setter
@Alias("user")
public class User implements UserDetails {
    private int uid;
    private String name;
    @JsonIgnore
    private String pwd;
    private String phone;
    private String photo;
    private String email;
    private Integer status;
    private List<Role> roles;
    private Date createTime;
    private Date updateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (status == 1) {
            return true;
        } else {
            return false;
        }
    }
}
