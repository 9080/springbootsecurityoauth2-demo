package com.helon.oauth2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @className: RedisAuthenticationCodeService
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/13 3:47 PM
 * version: v1.0
 */
@Service
@Slf4j
public class RedisAuthorizationCodeService extends RandomValueAuthorizationCodeServices {
    /**授权码存入redis的key*/
    private static final String AUTH_CODE_REDIS_HASH_KEY = "oauth2:authCode";
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        try {
            redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    try {
                        redisConnection.hSet(AUTH_CODE_REDIS_HASH_KEY.getBytes("utf-8"),
                                code.getBytes("utf-8"), SerializationUtils.serialize(authentication));
                        redisConnection.expire(AUTH_CODE_REDIS_HASH_KEY.getBytes("utf-8"), 60 * 5);
                    } catch (UnsupportedEncodingException e) {
                        log.error("[存储AuthenticationCode]-不支持的编码格式");
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            log.error("[存储AuthenticationCode]-store error");
        }

    }

    @Override
    protected OAuth2Authentication remove(String code) {
        OAuth2Authentication auth2Authentication = null;
        try {
            //通过code获取OAuth2Authentication信息
            auth2Authentication = (OAuth2Authentication)redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    Object obj = null;
                    try {
                        obj = SerializationUtils.deserialize(redisConnection.hGet(AUTH_CODE_REDIS_HASH_KEY.getBytes("utf-8"),
                                code.getBytes("utf-8")));
                    } catch (UnsupportedEncodingException e) {
                        log.error("[获取AuthenticationCode]-不支持的编码格式");
                    }
                    return obj;
                }
            });
            log.debug("OAuth2Authentication信息:{}", auth2Authentication.toString());
            //清除OAuth2Authentication信息
            redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    try {
                        redisConnection.hDel(AUTH_CODE_REDIS_HASH_KEY.getBytes("utf-8"), code.getBytes("utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        log.error("[清除AuthenticationCode]-不支持的编码格式");
                    }
                    return null;
                }
            });

        } catch (Exception e) {
            log.error("[返回并清除AuthenticationCode]-get error");
        }
        return auth2Authentication;
    }

}