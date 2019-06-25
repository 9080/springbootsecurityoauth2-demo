package com.helon.oauth2.mybatis;

import com.helon.oauth2.server.CityService;
import com.helon.oauth2.server.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @className: OauthServerApplicationTest
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/4/28 10:43 AM
 * version: v1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OauthServerApplicationTest {
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    @Test
    public void insertCityTest() {
        City city = new City();
        city.setName("lisi");
        city.setState("CA");
        city.setCountry("CN");
        long id = cityService.insert(city);
        log.info("city插入成功:{}", id);
    }

    @Test
    public void insertUserTest() {
        User user = new User();
        user.setName("张三");
        Long id = userService.insert(user);
        log.info("user插入成功:{}", id);
    }

}