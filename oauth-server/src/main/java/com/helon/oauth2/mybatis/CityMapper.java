package com.helon.oauth2.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {

    @Insert("insert into city(name,state,country) values(#{name}, #{state}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long insert(City city);

    @Select("select * from city where id = #{id}")
    City findById(long id);

    @Select("select * from city")
    List<City> queryList();
}
