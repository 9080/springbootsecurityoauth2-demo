package com.helon.oauth2.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @className: DataSourceConfig
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/11 6:22 PM
 * version: v1.0
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}