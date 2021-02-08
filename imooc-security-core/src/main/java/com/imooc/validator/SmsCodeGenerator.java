package com.imooc.validator;

import com.imooc.core.properties.SecurityProperties;
import com.imooc.dto.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
@Slf4j
public class SmsCodeGenerator implements ValidatorCodeGenerator {

  @Autowired private SecurityProperties securityProperties;

  @Override
  public ValidateCode generator(ServletWebRequest request) {
    String random =
        RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getSize());
    return new ValidateCode(random, securityProperties.getCode().getSms().getExpiredIn());
  }
}
