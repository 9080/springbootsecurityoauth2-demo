package com.helon.oauth2.stat;

import com.helon.oauth2.mybatis.City;
import com.helon.oauth2.mybatis.SysUser;
import com.helon.oauth2.server.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: TestController
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/5/14 9:53 AM
 * version: v1.0
 */
@RestController
public class TestController {

    @Autowired
    CityService cityService;

    @PostMapping("/list")
    public ResponseEntity list() {
        List<City> list = cityService.list();
        return ResponseEntity.ok(list);
    }

    @RequestMapping("/api/userinfo")
    public ResponseEntity<SysUser> userInfo() {
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@xx.com";
        SysUser sysUser = new SysUser();
        sysUser.setName(user.getName());
        sysUser.setUsername(email);
        return ResponseEntity.ok(sysUser);
    }
}