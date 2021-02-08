package com.imooc.autentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsAuthenticationProvider implements AuthenticationProvider {

  private UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    SmsAuthenticationToken token = (SmsAuthenticationToken) authentication;
    String mobile = (String) token.getPrincipal();
    UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
    // 如果用户数据不存在, 抛出异常
    if (userDetails == null) {
      throw new InternalAuthenticationServiceException("手机号为" + mobile + "的用户不存在");
    }

    SmsAuthenticationToken authenticationToken =
        new SmsAuthenticationToken(mobile, userDetails.getAuthorities());
    authenticationToken.setDetails(userDetails);
    return authenticationToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return SmsAuthenticationToken.class.isAssignableFrom(authentication);
  }

  public UserDetailsService getUserDetailsService() {
    return userDetailsService;
  }

  public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }
}
