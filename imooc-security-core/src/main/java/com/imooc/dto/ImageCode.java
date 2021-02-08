package com.imooc.dto;

import lombok.ToString;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/** 图片验证码 */
@ToString
public class ImageCode extends ValidateCode {

  // 页面上要展示的图片
  private BufferedImage image;

  public ImageCode(BufferedImage image, String code, int seconds) {
    super(code, seconds);
    this.image = image;
  }

  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    super(code, expireTime);
    this.image = image;
  }

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }
}
