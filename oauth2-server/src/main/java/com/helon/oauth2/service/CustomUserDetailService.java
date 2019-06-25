package com.helon.oauth2.service;

import com.helon.oauth2.config.SecurityUserInfo;
import com.helon.oauth2.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @className: CustomUserDetailService
 * @summary: 自定义 用户登录身份认证
 * @Description: 当用户登录时会进入此类的loadUserByUsername方法对用户进行验证，
 *               验证成功后会被保存在当前回话的principal对象中，系统获取当前登录对象信息方法
 *               WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
 * @author: helon
 * date: 2019/6/17 3:23 PM
 * version: v1.0
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 通过username查询用户信息

        //2. 判断用户是否存在

        //3. 获取用户所拥有的角色权限 （可忽略）

        //4. 返回userDetail对象
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1002L);
        userInfo.setName(username);
        userInfo.setPassword("$2a$10$lhbudCS96A.SlXO9GH4hLO7EtUbHK5NTACg3cdsdWfViB4aCrIH4C");
        SecurityUserInfo securityUserInfo = new SecurityUserInfo(userInfo);
        return securityUserInfo;
    }
}