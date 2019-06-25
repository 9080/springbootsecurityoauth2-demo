package com.helon.oauth2.mybatis;

import lombok.Data;

/**
 * @className: City
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/4/27 9:39 PM
 * version: v1.0
 */
@Data
public class City {

    private Long id;
    private String name;
    private String state;
    private String country;
}