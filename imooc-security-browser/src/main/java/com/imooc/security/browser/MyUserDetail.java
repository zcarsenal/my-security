package com.imooc.security.browser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 用户校验逻辑类
 */
public class MyUserDetail implements UserDetails {

  private String username;

  private String password;

  private Boolean isEnabled;

  private List<GrantedAuthority> authorityList;

  public MyUserDetail(String username, String password, Boolean isEnabled, List<GrantedAuthority> authorityList) {
    this.username = username;
    this.password = password;
    this.isEnabled = isEnabled;
    this.authorityList = authorityList;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorityList;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnabled;
  }
}
