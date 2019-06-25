package com.helon.oauth2.config;

import com.helon.oauth2.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @className: SecurityUserInfo
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/17 3:51 PM
 * version: v1.0
 */
public class SecurityUserInfo extends UserInfo implements UserDetails {

    public SecurityUserInfo(UserInfo userInfo){
        if (userInfo != null) {
            this.setId(userInfo.getId());
            this.setName(userInfo.getName());
            this.setPassword(userInfo.getPassword());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getName();
    }

    //账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //账户是否未禁用
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否启用
    @Override
    public boolean isEnabled() {
        return true;
    }
}