package com.helon.oauth2.server;

import com.helon.oauth2.mybatis.SysUser;
import com.helon.oauth2.mybatis.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: SysUserServiceImpl
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/5 3:25 PM
 * version: v1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserMapper sysUserMapper;


    @Override
    public SysUser save(SysUser sysUser) {
        if(sysUser.getId() != null){
            sysUserMapper.update(sysUser);
        } else {
            sysUser.setId(System.currentTimeMillis());
            sysUserMapper.insert(sysUser);
        }
        return sysUser;
    }

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.findById(id);
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }

    @Override
    public SysUser findByTel(String tel) {
        return sysUserMapper.findByTel(tel);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public void delete(Long id) {
        sysUserMapper.delete(id);
    }

    @Override
    public List<SysUser> findByName(String name) {
        return sysUserMapper.findByName("%" + name + "");
    }
}