package com.imooc.dto;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/** 图片验证码 */
public class ImageCode {

  // 页面上要展示的图片
  private BufferedImage image;

  // 图片中的随机数
  private String code;

  // 过期时间
  private LocalDateTime expireTime;

  public ImageCode(BufferedImage image, String code, int seconds) {
    this(image, code, null);
    this.expireTime = LocalDateTime.now().plusSeconds(seconds);
  }

  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    this.image = image;
    this.code = code;
    this.expireTime = expireTime;
  }

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public LocalDateTime getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
  }

    public boolean isExpired() {
    return LocalDateTime.now().isAfter(this.getExpireTime());
    }
}
