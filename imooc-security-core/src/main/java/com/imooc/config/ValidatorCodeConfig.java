package com.imooc.config;

import com.imooc.core.properties.SecurityProperties;
import com.imooc.validator.ImageCodeGenerator;
import com.imooc.validator.ValidatorCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorCodeConfig {

  @Autowired private SecurityProperties securityProperties;

  @Bean
  @ConditionalOnMissingBean(name = "imageCodeGenerator")
  public ValidatorCodeGenerator imageCodeGenerator() {
    ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
    imageCodeGenerator.setSecurityProperties(securityProperties);
    return imageCodeGenerator;
  }
}
