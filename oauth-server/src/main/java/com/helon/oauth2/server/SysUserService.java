package com.helon.oauth2.server;

import com.helon.oauth2.mybatis.SysUser;

import java.util.List;

public interface SysUserService {

    SysUser save(SysUser sysUser);

    SysUser findById(Long id);

    SysUser findByUsername(String username);

    SysUser findByTel(String tel);

    List<SysUser> findAll();

    void delete(Long id);

    List<SysUser> findByName(String name);

}
