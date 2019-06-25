package com.helon.oauth2.config;

import com.helon.oauth2.service.CustomClientDetailService;
import com.helon.oauth2.service.RedisAuthorizationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


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
 /*   @Autowired
    private DataSource dataSource;*/
    //自定义的查询三方应用信息service
    @Autowired
    private CustomClientDetailService customClientDetailService;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private RedisAuthorizationCodeService redisAuthenticationCodeService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //持久化access_token和refresh_token信息，如果不配置则会存储到内存中，假如服务重启就会失效，多服务负载也可能获取不到
    @Bean
    public TokenStore tokenStore() { //(2)
        return new RedisTokenStore(redisConnectionFactory);
    }

    //持久化用户授权批准记录，如果批准通过将在一段时间内不再弹出授权页面
    @Bean
    public ApprovalStore approvalStore() { //(3)
//        return new JdbcApprovalStore(dataSource);
        TokenApprovalStore tokenApprovalStore = new TokenApprovalStore();
        tokenApprovalStore.setTokenStore(tokenStore());
        return tokenApprovalStore;
    }
    //持久化授权码信息，如果不配置则会存储到内存中，假如服务重启就会失效，多服务负载也可能获取不到
 /*   @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }*/

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception { //(4)
        endpoints.approvalStore(approvalStore())
                .tokenStore(tokenStore())
                .authorizationCodeServices(redisAuthenticationCodeService)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception { //(5)
//        clients.jdbc(dataSource);
        //实现ClientDetailService接口，定义自己的CustomClientDetailService
        clients.withClientDetails(customClientDetailService);

       /* //内存存储
        clients.inMemory()
                .withClient("clientapp")
                .secret("{bcrypt}$2a$04$LoD4ez17SMl3Md/c930MYuz8fIuFRWOtKk5ow1UcxdJXwDMomcx2K")
                .redirectUris("https://www.baidu.com")
                .authorizedGrantTypes("authorization_code")
                .scopes("read_userinfo");*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return bCryptPasswordEncoder;
    }
}