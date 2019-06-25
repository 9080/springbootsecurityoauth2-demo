package com.helon.oauth2.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @className: GantController
 * @summary: 自定义授权页面
 * @Description: TODO
 * @author: helon
 * date: 2019/6/18 3:11 PM
 * version: v1.0
 */
@Controller
@SessionAttributes("authorizationRequest")
public class GrantController {

    /** 
     * @Summary 自定义授权页面：覆盖框架中的/oauth/confirm_access方法
     * @Description 注意：@SessionAttributes("authorizationRequest") 一定要加上
     * @Author helon 
     * @Date 2019/6/18 5:34 PM 
     * @Param [model] 
     * @return org.springframework.web.servlet.ModelAndView 
     **/
    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("base-grant");
        view.addObject("clientId", authorizationRequest.getClientId());
        return view;
    }
}