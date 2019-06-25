package com.helon.oauth2.mybatis;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysUserMapper {
    @Insert("INSERT INTO sys_user(id,name,username,password,tel,gender,createTime) VALUES(#{id},#{name},#{username},#{password},#{tel},#{gender},#{createTime})")
    void insert(SysUser sysUser);

    @Delete("DELETE FROM sys_user WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE sys_user SET name=#{name},username=#{username},password=#{password},tel=#{tel},gender=#{gender},createTime=#{createTime} WHERE id =#{id}")
    int update(SysUser sysUser);

    @Results(id="baseResultMap", value = {
            @Result(column = "id", property = "id", id=true),
            @Result(column = "name", property = "name"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "createTime", property = "createTime"),
    })
    @Select("SELECT * FROM sys_user WHERE id=#{id}")
    SysUser findById(Long id);

    @ResultMap("baseResultMap")
    @Select("SELECT * FROM sys_user WHERE username=#{username}")
    SysUser findByUsername(String username);

    @ResultMap("baseResultMap")
    @Select("SELECT * FROM sys_user WHERE tel=#{tel}")
    SysUser findByTel(String tel);

    @ResultMap("baseResultMap")
    @Select("SELECT * FROM sys_user")
    List<SysUser> findAll();

    @ResultMap("baseResultMap")
    @Select("SELECT * FROM sys_user WHERE name like #{name}")
    List<SysUser> findByName(String name);

}
