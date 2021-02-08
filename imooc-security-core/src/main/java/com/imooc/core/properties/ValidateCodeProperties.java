package com.imooc.core.properties;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ValidateCodeProperties {

  private int size = 6;

  private int expiredIn = 60;

  /**
   * 需要发送验证码的url
   */
  private Set<String> urls = new HashSet<>();

}
