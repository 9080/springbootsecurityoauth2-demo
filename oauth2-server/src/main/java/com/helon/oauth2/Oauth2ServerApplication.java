package com.helon.oauth2;

import com.chtwm.htool.cache.redis.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @className: Oauth2ServerApplication
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/10 4:38 PM
 * version: v1.0
 */
@SpringBootApplication
@Import({RedisUtil.class})
public class Oauth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }
}