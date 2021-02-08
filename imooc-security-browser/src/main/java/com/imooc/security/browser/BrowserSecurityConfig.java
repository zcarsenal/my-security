package com.imooc.security.browser;

import com.imooc.core.properties.SecurityProperties;
import com.imooc.filter.ValidatorCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private SecurityProperties securityProperties;

  @Autowired private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

  @Autowired private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

  @Autowired private ValidatorCodeFilter validatorCodeFilter;

  /**
   * 用户数据加密类
   *
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
        //        .loginPage("/my_login.html") // 自定义登录页面
        .loginPage("/authentication/require") // 先跳转到controller 在进行转发到html还是json
        .usernameParameter("username1")
        .passwordParameter("password1")
        // 告知spring-security 使用UsernamePasswordAuthenticationFilter处理认证请求
        .loginProcessingUrl("/myLogin")
        //  http.httpBasic()
        //        .and()
        //        .rememberMe()
        //        .rememberMeParameter("re-me")
        .successHandler(imoocAuthenticationSuccessHandler)
        .failureHandler(imoocAuthenticationFailureHandler)
        .and()
        .addFilterBefore(validatorCodeFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(
            "/authentication/require", securityProperties.getBrowser().getLoginUrl(), "/code/image")
        .permitAll() // 不需要身份认证
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable(); // 不关闭此处 登录不成功
  }
}
