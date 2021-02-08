package com.imooc.dto;

import lombok.ToString;

import java.time.LocalDateTime;

/** 验证码基类 */
@ToString
public class ValidateCode {

  // 图片中的随机数
  private String code;

  // 过期时间
  private LocalDateTime expireTime;

  public ValidateCode(String code, int seconds) {
    this(code, null);
    this.expireTime = LocalDateTime.now().plusSeconds(seconds);
  }

  public ValidateCode(String code, LocalDateTime expireTime) {
    this.code = code;
    this.expireTime = expireTime;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(this.getExpireTime());
  }

  public LocalDateTime getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
  }
}
