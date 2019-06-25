package com.helon.oauth2.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


/**
 * @className: OAuth2AuthorizationConfig
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/5 4:05 PM
 * version: v1.0
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientapp")
                .secret("123")
                .redirectUris("https://www.baidu.com")
                .authorizedGrantTypes("authorization_code")
                .scopes("read_userinfo");
    }


}