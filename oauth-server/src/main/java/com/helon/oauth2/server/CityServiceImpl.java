package com.helon.oauth2.server;

import com.helon.oauth2.mybatis.City;
import com.helon.oauth2.mybatis.CityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: CityServiceImpl
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/4/28 9:52 AM
 * version: v1.0
 */
@Service
public class CityServiceImpl implements CityService {
    @Resource
    private CityMapper cityMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public long insert(City city) {
        long id = cityMapper.insert(city);
        return id;
    }

    @Override
    public City getCityById(long id) {
        City city = cityMapper.findById(id);
        return city;
    }


    @Override
    public List<City> list() {
        return cityMapper.queryList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long insertRequiresNew(City city) {
        long id = cityMapper.insert(city);
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public long insertNested(City city) {
        long id = cityMapper.insert(city);
        return id;
    }
}