package com.helon.oauth2.config;

import com.helon.oauth2.handler.CustomUsernamePasswordAuthenticationFilter;
import com.helon.oauth2.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @className: SecurityConfig
 * @summary: TODO
 * @Description: TODO
 * @author: helon
 * date: 2019/6/6 11:17 AM
 * version: v1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService customUserDetailService;


    /** 
     * @Summary 设置 userDetailsService 服务
     * @Description TODO 
     * @Author helon 
     * @Date 2019/6/17 4:22 PM 
     * @Param [auth] 
     * @return void 
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置用户认证处理service，并制定密码加密器
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
        //不删除用户信息，以便后续使用
        auth.eraseCredentials(false);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf验证
        http.csrf().disable()
                // 基于token，所以不需要session  如果基于session 则表使用这段代码
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                //对请求进行认证  url认证配置顺序为：1.先配置放行不需要认证的 permitAll() 2.然后配置 需要特定权限的 hasRole() 3.最后配置 anyRequest().authenticated()
                .authorizeRequests()
                // 所有/oauth2/login 请求的都放行 不做认证即不需要登录即可访问
                .antMatchers("/oauth2/login").permitAll()
                //.antMatchers("/auth/v1/api/login/**","/auth/v1/api/module/tree/**","/auth/v1/api/grid/**").permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("oauth/**").permitAll()
                // 其他请求都需要进行认证,认证通过够才能访问
                .anyRequest().authenticated()
                .and().exceptionHandling()
                // 认证配置当用户请求了一个受保护的资源，但是用户没有通过登录认证，则抛出登录认证异常，MyAuthenticationEntryPointHandler类中commence()就会调用
//                .authenticationEntryPoint(myAuthenticationEntryPoint())
                //用户已经通过了登录认证，在访问一个受保护的资源，但是权限不够，则抛出授权异常，MyAccessDeniedHandler类中handle()就会调用
//                .accessDeniedHandler(myAccessDeniedHandler())
                .and()
                //
                .formLogin()
                // 登录url
                .loginProcessingUrl("/oauth2/login")  // 此登录url 和Controller 无关系
                // .loginProcessingUrl("/auth/v1/api/login/enter")  //使用自己定义的Controller 中的方法 登录会进入Controller 中的方法
                // username参数名称 后台接收前端的参数名
                .usernameParameter("userAccount")
                //登录密码参数名称 后台接收前端的参数名
                .passwordParameter("userPwd")
                //登录成功跳转路径
//                .successForwardUrl("/")
                //登录失败跳转路径
//                .failureUrl("/")
                //登录页面路径
                .loginPage("/oauth2/toLogin")
                .permitAll()
                //登录成功后 MyAuthenticationSuccessHandler类中onAuthenticationSuccess（）被调用
//                .successHandler(myAuthenticationSuccessHandler())
                //登录失败后 MyAuthenticationFailureHandler 类中onAuthenticationFailure（）被调用
//                .failureHandler(myAuthenticationFailureHandler())
                .and()
                .logout()
                //退出系统url
                .logoutUrl("/oauth2/logout")
                //退出系统后的url跳转
                .logoutSuccessUrl("/")
                //退出系统后的 业务处理
//                .logoutSuccessHandler(myLogoutSuccessHandler())
                .permitAll();
//                .invalidateHttpSession(true)
//                .and()
                //登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                // 勾选Remember me登录会在PERSISTENT_LOGINS表中，生成一条记录
//                .rememberMe()
                //cookie的有效期（秒为单位
//                .tokenValiditySeconds(3600);
        // 加入自定义UsernamePasswordAuthenticationFilter替代原有Filter
        http.addFilterAt(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //在 beforeFilter 之前添加 自定义 filter
//        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/toLogin", "/oauth/login", "oauth/authorize")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/oauth/toLogin")
                .loginProcessingUrl("/oauth/login");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    public UsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
        return new CustomUsernamePasswordAuthenticationFilter(authenticationManager());
    }*/

}