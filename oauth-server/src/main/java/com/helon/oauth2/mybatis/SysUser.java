package com.helon.oauth2.mybatis;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: SysUser
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/5 3:17 PM
 * version: v1.0
 */
@Data
public class SysUser implements Serializable {

    private Long id;

    private String name;

    private String username;

    private String password;

    private String tel;

    private String gender;

    private Date createTime;

}