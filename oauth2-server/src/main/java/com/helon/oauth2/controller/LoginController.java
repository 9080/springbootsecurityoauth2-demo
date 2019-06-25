package com.helon.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: StaticResourceController
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/18 11:16 AM
 * version: v1.0
 */
@Controller
@RequestMapping("/oauth")
public class LoginController {

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

}