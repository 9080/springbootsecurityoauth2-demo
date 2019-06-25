package com.helon.oauth2.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user(name) values (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long insert(User user);

    @Select("select * from user where id = #{id}")
    User findById(Long id);

    @Select("select * from user")
    List<User> list();
}
