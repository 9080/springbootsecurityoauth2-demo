package com.helon.oauth2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @className: EncyptPwd
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/18 1:45 PM
 * version: v1.0
 */
public class EncyptPwd {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("abc123"));
    }
}