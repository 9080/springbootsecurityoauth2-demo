package com.helon.oauth2.server;

import com.helon.oauth2.mybatis.User;

import java.util.List;

public interface UserService {

    Long insert(User user);

    User findById(Long id);

    List<User> list();

    void insertException(User user);

    void insertRequiresNew(User user);

    void insertRequiresNewException(User user);

    void insertNested(User user);

    void insertNestedException(User user);

}
