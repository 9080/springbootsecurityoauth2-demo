package com.helon.oauth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ResourceController
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/11 10:06 AM
 * version: v1.0
 */
@RestController
public class ResourceController {

    @PostMapping("/api/user/getUserInfo")
    public Authentication getUserInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PostMapping("/api/isLogin")
    public boolean isLogin() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }
}