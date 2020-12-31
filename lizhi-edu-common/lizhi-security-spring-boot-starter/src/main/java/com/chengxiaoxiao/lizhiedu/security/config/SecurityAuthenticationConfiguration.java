package com.chengxiaoxiao.lizhiedu.security.config;


import com.chengxiaoxiao.lizhiedu.security.filter.CustomAuthenticationFilter;
import com.chengxiaoxiao.lizhiedu.security.filter.JwtAuthenticationTokenFilter;
import com.chengxiaoxiao.lizhiedu.security.provider.UserAuthenticationProvider;
import com.chengxiaoxiao.lizhiedu.security.handler.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


/**
 * Spring Security配置类，添加用户权限校验
 *
 * @Author: Cheng XiaoXiao
 * @Date: 2020/11/3 11:18:02
 * @Description:
 */
@Configuration
@EnableWebSecurity
@ConditionalOnWebApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityAuthenticationConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    SecurityProperties securityProperties;

    /**
     * 自定义登录成功处理器
     */
    @Resource
    private UserLoginSuccessHandler userLoginSuccessHandler;
    /**
     * 自定义登录失败处理器
     */
    @Resource
    private UserLoginFailureHandler userLoginFailureHandler;
    /**
     * 自定义注销成功处理器
     */
    @Resource
    private UserLogoutSuccessHandler userLogoutSuccessHandler;
    /**
     * 自定义暂无权限处理器
     */
    @Resource
    private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;
    /**
     * 自定义未登录的处理器
     */
    @Resource
    private UserLoginDeniedHandler userLoginDeniedHandler;
    /**
     * 自定义登录逻辑验证器
     */
    @Resource
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * jwt过滤器
     */
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    /**
     * 配置自定义登录验证逻辑
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        if (securityProperties.isEnabled()) {
            auth.authenticationProvider(userAuthenticationProvider);
        }
    }

    /**
     * 注册自定义的JSON方式登录逻辑
     *
     * @return 自定义验证器
     */
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(userLoginSuccessHandler);
        filter.setAuthenticationFailureHandler(userLoginFailureHandler);
        filter.setFilterProcessesUrl("/user/login");

        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (securityProperties.isEnabled()) {
            http.authorizeRequests()
                    // 允许对于网站静态资源的无授权访问
                    .antMatchers(
                            HttpMethod.GET,
                            "/",
                            "/*.html",
                            "/favicon.ico",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js"
                    ).permitAll()
                    //不进行权限验证的请求或资源(从配置文件中读取)
                    .antMatchers(securityProperties.getIgnorePath().split(",")).permitAll()
                    //其他的需要登陆后才能访问
                    .anyRequest().authenticated()
                    .and()
                    //配置未登录自定义处理类
                    .httpBasic().authenticationEntryPoint(userLoginDeniedHandler)
                    .and()
                    //配置登录地址
                    .formLogin()
                    .loginProcessingUrl("/login")
                    //配置登录成功自定义处理类
                    .successHandler(userLoginSuccessHandler)
                    //配置登录失败自定义处理类
                    .failureHandler(userLoginFailureHandler)
                    .and()
                    //配置登出地址
                    .logout()
                    .logoutUrl("/user/logout")
                    //配置用户登出自定义处理类
                    .logoutSuccessHandler(userLogoutSuccessHandler)
                    .and()
                    //配置没有权限自定义处理类
                    .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler)
                    .and()
                    // 开启跨域
                    .cors()
                    .and()
                    // 取消跨站请求伪造防护
                    .csrf().disable();

            // 添加JWT filter
            http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

            //用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
            http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        } else {
            http.authorizeRequests()
                    // 允许对资源全部访问
                    .antMatchers("/**").permitAll()
                    //其他的需要登陆后才能访问
                    .anyRequest().authenticated()
                    .and()
                    // 开启跨域
                    .cors()
                    .and()
                    // 取消跨站请求伪造防护
                    .csrf().disable();
        }
        // 基于Token不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
    }
}
