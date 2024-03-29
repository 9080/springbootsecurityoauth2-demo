package com.helon.oauth2.server;

import com.helon.oauth2.mybatis.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @className: UserDetailsServiceImpl
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/5 3:40 PM
 * version: v1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = new UserDetails() {
            SysUser sysUser = sysUserService.findByUsername(s);
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {

                return sysUser.getPassword();
            }

            @Override
            public String getUsername() {
                return sysUser.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        return userDetails;
    }
}