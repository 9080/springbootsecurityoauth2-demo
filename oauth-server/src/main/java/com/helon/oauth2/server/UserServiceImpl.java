package com.helon.oauth2.server;

import com.helon.oauth2.mybatis.User;
import com.helon.oauth2.mybatis.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: UserServiceImpl
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/5/18 9:39 AM
 * version: v1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertException(User user) {
        userMapper.insert(user);

        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNew(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNewException(User user) {
        userMapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void insertNestedException(User user) {
        userMapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void insertNested(User user) {
        userMapper.insert(user);
    }
}