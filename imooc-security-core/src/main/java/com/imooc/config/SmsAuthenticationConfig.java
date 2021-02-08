package com.imooc.config;

import com.imooc.autentication.SmsAuthenticationFilter;
import com.imooc.autentication.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsAuthenticationConfig
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Autowired private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

  @Autowired private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

  @Autowired private UserDetailsService userDetailsService;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
    smsAuthenticationFilter.setAuthenticationSuccessHandler(imoocAuthenticationSuccessHandler);
    smsAuthenticationFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
    // 设置认证管理器
    smsAuthenticationFilter.setAuthenticationManager(
        http.getSharedObject(AuthenticationManager.class));

    SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
    smsAuthenticationProvider.setUserDetailsService(userDetailsService);

    http.authenticationProvider(smsAuthenticationProvider)
        .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
