package com.helon.oauth2.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * @className: CustomClientDetailService
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/13 1:43 PM
 * version: v1.0
 */
@Service
public class CustomClientDetailService implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        //1.从数据库中查询client信息

        //构造函数参数：clientId,resourceIds,scopes, grantTypes, authorities,redirectUris
        BaseClientDetails baseClientDetails = new BaseClientDetails("clientapp", null,
                "read_userinfo,read_status",
                "authorization_code,refresh_token",
                null,
                "https://www.baidu.com");
        //如果autoApproveScopes不配置，则isAutoApprove默认为false，
        // 如果autoApproveScopes配置为true，则所有scope都自动授权
//        baseClientDetails.isAutoApprove("");
        baseClientDetails.setClientSecret("{bcrypt}$2a$04$LoD4ez17SMl3Md/c930MYuz8fIuFRWOtKk5ow1UcxdJXwDMomcx2K");
        //AccessToken超时时长
        baseClientDetails.setAccessTokenValiditySeconds(60 * 10);
        //RefreshToken超时时长
        baseClientDetails.setRefreshTokenValiditySeconds(60 * 60 * 24 * 30);
        return baseClientDetails;
    }
}