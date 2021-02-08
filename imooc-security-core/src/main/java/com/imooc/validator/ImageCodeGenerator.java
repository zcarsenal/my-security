package com.imooc.validator;

import com.imooc.core.properties.SecurityProperties;
import com.imooc.dto.ImageCode;
import com.imooc.util.VerificationCodeUtil;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

public class ImageCodeGenerator implements ValidatorCodeGenerator {

  private SecurityProperties securityProperties;

  @Override
  public ImageCode generator(ServletWebRequest request) {

    return VerificationCodeUtil.generateVerificationCode(
        ServletRequestUtils.getIntParameter(
            request.getRequest(), "width", securityProperties.getCode().getImage().getWidth()),
        ServletRequestUtils.getIntParameter(
            request.getRequest(), "height", securityProperties.getCode().getImage().getHeight()),
        securityProperties.getCode().getImage().getSize(),
        securityProperties.getCode().getImage().getExpiredIn());
  }

  public void setSecurityProperties(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }
}
