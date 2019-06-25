package com.helon.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @className: Oauth2ResourceServer
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/5 5:44 PM
 * version: v1.0
 */
@Configuration
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .authorizeRequests()
                //配置资源scope范围检测
                .antMatchers(HttpMethod.POST,"/api/user/getUserInfo").access("#oauth2.hasScope('read_userinfo')")
                .antMatchers(HttpMethod.POST,"/api/isLogin").access("#oauth2.hasScope('read_status')")
                .anyRequest()
                .authenticated();
    }
}