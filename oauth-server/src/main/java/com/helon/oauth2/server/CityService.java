package com.helon.oauth2.server;

import com.helon.oauth2.mybatis.City;

import java.util.List;

/**
 * @className: CityService
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/4/28 9:51 AM
 * version: v1.0
 */
public interface CityService {

    long insert(City city);

    long insertRequiresNew(City city);

    City getCityById(long id);

    List<City> list();

    long insertNested(City city);
}