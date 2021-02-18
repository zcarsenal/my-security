package com.imooc.security.browser;

import com.imooc.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 获取用户信息类
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService , SocialUserDetailsService {

  @Autowired private SysUserService sysUserService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //
    log.info("username is : {}", username);
    //    return new User(
    //        username,
    //        "123456",
    //        true,
    //        true,
    //        true,
    //        false,
    //        AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    String password = passwordEncoder.encode("123456");
    log.info("password is {}", password);
    return new MyUserDetail(
        username, password, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }

  @Override
  public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
    String password = passwordEncoder.encode("123456");
    log.info("password is {}", password);
    return new SocialUser(
            userId, password, true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }
}
