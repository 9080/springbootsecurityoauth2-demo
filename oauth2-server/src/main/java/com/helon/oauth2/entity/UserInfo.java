package com.helon.oauth2.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: UserInfo
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/17 3:47 PM
 * version: v1.0
 */
@Data
public class UserInfo implements Serializable {

    private Long id;

    private String name;

    private String password;

}