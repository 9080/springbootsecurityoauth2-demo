package com.helon.oauth2.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: CustomUsernamePasswordAuthenticationFilter
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/17 5:42 PM
 * version: v1.0
 */
@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.setFilterProcessesUrl("/oauth2/login");
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //添加一些其他的处理，比如：验证码校验
        log.info("=======attemptAuthentication========");
        return super.attemptAuthentication(request, response);
    }
}