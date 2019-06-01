package com.hotel.admin.config;

import com.hotel.admin.redis.UserInfoCache;
import com.hotel.admin.security.JwtAuthenticationFilter;
import com.hotel.admin.security.JwtAuthenticationProvider;
import com.hotel.admin.security.JwtLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security Config
 *
 * @author Louis
 * @date Nov 20, 2018
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserInfoCache userInfoCache;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.authorizeRequests().
                // 跨域预检请求
                antMatchers(HttpMethod.OPTIONS, "/**").permitAll().
                // web jars
                antMatchers("/webjars/**").permitAll().
                antMatchers("/static/**").permitAll().
                antMatchers("/favicon.ico").permitAll().
                antMatchers("/websocket/**").permitAll().
                // 首页和登录页面
                antMatchers("/").permitAll().
                antMatchers("/login").permitAll().
                antMatchers("/img/**").permitAll().
                antMatchers("/document/preview/**").permitAll().
                antMatchers("/document/queryByRelId/**").permitAll().
                // 验证码
                antMatchers("/captcha.jpg**").permitAll()
                // 服务监控
//            .antMatchers("/actuator/**").permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated();
//        http.formLogin().loginPage("/login").successHandler(new JwtLoginSuccessHandler());
        // 退出登录处理器
//        http.logout().logoutSuccessHandler(new JwtLogoutSuccessHandler(userInfoCache));
//        http.logout().logoutUrl("/signOut").logoutSuccessUrl("login").deleteCookies("JSESSIONID");
        // token验证过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager(),userInfoCache), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}